package com.onlylemi.mapview.library.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.TypedValue;
import android.view.MotionEvent;

import com.onlylemi.mapview.library.MapView;

public abstract class MapBaseLayer {

    protected static final int MAP_LEVEL = 0;
    protected static final int LOCATION_LEVEL = Integer.MAX_VALUE;

    public int level;
    public boolean isVisible = true;

    protected MapView mapView;

    public MapBaseLayer(MapView mapView) {
        this.mapView = mapView;
    }

    public abstract void onTouch(MotionEvent event);

    public abstract void draw(Canvas canvas, Matrix currentMatrix, float currentZoom,
                              float currentRotateDegrees);

    public void setLevel(int level) {
        this.level = level;
    }

    protected float setValue(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, mapView.getResources()
                .getDisplayMetrics());
    }
}
