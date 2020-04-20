package com.onlylemi.mapview;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public final class DataFloor4 {

    private DataFloor4() {}

    public static List<PointF> getMarks() {
        List<PointF> marks = new ArrayList<>();

        return marks;
    }

    public static List<String> getMarksName() {
        List<String> marksName = new ArrayList<>();
        for (int i = 0; i < getMarks().size(); i++) {
            marksName.add(Integer.toString(i + 100));
        }
        return marksName;
    }
}
