package com.onlylemi.mapview;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.onlylemi.mapview.library.MapView;
import com.onlylemi.mapview.library.MapViewListener;
import com.onlylemi.mapview.library.layer.MapBaseLayer;
import com.onlylemi.mapview.library.layer.MarkLayer;
import com.onlylemi.mapview.library.layer.RouteLayer;
import com.onlylemi.mapview.library.utils.MapUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;




public class MainActivity extends AppCompatActivity {

    private MapView mapView; //слой карты (изображения)
    private MarkLayer markLayer; //слой меток
    private RouteLayer routeLayer; //слой взаимосвязанных узлов, для прокладки маршрута
    private Button but1, but2, but3, but4, but5; // кнопки этажи
    private  ImageButton butRoute, butRes; //кнопка проложить маршрут и сбросить
    private String image_name = "map1.png"; //имя файла для отображения

    List<PointF> marks; //список меток (координаты)
    List<String> marksName; //список имен меток
    private List<PointF> nodes; //список узлов перемещения
    private List<PointF> nodesContact; //список смежности узлов

    String roomFrom, roomTo; //начало, конец маршрута (имя помещения)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //подгрузка лого АлтГТУ в actionsbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        InputStream ims = null;
        try {
            ims = getAssets().open("altstu-logo.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(ims, null);
        getSupportActionBar().setLogo(d);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //получаем кнопки и для каждой указываем слушателя
        but1 = (Button) findViewById(R.id.butFloor1); but1.setOnClickListener(oclBtn);
        but2 = (Button) findViewById(R.id.butFloor2); but2.setOnClickListener(oclBtn);
        but3 = (Button) findViewById(R.id.butFloor3); but3.setOnClickListener(oclBtn);
        but4 = (Button) findViewById(R.id.butFloor4); but4.setOnClickListener(oclBtn);
        but5 = (Button) findViewById(R.id.butFloor5); but5.setOnClickListener(oclBtn);
        butRoute = findViewById(R.id.butRoute); butRoute.setOnClickListener(oclBtnRoute);
        butRes = findViewById(R.id.butRes); butRes.setOnClickListener(oclBtnRoute);
        //подгружаем данные меток и узлов
        marks = DataFloor1.getMarks();
        marksName = DataFloor1.getMarksName();
        nodes = DataFloor1.getNodesList();
        nodesContact = DataFloor1.getNodesContactList();

        reloadMap();
    }

    //слушатель кнопки маршрута и сброса
    View.OnClickListener oclBtnRoute = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.butRoute:
                    loadRouteDialog(); //вызываем диалог для ввода начала и конца маршрута
                    break;
                case R.id.butRes:
                    List<Integer> routeList = new ArrayList<>();
                    routeLayer.setRouteList(routeList);
                    mapView.refresh();
                    break;
            }
        }
    };

    //слушатель кнопок этажей
    View.OnClickListener oclBtn = new View.OnClickListener() {
        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View v) {
            //сбрасываем цвет текста кнопок
            but1.setTextColor(Color.WHITE);
            but2.setTextColor(Color.WHITE);
            but3.setTextColor(Color.WHITE);
            but4.setTextColor(Color.WHITE);
            but5.setTextColor(Color.WHITE);
            //нужно очистить старый слои с метками и узлами, так как переходим на новый этаж
            marks = null; marksName = null;
            nodes = null; nodesContact = null;
            List<MapBaseLayer> layers = mapView.getLayers();
            layers.remove(layers.size() -1);
            layers.remove(layers.size() -1);

            switch (v.getId()) {
                case R.id.butFloor1:
                    //указываем имя изображения которое нужно будет загрузить
                    image_name = "map1.png";
                    //выводим всплывающее сообщение
                    Toast.makeText(getApplicationContext (), "1 этаж", Toast.LENGTH_LONG).show();
                    //подгружаем метки и узлы соответствующего этажа
                    marks = DataFloor1.getMarks(); marksName = DataFloor1.getMarksName();
                    nodes = DataFloor1.getNodesList(); nodesContact = DataFloor1.getNodesContactList();
                    break;
                case R.id.butFloor2:
                    image_name = "map2.png";
                    Toast.makeText(getApplicationContext (), "2 этаж", Toast.LENGTH_LONG).show();
                    marks = DataFloor2.getMarks(); marksName = DataFloor2.getMarksName();
                    nodes = DataFloor1.getNodesList(); nodesContact = DataFloor1.getNodesContactList();
                    break;
                case R.id.butFloor3:
                    image_name = "map3.png";
                    Toast.makeText(getApplicationContext (), "3 этаж", Toast.LENGTH_LONG).show();
                    marks = DataFloor3.getMarks(); marksName = DataFloor3.getMarksName();
                    nodes = DataFloor1.getNodesList(); nodesContact = DataFloor1.getNodesContactList();
                    break;
                case R.id.butFloor4:
                    image_name = "map4.png";
                    Toast.makeText(getApplicationContext (), "4 этаж", Toast.LENGTH_LONG).show();
                    marks = DataFloor4.getMarks(); marksName = DataFloor4.getMarksName();
                    nodes = DataFloor1.getNodesList(); nodesContact = DataFloor1.getNodesContactList();
                    break;
                case R.id.butFloor5:
                    image_name = "map5.png";
                    Toast.makeText(getApplicationContext (), "5 этаж", Toast.LENGTH_LONG).show();
                    marks = DataFloor5.getMarks(); marksName = DataFloor5.getMarksName();
                    nodes = DataFloor1.getNodesList(); nodesContact = DataFloor1.getNodesContactList();
                    break;
            }
            //меняем цвет нажатой кнопки
            ((Button) v).setTextColor(Color.BLACK);
            //перерисовываем карту
            reloadMap();
        }
    };

    protected void reloadMap(){
        //получаем наш элемент MapView
        mapView = (MapView) findViewById(R.id.mapview);
        // сохраняем матрицу смещения, коэфициент зума и поворота. (они сбросятся при загрузке другого этажа)
        Matrix matr = new Matrix(mapView.getCurrentMatrix());
        float zom = mapView.getCurrentZoom();
        float rot = mapView.getCurrentRotateDegrees();
        //подгружаем изображение этажа
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getAssets().open(image_name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapView.loadMap(bitmap);
        //инициализируем слой меток
        markLayer = null;
        markLayer = new MarkLayer(mapView, marks, marksName);
        mapView.addLayer(markLayer);
        //инициализируем слой узлов маршрута
        MapUtils.init(nodes.size(), nodesContact.size());
        routeLayer = null;
        routeLayer = new RouteLayer(mapView);
        mapView.addLayer(routeLayer);
        //устанавливаем слушатель по нажатию на метку
        markLayer.setMarkIsClickListener(new MarkLayer.MarkIsClickListener() {
            @Override
            public void markIsClick(int num) {
                Toast.makeText(getApplicationContext(), marksName.get(num)
                        , Toast.LENGTH_SHORT).show();
            }
        });

        //воостанавливаем матрицу смещения, коэфициент зума и поворота, чтобы новый этаж был в том же положении, что и старый
        mapView.setCurrentMatrix(matr);
        mapView.setCurrentRotateDegrees2(rot);
        mapView.setCurrentZoom2(zom);
        mapView.refresh();

    }

    //функция вызова диалога для построения маршрута
    protected void loadRouteDialog(){
        //Получаем вид с файла route_dialog.xml, который применим для диалогового окна:
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.route_dialog, null);
        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);
        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);
        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText userInputFrom = (EditText) promptsView.findViewById(R.id.inputFrom);
        final EditText userInputTo = (EditText) promptsView.findViewById(R.id.inputTo);
        mDialogBuilder
                .setTitle("Проложить маршрут")
                .setCancelable(false)
                .setPositiveButton("Показать",
                        //слушатель по нажатию Показать
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //получаем имена помещений
                                roomFrom = userInputFrom.getText().toString();
                                roomTo = userInputTo.getText().toString();
                                if(!(roomFrom.length() == 0 || roomTo.length() == 0) ) {
                                    //поиск индексов помещений
                                    int indTo = -1, indFrom = -1;
                                    for(int i = 0; i < marksName.size() - 1; i++) {
                                        if (marksName.get(i).equals(roomFrom))
                                            indFrom = i;
                                        if (marksName.get(i).equals(roomTo))
                                            indTo = i;
                                    }
                                    if(indFrom == -1) {
                                        Toast.makeText(getApplicationContext(), "Помещение " + roomFrom + " не найдено.", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    if(indTo == -1) {
                                        Toast.makeText(getApplicationContext(), "Помещение " + roomTo + " не найдено.", Toast.LENGTH_LONG).show();
                                        return;
                                    }

                                    //используем функцию поиска кратчайшего пути между помещениями
                                    List<Integer> routeList = MapUtils.getShortestDistanceBetweenTwoPoints
                                            (marks.get(indFrom), marks.get(indTo), nodes, nodesContact);
                                    routeLayer.setNodeList(nodes);
                                    routeLayer.setRouteList(routeList);
                                    mapView.refresh();
                                }
                                else{
                                    Toast.makeText(getApplicationContext (), "Заполните оба поля!", Toast.LENGTH_LONG).show();
                                    dialog.cancel();
                                }
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();
        //и отображаем его:
        alertDialog.show();
    }
}
