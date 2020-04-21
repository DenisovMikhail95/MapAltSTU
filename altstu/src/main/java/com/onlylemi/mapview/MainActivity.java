package com.onlylemi.mapview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.onlylemi.mapview.library.MapView;
import com.onlylemi.mapview.library.MapViewListener;
import com.onlylemi.mapview.library.layer.MapBaseLayer;
import com.onlylemi.mapview.library.layer.MarkLayer;
import com.onlylemi.mapview.library.layer.RouteLayer;
import com.onlylemi.mapview.library.utils.MapUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private MarkLayer markLayer;
    private RouteLayer routeLayer;
    private Button but1,but2,but3,but4,but5;
    private static final String TAG = "MapLayerTestActivity";
    private String image_name = "map1.png";

    List<PointF> marks;
    List<String> marksName;
    private List<PointF> nodes;
    private List<PointF> nodesContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //подгрузка лого в actionsbar
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

        but1 = (Button) findViewById(R.id.butFloor1); but1.setOnClickListener(oclBtn);
        but2 = (Button) findViewById(R.id.butFloor2); but2.setOnClickListener(oclBtn);
        but3 = (Button) findViewById(R.id.butFloor3); but3.setOnClickListener(oclBtn);
        but4 = (Button) findViewById(R.id.butFloor4); but4.setOnClickListener(oclBtn);
        but5 = (Button) findViewById(R.id.butFloor5); but5.setOnClickListener(oclBtn);

        marks = DataFloor1.getMarks();
        marksName = DataFloor1.getMarksName();
        nodes = DataFloor1.getNodesList();
        nodesContract = DataFloor1.getNodesContactList();

        reloadMap();
    }


    View.OnClickListener oclBtn = new View.OnClickListener() {
        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View v) {
            but1.setTextColor(Color.WHITE);
            but2.setTextColor(Color.WHITE);
            but3.setTextColor(Color.WHITE);
            but4.setTextColor(Color.WHITE);
            but5.setTextColor(Color.WHITE);
            //нужно очистить старый слой с метками и маршрутами
            marks = null; marksName = null;
            nodes = null; nodesContract = null;
            List<MapBaseLayer> layers = mapView.getLayers();
            /*
            for (int i = 0; i < layers.size(); i++)
                if ( layers.get(i) instanceof MarkLayer) {
                    layers.remove(i); break;
                }
            for (int i = 0; i < layers.size(); i++)
                if ( layers.get(i) instanceof RouteLayer) {
                    layers.remove(i); break;
                }
             */
            layers.remove(layers.size() -1);
            layers.remove(layers.size() -1);


            switch (v.getId()) {
                case R.id.butFloor1:
                    image_name = "map1.png";
                    Toast.makeText(getApplicationContext (), "1 этаж", Toast.LENGTH_LONG).show();
                    marks = DataFloor1.getMarks(); marksName = DataFloor1.getMarksName();
                    nodes = DataFloor1.getNodesList(); nodesContract = DataFloor1.getNodesContactList();
                    break;
                case R.id.butFloor2:
                    image_name = "map2.png";
                    Toast.makeText(getApplicationContext (), "2 этаж", Toast.LENGTH_LONG).show();
                    marks = DataFloor2.getMarks(); marksName = DataFloor2.getMarksName();
                    nodes = DataFloor1.getNodesList(); nodesContract = DataFloor1.getNodesContactList();
                    break;
                case R.id.butFloor3:
                    image_name = "map3.png";
                    Toast.makeText(getApplicationContext (), "3 этаж", Toast.LENGTH_LONG).show();
                    marks = DataFloor3.getMarks(); marksName = DataFloor3.getMarksName();
                    nodes = DataFloor1.getNodesList(); nodesContract = DataFloor1.getNodesContactList();
                    break;
                case R.id.butFloor4:
                    image_name = "map4.png";
                    Toast.makeText(getApplicationContext (), "4 этаж", Toast.LENGTH_LONG).show();
                    marks = DataFloor4.getMarks(); marksName = DataFloor4.getMarksName();
                    nodes = DataFloor1.getNodesList(); nodesContract = DataFloor1.getNodesContactList();
                    break;
                case R.id.butFloor5:
                    image_name = "map5.png";
                    Toast.makeText(getApplicationContext (), "5 этаж", Toast.LENGTH_LONG).show();
                    marks = DataFloor5.getMarks(); marksName = DataFloor5.getMarksName();
                    nodes = DataFloor1.getNodesList(); nodesContract = DataFloor1.getNodesContactList();
                    break;
            }
            ((Button) v).setTextColor(Color.BLACK);
            reloadMap();
        }
    };

    protected void reloadMap(){
        mapView = (MapView) findViewById(R.id.mapview);

        Matrix matr = new Matrix(mapView.getCurrentMatrix());
        float zom = mapView.getCurrentZoom();
        float rot = mapView.getCurrentRotateDegrees();

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getAssets().open(image_name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapView.loadMap(bitmap);

        MapUtils.init(nodes.size(), nodesContract.size());
        routeLayer = null;
        routeLayer = new RouteLayer(mapView);
        mapView.addLayer(routeLayer);
        markLayer = null;
        markLayer = new MarkLayer(mapView, marks, marksName);
        mapView.addLayer(markLayer);
        markLayer.setMarkIsClickListener(new MarkLayer.MarkIsClickListener() {
            @Override
            public void markIsClick(int num) {
                Toast.makeText(getApplicationContext(), "Помещение № " + marksName.get(num)
                        , Toast.LENGTH_SHORT).show();
                PointF target = new PointF(marks.get(num).x, marks.get(num).y);
                List<Integer> routeList = MapUtils.getShortestDistanceBetweenTwoPoints
                        (marks.get(37), target, nodes, nodesContract);
                routeLayer.setNodeList(nodes);
                routeLayer.setRouteList(routeList);
                mapView.refresh();
            }
        });

        mapView.setCurrentMatrix(matr);
        mapView.setCurrentRotateDegrees2(rot);
        mapView.setCurrentZoom2(zom);
        mapView.refresh();

    }

}
