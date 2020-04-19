package com.onlylemi.mapview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
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
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private Button but1,but2,but3,but4,but5;
    private static final String TAG = "MapLayerTestActivity";
    private String image_name = "map1.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        but1 = findViewById(R.id.butFloor1); but1.setOnClickListener(oclBtn);
        but2 = findViewById(R.id.butFloor2); but2.setOnClickListener(oclBtn);
        but3 = findViewById(R.id.butFloor3); but3.setOnClickListener(oclBtn);
        but4 = findViewById(R.id.butFloor4); but4.setOnClickListener(oclBtn);
        but5 = findViewById(R.id.butFloor5); but5.setOnClickListener(oclBtn);

        reloadMap();
    }


    View.OnClickListener oclBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.butFloor1:
                    image_name = "map1.png";
                    break;
                case R.id.butFloor2:
                    image_name = "map2.png";
                    break;
                case R.id.butFloor3:
                    image_name = "map3.png";
                    break;
                case R.id.butFloor4:
                    image_name = "map4.png";
                    break;
                case R.id.butFloor5:
                    image_name = "map5.png";
                    break;
            }
            reloadMap();
        }
    };

    protected void reloadMap(){
        mapView = (MapView) findViewById(R.id.mapview);

        Matrix matr = new Matrix(mapView.getCurrentMatrix());
        float zom = mapView.getCurrentZoom();
        float rot = mapView.getCurrentRotateDegrees();
        mapView.reset();

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getAssets().open(image_name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapView.loadMap(bitmap);
        mapView.setMinZoom((float) 0.1);
        mapView.setMaxZoom((float) 2.5);
        mapView.setScaleAndRotateTogether(true);

        //mapView.setCurrentMatrix(matr);
        //mapView.setCurrentRotateDegrees(rot);
        //mapView.setCurrentZoom(zom);
       // mapView.setMid(mid);


    }

}
