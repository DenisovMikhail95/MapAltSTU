package com.onlylemi.mapview.library.layer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.onlylemi.mapview.library.MapView;
import com.onlylemi.mapview.library.utils.MapMath;
import com.onlylemi.mapview.library.R;

import java.util.List;

public class MarkLayer extends MapBaseLayer {

    private List<PointF> marks;
    private List<String> marksName;
    private List<Integer> marksType;
    private MarkIsClickListener listener;

    private Bitmap bmpMark, bmpMarkTouch, bmpFlag;
    private  Bitmap bmpStairs, bmpCafe, bmpWC, bmpWard, bmpPrint, bmpTerm, bmpShop, bmpCross, bmpEnter, bmpBook, bmpAct, bmpDecan;

    private float radiusMark;
    private boolean isClickMark = false;
    private int num = -1;

    private Paint paint;

    private float locate_x,locate_y;
    private boolean isLocate = false;

    public MarkLayer(MapView mapView) {
        this(mapView, null, null,null);
    }

    public MarkLayer(MapView mapView, List<PointF> marks, List<String> marksName, List<Integer> marksType) {
        super(mapView);
        this.marks = marks;
        this.marksName = marksName;
        this.marksType = marksType;

        initLayer();
    }

    private void initLayer() {
        radiusMark = setValue(10f);

        bmpMark = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.mark);
        bmpMarkTouch = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.mark_touch);
        bmpStairs = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.bmpstairs);
        bmpCafe = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.bmpcafe);
        bmpWC = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.bmpwc);
        bmpWard = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.bmpward);
        bmpPrint = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.bmpprint);
        bmpTerm = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.bmpterm);
        bmpShop = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.bmpshop);
        bmpCross = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.bmpcross);
        bmpEnter = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.bmpenter);
        bmpBook = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.bmpbook);
        bmpAct = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.bmpact);
        bmpDecan = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.bmpdecan);
        bmpFlag = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.flag);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void onTouch(MotionEvent event) {
        if (marks != null) {
            if (!marks.isEmpty()) {
                float[] goal = mapView.convertMapXYToScreenXY(event.getX(), event.getY());
                for (int i = 0; i < marks.size(); i++) {
                    if (MapMath.getDistanceBetweenTwoPoints(goal[0], goal[1],
                            marks.get(i).x - bmpMark.getWidth() / 2, marks.get(i).y - bmpMark
                                    .getHeight() / 2) <= 50) {
                        num = i;
                        isClickMark = true;
                        break;
                    }

                    if (i == marks.size() - 1) {
                        //isClickMark = false;
                    }
                }
            }

            if (listener != null && isClickMark) {
                listener.markIsClick(num);
                mapView.refresh();
            }
        }
    }

    @Override
    public void draw(Canvas canvas, Matrix currentMatrix, float currentZoom, float
            currentRotateDegrees) {
        if (isVisible && marks != null) {
            canvas.save();
            if (!marks.isEmpty()) {
                for (int i = 0; i < marks.size(); i++) {
                    PointF mark = marks.get(i);
                    float[] goal = {mark.x, mark.y};
                    currentMatrix.mapPoints(goal);
                    //mark ico
                    switch (marksType.get(i)){
                        case 1:
                            canvas.drawBitmap(bmpMark, goal[0] - bmpMark.getWidth() / 2,
                                    goal[1] - bmpMark.getHeight() / 2, paint);
                            break;
                        case 2:
                            canvas.drawBitmap(bmpWC, goal[0] - bmpWC.getWidth() / 2,
                                    goal[1] - bmpWC.getHeight() / 2, paint);
                            break;
                        case 3:
                            canvas.drawBitmap(bmpCafe, goal[0] - bmpCafe.getWidth() / 2,
                                    goal[1] - bmpCafe.getHeight() / 2, paint);
                            break;
                        case 4:
                            canvas.drawBitmap(bmpStairs, goal[0] - bmpStairs.getWidth() / 2,
                                    goal[1] - bmpStairs.getHeight() / 2, paint);
                            break;
                        case 5:
                            canvas.drawBitmap(bmpWard, goal[0] - bmpWard.getWidth() / 2,
                                    goal[1] - bmpWard.getHeight() / 2, paint);
                            break;
                        case 6:
                            canvas.drawBitmap(bmpPrint, goal[0] - bmpPrint.getWidth() / 2,
                                    goal[1] - bmpPrint.getHeight() / 2, paint);
                            break;
                        case 7:
                            canvas.drawBitmap(bmpTerm, goal[0] - bmpTerm.getWidth() / 2,
                                    goal[1] - bmpTerm.getHeight() / 2, paint);
                            break;
                        case 8:
                            canvas.drawBitmap(bmpShop, goal[0] - bmpShop.getWidth() / 2,
                                    goal[1] - bmpShop.getHeight() / 2, paint);
                            break;
                        case 9:
                            canvas.drawBitmap(bmpEnter, goal[0] - bmpEnter.getWidth() / 2,
                                    goal[1] - bmpEnter.getHeight() / 2, paint);
                            break;
                        case 10:
                            canvas.drawBitmap(bmpCross, goal[0] - bmpCross.getWidth() / 2,
                                    goal[1] - bmpCross.getHeight() / 2, paint);
                            break;
                        case 11:
                            canvas.drawBitmap(bmpBook, goal[0] - bmpBook.getWidth() / 2,
                                    goal[1] - bmpBook.getHeight() / 2, paint);
                            break;
                        case 12:
                            canvas.drawBitmap(bmpAct, goal[0] - bmpAct.getWidth() / 2,
                                    goal[1] - bmpAct.getHeight() / 2, paint);
                            break;
                        case 13:
                            canvas.drawBitmap(bmpDecan, goal[0] - bmpDecan.getWidth() / 2,
                                    goal[1] - bmpDecan.getHeight() / 2, paint);
                            break;
                    }

                    paint.setColor(Color.parseColor("#00008B"));
                    paint.setTextSize(radiusMark*1.1f);
                    paint.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD));
                    //mark name
                    if (mapView.getCurrentZoom() > 0.5 && marksName != null
                            && marksName.size() == marks.size()) {
                        canvas.drawText(marksName.get(i), goal[0] - radiusMark, goal[1] -
                                radiusMark, paint);
                    }

                    if (i == num && isClickMark) {
                        canvas.drawBitmap(bmpMarkTouch, goal[0] - bmpMarkTouch.getWidth() / 2,
                                goal[1] - bmpMarkTouch.getHeight(), paint);
                    }
                }
            }

            if (isLocate){
                float[] goal = {locate_x, locate_y};
                currentMatrix.mapPoints(goal);
                paint.setColor(Color.BLACK);
                paint.setTextSize(radiusMark);
                paint.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD));
                canvas.drawText("ВЫ", goal[0] - radiusMark, goal[1] -
                        radiusMark*2, paint);
                canvas.drawBitmap(bmpFlag, goal[0] - bmpFlag.getWidth() / 2,
                        goal[1] - bmpFlag.getHeight(), paint);
            }
            canvas.restore();
        }
    }

    public void setLocation(float x, float y){
        locate_x = x;
        locate_y = y;
        isLocate = true;
    }

    public void setLocation(){
        isLocate = false;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setClickMark(boolean cl) {
        this.isClickMark = cl; }

    public List<PointF> getMarks() {
        return marks;
    }

    public void setMarks(List<PointF> marks) {
        this.marks = marks;
    }

    public List<String> getMarksName() {
        return marksName;
    }

    public void setMarksName(List<String> marksName) {
        this.marksName = marksName;
    }

    public boolean isClickMark() {
        return isClickMark;
    }

    public void setMarkIsClickListener(MarkIsClickListener listener) {
        this.listener = listener;
    }

    public interface MarkIsClickListener {
        void markIsClick(int num);
    }
}
