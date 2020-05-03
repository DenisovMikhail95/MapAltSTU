package com.onlylemi.mapview;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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

    Floor data_floor; //все данные этажа

    String roomFrom = "", roomTo = ""; //начало, конец маршрута (имя помещения)
    int indexFrom, indexTo; //индексы помещений
    int floorFrom, floorTo, cur_floor = 1; //стартовый этаж, конечный, текущий
    String cur_building = "Главный корпус";
    int stairs; //лестница в случае перехода между этажами.
    private  boolean flag_route = false; // построен ли маршрут в данный момент
    List<Integer> routeList;

    boolean DeveloperMode = true;

    DataBaseHelper myDbHelper;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //подключаемся к базе
        myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
            myDbHelper.openDataBase();
        } catch (IOException ioe) {
        }
        //берем из базы данные о этаже
        data_floor = myDbHelper.getDataFloor(cur_building, "1");

        //получаем кнопки и для каждой указываем слушателя
        but1 = (Button) findViewById(R.id.butFloor1); but1.setOnClickListener(oclBtn);
        but2 = (Button) findViewById(R.id.butFloor2); but2.setOnClickListener(oclBtn);
        but3 = (Button) findViewById(R.id.butFloor3); but3.setOnClickListener(oclBtn);
        but4 = (Button) findViewById(R.id.butFloor4); but4.setOnClickListener(oclBtn);
        but5 = (Button) findViewById(R.id.butFloor5); but5.setOnClickListener(oclBtn);
        butRoute = findViewById(R.id.butRoute); butRoute.setOnClickListener(oclBtnRoute);
        butRes = findViewById(R.id.butRes);
        //слушатель для кнопки reset
        butRes.setOnTouchListener(new View.OnTouchListener(){
            long startTime;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажатие
                        startTime = System.currentTimeMillis();
                        routeList = new ArrayList<>();
                        routeLayer.setRouteList(routeList);
                        flag_route = false;
                        markLayer.setClickMark(false);
                        mapView.refresh();
                        roomFrom = ""; roomTo = "";
                        tvRoute.setText("");
                        but1.setEnabled(true); but2.setEnabled(true); but3.setEnabled(true); but4.setEnabled(true); but5.setEnabled(true);
                        break;
                    case MotionEvent.ACTION_UP: // отпускание
                        long totalTime = System.currentTimeMillis() - startTime;
                        long totalSecоnds = totalTime / 1000;
                        if( totalSecоnds >= 5 )
                        {
                            DeveloperMode = !DeveloperMode;
                            if(DeveloperMode)
                                Toast.makeText(getApplicationContext(), "РЕЖИМ РАЗРАБОТЧИКА", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getApplicationContext(), "ПОЛЬЗОВАТЕЛЬСКИЙ РЕЖИМ", Toast.LENGTH_SHORT).show();
                        }
                }
                return true;
            }
        });
        tvRoute = findViewById(R.id.tvRoute);
        reloadMap();
    }

    //слушатель кнопки маршрута
    View.OnClickListener oclBtnRoute = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loadRouteDialog(); //вызываем диалог для ввода начала и конца маршрута
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
            //нужно очистить старые слои с метками и узлами, так как переходим на новый этаж
            List<MapBaseLayer> layers = mapView.getLayers();
            layers.remove(layers.size() -1);
            layers.remove(layers.size() -1);

            cur_floor = 0;
            switch (v.getId()) {
                case R.id.butFloor1:
                    //указываем имя изображения которое нужно будет загрузить
                    image_name = "map1.png";
                    //подгружаем из базы данные соответствующего этажа
                    data_floor = myDbHelper.getDataFloor(cur_building,"1");
                    cur_floor = 1;
                    break;
                case R.id.butFloor2:
                    image_name = "map2.png";
                    data_floor = myDbHelper.getDataFloor(cur_building,"2");
                    cur_floor = 2;
                    break;
                case R.id.butFloor3:
                    image_name = "map3.png";
                    data_floor = myDbHelper.getDataFloor(cur_building,"3");
                    cur_floor = 3;
                    break;
                case R.id.butFloor4:
                    image_name = "map4.png";
                    data_floor = myDbHelper.getDataFloor(cur_building,"4");
                    cur_floor = 4;
                    break;
                case R.id.butFloor5:
                    image_name = "map5.png";
                    data_floor = myDbHelper.getDataFloor(cur_building,"5");
                    cur_floor = 5;
                    break;
            }
            //меняем цвет нажатой кнопки
            ((Button) v).setTextColor(Color.BLACK);
            //перерисовываем карту
            reloadMap();
            //если был построенный маршрут, отрисовываем
            if(flag_route && (cur_floor == floorFrom || cur_floor == floorTo)){
                //если старт и финиш находятся на разных этажах

                if(floorFrom != floorTo) {
                    if(cur_floor == floorFrom)// если текущий этаж стартовый. маршрут от старта к лестнице
                        buildRoute(indexFrom, data_floor.getListPos().size() - stairs);  //(лестницы в конце списка)
                    else
                        buildRoute(data_floor.getListPos().size() - stairs, indexTo);  //(лестницы в конце списка)
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
        markLayer = new MarkLayer(mapView, data_floor.getListPos(), data_floor.getListName());
        mapView.addLayer(markLayer);
        //инициализируем слой узлов маршрута
        MapUtils.init(data_floor.getListNodes().size(), data_floor.getListContacts().size());
        routeLayer = null;
        routeLayer = new RouteLayer(mapView);
        mapView.addLayer(routeLayer);
        //устанавливаем слушатель по нажатию на метку
        markLayer.setMarkIsClickListener(new MarkLayer.MarkIsClickListener() {
            @Override
            public void markIsClick(int num) {
                loadInfoDialog(num);

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
        userInputFrom.requestFocus();

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
                                    datafloors.add(myDbHelper.getMarksName(cur_building, "1"));
                                    datafloors.add(myDbHelper.getMarksName(cur_building, "2"));
                                    datafloors.add(myDbHelper.getMarksName(cur_building, "3"));
                                    datafloors.add(myDbHelper.getMarksName(cur_building, "4"));
                                    datafloors.add(myDbHelper.getMarksName(cur_building, "5"));
                                    for(int i = 0 ; i <  datafloors.size(); i++){
                                        for(int j = 0; j < datafloors.get(i).size(); j++) {
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

                                    if(cur_floor != floorFrom) {
                                        switch (floorFrom){
                                            case 1:
                                                data_floor = myDbHelper.getDataFloor(cur_building, "1");
                                                break;
                                            case 2:
                                                data_floor = myDbHelper.getDataFloor(cur_building, "2");
                                                break;
                                            case 3:
                                                data_floor = myDbHelper.getDataFloor(cur_building, "3");
                                                break;
                                            case 4:
                                                data_floor = myDbHelper.getDataFloor(cur_building, "4");
                                                break;
                                            case 5:
                                                data_floor = myDbHelper.getDataFloor(cur_building, "5");
                                                break;
                                        }
                                    }

                                    //если переход между этажами, определяем лестницу
                                    if(floorFrom != floorTo){
                                        if(data_floor.getListPos().get(indexFrom).x < 2500)
                                            stairs = 1;
                                        else if (data_floor.getListPos().get(indexFrom).x >= 2500 && data_floor.getListPos().get(indexFrom).x < 3700)
                                            stairs = 2;
                                        else if (data_floor.getListPos().get(indexFrom).x >= 3700 && data_floor.getListPos().get(indexFrom).x < 4900)
                                            stairs = 3;
                                        else if (data_floor.getListPos().get(indexFrom).x >= 4900)
                                            stairs = 4;
                                    }

                                    //переключаем на стартовый этаж программным нажатием кнопки. там и отрисуется маршрут
                                    switchFloor(floorFrom);

                                    if (floorFrom != 1 && floorTo != 1)
                                        but1.setEnabled(false);
                                    if (floorFrom != 2 && floorTo != 2)
                                        but2.setEnabled(false);
                                    if (floorFrom != 3 && floorTo != 3)
                                        but3.setEnabled(false);
                                    if (floorFrom != 4 && floorTo != 4)
                                        but4.setEnabled(false);
                                    if (floorFrom != 5 && floorTo != 5)
                                        but5.setEnabled(false);
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
                (data_floor.getListPos().get(indFrom),data_floor.getListPos().get(indTo), data_floor.getListNodes(), data_floor.getListContacts());
        routeLayer.setNodeList(data_floor.getListNodes());
        routeLayer.setRouteList(routeList);
        flag_route = true;
        mapView.mapCenterWithPoint(data_floor.getListPos().get(indFrom).x, data_floor.getListPos().get(indFrom).y);
        //mapView.setCurrentZoom(0.4f);
        mapView.refresh();
    }

    //создание меню в actionbar, содержащее строку поиска
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        menu.add("Главный корпус (ГК)");
        menu.add("Корпус Д");
        menu.add("Корпус Г");
        menu.add("Пищевой корпус (ПК)");
        menu.add("Корпус В");
        menu.add("Новый корпус");
        return true;
    }
    //событие нажатия на кнопку поиска
    @Override
    public boolean onQueryTextSubmit(String query) {
        int indexRoom = -1, indexFloor = -1;
        List< List<String>> datafloors = new ArrayList<List<String>>();
        datafloors.add(myDbHelper.getMarksName(cur_building, "1"));
        datafloors.add(myDbHelper.getMarksName(cur_building, "2"));
        datafloors.add(myDbHelper.getMarksName(cur_building, "3"));
        datafloors.add(myDbHelper.getMarksName(cur_building, "4"));
        datafloors.add(myDbHelper.getMarksName(cur_building, "5"));
        for(int i = 0 ; i <  datafloors.size() ; i++){
            for(int j = 0; j < datafloors.get(i).size() ; j++) {
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

        switchFloor(indexFloor);

        mapView.mapCenterWithPoint(data_floor.getListPos().get(indexRoom).x, data_floor.getListPos().get(indexRoom).y);
        mapView.setCurrentZoom(0.6f);
        markLayer.setNum(indexRoom);
        markLayer.setClickMark(true);
        mapView.refresh();
        loadInfoDialog(indexRoom);
        return false;
    }
    //событие изменения текста в строке поиска
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    protected void loadInfoDialog(final int num){
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView;
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);
        //настройка title
        TextView title = new TextView(this);
        title.setBackgroundColor(Color.DKGRAY);
        title.setText(data_floor.getListName().get(num));
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);
        mDialogBuilder.setCustomTitle(title);

        if(DeveloperMode){
            //Получаем вид с файла info_dialog_admin.xml, который применим для диалогового окна:
            promptsView = li.inflate(R.layout.info_dialog_admin, null);
            //Настраиваем xml для нашего AlertDialog:
            mDialogBuilder.setView(promptsView);

            //имя объекта
            final EditText edName = promptsView.findViewById(R.id.edName);
            edName.setText(data_floor.getListName().get(num));
            //тип
            //edType.setText(data_floor.getListType().get(num).toString());
            List<String> list_types = myDbHelper.getAllTypes();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_types);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            final Spinner spinner = (Spinner) promptsView.findViewById(R.id.spinType);
            spinner.setAdapter(adapter);
            spinner.setPrompt("Тип объекта");
            spinner.setSelection(data_floor.getListType().get(num) - 1);
            //описание объекта
            final EditText edDesc = promptsView.findViewById(R.id.edDesc);
            edDesc.setText(data_floor.getListDesctription().get(num));

            //final String old_name = data_floor.getListName().get(num);
            //final int old_type = (data_floor.getListType().get(num) - 1);
            //final String old_desc = data_floor.getListDesctription().get(num);

            mDialogBuilder
                    .setTitle(data_floor.getListName().get(num))
                    .setCancelable(false)
                    .setPositiveButton("Сохранить",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    //если есть изменения, сохраняем в базу
                                    if(!data_floor.getListName().get(num).equals(edName.getText().toString())
                                            || (data_floor.getListType().get(num) - 1) != spinner.getSelectedItemPosition()
                                            || !data_floor.getListDesctription().get(num).equals(edDesc.getText().toString())){
                                        myDbHelper.updateObject(data_floor.getListId().get(num), spinner.getSelectedItemPosition() + 1, edName.getText().toString(), edDesc.getText().toString());
                                        Toast.makeText(getBaseContext(), " Изменения сохранены", Toast.LENGTH_SHORT).show();
                                    }
                                    switchFloor(cur_floor);
                                    markLayer.setClickMark(false);
                                }
                            })
                    .setNegativeButton("Закрыть",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                    markLayer.setClickMark(false);
                                }
                            });
        }
        else{
            //Получаем вид с файла info_dialog.xml, который применим для диалогового окна:
            promptsView = li.inflate(R.layout.info_dialog, null);
            //Настраиваем xml для нашего AlertDialog:
            mDialogBuilder.setView(promptsView);

            TextView tvOutputInfo = promptsView.findViewById(R.id.tvOutputInfo);
            tvOutputInfo.setText(data_floor.getListDesctription().get(num));

            mDialogBuilder
                    .setTitle(data_floor.getListName().get(num))
                    .setCancelable(false)
                    .setNegativeButton("Закрыть",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                    markLayer.setClickMark(false);
                                }
                            });
        }

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();
        WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        alertDialog.getWindow().setAttributes(params);
        //и отображаем его:
        alertDialog.show();
    }

    public void switchFloor (int number_floor){
        switch (number_floor){
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
}
