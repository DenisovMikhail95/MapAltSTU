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
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private Button but1,but2,but3,but4,but5;
    private static final String TAG = "MapLayerTestActivity";
    private String image_name = "map1.png";

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
            switch (v.getId()) {
                case R.id.butFloor1:
                    image_name = "map1.png";
                    Toast.makeText(getApplicationContext (), "1 этаж", Toast.LENGTH_LONG).show();
                    break;
                case R.id.butFloor2:
                    image_name = "map2.png";
                    Toast.makeText(getApplicationContext (), "2 этаж", Toast.LENGTH_LONG).show();
                    break;
                case R.id.butFloor3:
                    image_name = "map3.png";
                    Toast.makeText(getApplicationContext (), "3 этаж", Toast.LENGTH_LONG).show();
                    break;
                case R.id.butFloor4:
                    image_name = "map4.png";
                    Toast.makeText(getApplicationContext (), "4 этаж", Toast.LENGTH_LONG).show();
                    break;
                case R.id.butFloor5:
                    image_name = "map5.png";
                    Toast.makeText(getApplicationContext (), "5 этаж", Toast.LENGTH_LONG).show();
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
        //mapView.reset();

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getAssets().open(image_name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapView.loadMap(bitmap);

        mapView.setCurrentMatrix(matr);
        mapView.setCurrentRotateDegrees2(rot);
        mapView.setCurrentZoom2(zom);
        mapView.refresh();

    }

}
