package com.onlylemi.mapview;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.onlylemi.mapview.library.MapView;
import com.onlylemi.mapview.library.layer.MapBaseLayer;
import com.onlylemi.mapview.library.layer.MarkLayer;
import com.onlylemi.mapview.library.layer.RouteLayer;
import com.onlylemi.mapview.library.utils.MapUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private MapView mapView; //слой карты (изображения)
    private MarkLayer markLayer; //слой меток
    private RouteLayer routeLayer; //слой взаимосвязанных узлов, для прокладки маршрута
    private Button but1, but2, but3, but4, but5; // кнопки этажи
    private  ImageButton butRoute, butRes; //кнопка проложить маршрут и сбросить
    private TextView tvRoute;
    private String image_name = "map1.png"; //имя файла для отображения

    List<PointF> marks; //список меток (координаты)
    List<String> marksName; //список имен меток
    private List<PointF> nodes; //список узлов перемещения
    private List<PointF> nodesContact; //список смежности узлов

    String roomFrom = "", roomTo = ""; //начало, конец маршрута (имя помещения)
    int indexFrom, indexTo; //индексы помещений
    int floorFrom, floorTo, cur_floor = 1; //стартовый этаж, конечный, текущий
    int stairs; //лестница в случае перехода между этажами.
    private  boolean flag_route = false; // построен ли маршрут в данный момент
    List<Integer> routeList;

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
        tvRoute = findViewById(R.id.tvRoute);
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
                    routeList = new ArrayList<>();
                    routeLayer.setRouteList(routeList);
                    flag_route = false;
                    mapView.refresh();
                    roomFrom = ""; roomTo = "";
                    tvRoute.setText("");
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

            cur_floor = 0;
            switch (v.getId()) {
                case R.id.butFloor1:
                    //указываем имя изображения которое нужно будет загрузить
                    image_name = "map1.png";
                    //подгружаем метки и узлы соответствующего этажа
                    marks = DataFloor1.getMarks(); marksName = DataFloor1.getMarksName();
                    nodes = DataFloor1.getNodesList(); nodesContact = DataFloor1.getNodesContactList();
                    cur_floor = 1;
                    break;
                case R.id.butFloor2:
                    image_name = "map2.png";
                    marks = DataFloor2.getMarks(); marksName = DataFloor2.getMarksName();
                    nodes = DataFloor2.getNodesList(); nodesContact = DataFloor2.getNodesContactList();
                    cur_floor = 2;
                    break;
                case R.id.butFloor3:
                    image_name = "map3.png";
                    marks = DataFloor3.getMarks(); marksName = DataFloor3.getMarksName();
                    nodes = DataFloor3.getNodesList(); nodesContact = DataFloor3.getNodesContactList();
                    cur_floor = 3;
                    break;
                case R.id.butFloor4:
                    image_name = "map4.png";
                    marks = DataFloor4.getMarks(); marksName = DataFloor4.getMarksName();
                    nodes = DataFloor4.getNodesList(); nodesContact = DataFloor4.getNodesContactList();
                    cur_floor = 4;
                    break;
                case R.id.butFloor5:
                    image_name = "map5.png";
                    marks = DataFloor5.getMarks(); marksName = DataFloor5.getMarksName();
                    nodes = DataFloor5.getNodesList(); nodesContact = DataFloor5.getNodesContactList();
                    cur_floor = 5;
                    break;
            }
            //меняем цвет нажатой кнопки
            ((Button) v).setTextColor(Color.BLACK);
            //перерисовываем карту
            reloadMap();
            //если был построенный маршрут, отрисовываем
            if(flag_route){
                //если старт и финиш находятся на разных этажах
                if(floorFrom != floorTo) {
                    if(cur_floor == floorFrom)// если текущий этаж стартовый. маршрут от старта к лестнице
                        buildRoute(indexFrom, marks.size() - stairs);  //(лестницы в конце списка)
                    else
                        buildRoute(marks.size() - stairs, indexTo);  //(лестницы в конце списка)
                }
                else { //если маршрут на одном этаже
                    buildRoute(indexFrom, indexTo);
                }
                tvRoute.setText("Откуда: " + roomFrom + " (" + floorFrom + " этаж)\n" + "Куда: " + roomTo + " (" + floorTo + " этаж)");
            }

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
        if(flag_route) {
            userInputFrom.setText(roomFrom);
            userInputTo.setText(roomTo);
        }

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
                                    //поиск индексов помещений по этажам
                                    indexTo = -1; indexFrom = -1;
                                    List< List<String>> datafloors = new ArrayList<List<String>>();
                                    datafloors.add(DataFloor1.getMarksName());
                                    datafloors.add(DataFloor2.getMarksName());
                                    datafloors.add(DataFloor3.getMarksName());
                                    datafloors.add(DataFloor4.getMarksName());
                                    datafloors.add(DataFloor5.getMarksName());
                                    for(int i = 0 ; i <  datafloors.size(); i++){
                                        for(int j = 0; j < datafloors.get(i).size() - 1; j++) {
                                            if (datafloors.get(i).get(j).equals(roomFrom)){
                                                indexFrom = j; floorFrom = i + 1;
                                            }
                                            if (datafloors.get(i).get(j).equals(roomTo)) {
                                                indexTo = j; floorTo = i + 1;
                                            }
                                        }
                                        if(indexFrom != -1 && indexTo != -1)
                                            break;
                                    }

                                    if(indexFrom == -1) {
                                        Toast.makeText(getApplicationContext(), "Помещение " + roomFrom + " не найдено.", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    if(indexTo == -1) {
                                        Toast.makeText(getApplicationContext(), "Помещение " + roomTo + " не найдено.", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    flag_route = true;

                                    //если переход между этажами, определяем лестницу
                                    if(floorFrom != floorTo){
                                        if(marks.get(indexFrom).x < 2500)
                                            stairs = 1;
                                        else if (marks.get(indexFrom).x >= 2500 && marks.get(indexFrom).x < 3700)
                                            stairs = 2;
                                        else if (marks.get(indexFrom).x >= 3700 && marks.get(indexFrom).x < 4900)
                                            stairs = 3;
                                        else if (marks.get(indexFrom).x >= 4900)
                                            stairs = 4;
                                    }

                                    //переключаем на стартовый этаж программным нажатием кнопки. там и отрисуется маршрут
                                    switch (floorFrom){
                                        case 1:
                                            but1.performClick();
                                            break;
                                        case 2:
                                            but2.performClick();
                                            break;
                                        case 3:
                                            but3.performClick();
                                            break;
                                        case 4:
                                            but4.performClick();
                                            break;
                                        case 5:
                                            but5.performClick();
                                            break;
                                    }
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

    protected  void buildRoute(int indFrom, int indTo){
        //используем функцию поиска кратчайшего пути между помещениями
        routeList = MapUtils.getShortestDistanceBetweenTwoPoints
                (marks.get(indFrom), marks.get(indTo), nodes, nodesContact);
        routeLayer.setNodeList(nodes);
        routeLayer.setRouteList(routeList);
        flag_route = true;
        mapView.mapCenterWithPoint(marks.get(indFrom).x, marks.get(indFrom).y);
        mapView.refresh();
    }

    //создание меню в actionbar, содержащее только строку поиска
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }
    //событие нажатия на кнопку поиска
    @Override
    public boolean onQueryTextSubmit(String query) {
        int indexRoom = -1, indexFloor = -1;
        List< List<String>> datafloors = new ArrayList<List<String>>();
        datafloors.add(DataFloor1.getMarksName());
        datafloors.add(DataFloor2.getMarksName());
        datafloors.add(DataFloor3.getMarksName());
        datafloors.add(DataFloor4.getMarksName());
        datafloors.add(DataFloor5.getMarksName());
        for(int i = 0 ; i <  datafloors.size(); i++){
            for(int j = 0; j < datafloors.get(i).size() - 1; j++) {
                if (datafloors.get(i).get(j).equals(query.toString())){
                    indexRoom = j; indexFloor = i + 1;
                }
            }
            if(indexRoom != -1)
                break;
        }
        if(indexRoom == -1) {
            Toast.makeText(getApplicationContext(), "Поиск не дал результатов", Toast.LENGTH_LONG).show();
            return false;
        }
        switch (indexFloor){
            case 1:
                but1.performClick();
                break;
            case 2:
                but2.performClick();
                break;
            case 3:
                but3.performClick();
                break;
            case 4:
                but4.performClick();
                break;
            case 5:
                but5.performClick();
                break;
        }

        mapView.mapCenterWithPoint(marks.get(indexRoom).x, marks.get(indexRoom).y);
        markLayer.setNum(indexRoom);
        markLayer.setClickMark(true);
        mapView.refresh();
        return false;
    }
    //событие изменения текста в строке поиска
    @Override
    public boolean onQueryTextChange(String newText) {
        // User changed the text
        return false;
    }
}
