package com.onlylemi.mapview.library.layer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.onlylemi.mapview.library.MapView;
import com.onlylemi.mapview.library.R;

import java.util.List;

/**
 * RouteLayer
 *
 * @author: onlylemi
 */
public class RouteLayer extends MapBaseLayer {

    private List<Integer> routeList; // routes list
    private List<PointF> nodeList; // nodes list

    private float routeWidth; // the width of route

    private Bitmap routeStartBmp;
    private Bitmap routeEndBmp;
    private  Bitmap startStairsBmp;
    private  Bitmap endStairsBmp;

    private Paint paint;

    public RouteLayer(MapView mapView) {
        this(mapView, null, null);
    }

    public RouteLayer(MapView mapView, List<PointF> nodeList, List<Integer> routeList) {
        super(mapView);
        this.nodeList = nodeList;
        this.routeList = routeList;

        initLayer();
    }

    private void initLayer() {
        this.routeWidth = 5;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        routeStartBmp = BitmapFactory.decodeResource(mapView.getResources(),
                R.mipmap.start_point);
        routeEndBmp = BitmapFactory.decodeResource(mapView.getResources(),
                R.mipmap.end_point);

        startStairsBmp = BitmapFactory.decodeResource(mapView.getResources(),
                R.mipmap.start_stairs);
        endStairsBmp = BitmapFactory.decodeResource(mapView.getResources(),
                R.mipmap.end_stairs);

    }

    @Override
    public void onTouch(MotionEvent event) {

    }

    @Override
    public void draw(Canvas canvas, Matrix currentMatrix, float currentZoom, float
            currentRotateDegrees) {
        if (isVisible && routeList != null && nodeList != null) {
            canvas.save();

            drawing:
            if (!routeList.isEmpty() && !nodeList.isEmpty()) {
                // draw route
                for (int i = 0; i < routeList.size() - 1; i++) {

                    if (routeList.get(i) >= nodeList.size() ||
                            routeList.get(i + 1) >= nodeList.size()) {
                        break drawing;
                    }

                    float[] goal1 = {nodeList.get(routeList.get(i)).x, nodeList.get(routeList.get(i)).y};
                    float[] goal2 = {nodeList.get(routeList.get(i + 1)).x, nodeList.get(routeList.get(i + 1)).y};
                    currentMatrix.mapPoints(goal1);
                    currentMatrix.mapPoints(goal2);
                    //For test
                    //paint.setColor(Color.rgb((int) (Math.random() * 255d), (int) (Math.random() * 255d), (int) (Math.random() * 255d)));
                    paint.setColor(Color.RED);
                    paint.setStrokeWidth(routeWidth);
                    canvas.drawLine(goal1[0], goal1[1], goal2[0], goal2[1], paint);
                }

                // draw bmp
                float[] goal1 = {nodeList.get(routeList.get(0)).x,
                        nodeList.get(routeList.get(0)).y};
                float[]  goal2 = {
                        nodeList.get(routeList.get(routeList.size() - 1)).x,
                        nodeList.get(routeList.get(routeList.size() - 1)).y};

                float[] start = {goal1[0],goal1[1]}; float[] end = {goal2[0],goal2[1]};

                currentMatrix.mapPoints(goal1);
                currentMatrix.mapPoints(goal2);

                if(isStairs(start[0],start[1]))
                    canvas.drawBitmap(startStairsBmp,
                            goal1[0] - startStairsBmp.getWidth() / 2, goal1[1]
                                    - startStairsBmp.getHeight(), paint);
                else
                    canvas.drawBitmap(routeStartBmp,
                            goal1[0] - routeStartBmp.getWidth() / 2, goal1[1]
                                    - routeStartBmp.getHeight(), paint);

                if(isStairs(end[0],end[1]))
                    canvas.drawBitmap(endStairsBmp,
                            goal2[0] - endStairsBmp.getWidth() / 2, goal2[1]
                                    - endStairsBmp.getHeight(), paint);
                else
                    canvas.drawBitmap(routeEndBmp,
                            goal2[0] - routeEndBmp.getWidth() / 2, goal2[1]
                                    - routeEndBmp.getHeight(), paint);

                /*
                canvas.drawBitmap(routeStartBmp,
                        goal1[0] - routeStartBmp.getWidth() / 2, goal1[1]
                                - routeStartBmp.getHeight(), paint);
                canvas.drawBitmap(routeEndBmp,
                        goal2[0] - routeEndBmp.getWidth() / 2, goal2[1]
                                - routeEndBmp.getHeight(), paint);

                 */
            }

            canvas.restore();
        }
    }

    public void setNodeList(List<PointF> nodeList) {
        this.nodeList = nodeList;
    }

    public void setRouteList(List<Integer> routeList) {
        this.routeList = routeList;
    }

    public boolean isStairs(float x, float y){
        if((x > 1558 && x < 1830 && y > 1927 && y < 2100)
            || (x > 3323 && x < 3454 && y > 1287 && y < 1535)
                || (x > 3889 && x < 4017 && y > 1287 && y < 1535)
                || (x > 5511 && x < 5780 && y > 1287 && y < 1535)){
            return true;
        }
        else
            return  false;
    }
}
