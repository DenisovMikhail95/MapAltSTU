package com.onlylemi.mapview.library.utils;

import android.graphics.PointF;
import android.util.Log;

import com.onlylemi.mapview.library.utils.math.FloydAlgorithm;
import com.onlylemi.mapview.library.utils.math.GeneticAlgorithm;
import com.onlylemi.mapview.library.utils.math.TSPNearestNeighbour;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public final class MapMath {

    private MapMath() {}

    public static float getDistanceBetweenTwoPoints(float x1, float y1,
                                                    float x2, float y2) {
        return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static float getDistanceBetweenTwoPoints(PointF start, PointF end) {
        return (float) Math.sqrt(Math.pow(end.x - start.x, 2)
                + Math.pow(end.y - start.y, 2));
    }


    public static List<Integer> getShortestPathBetweenTwoPoints(int begin,
                                                                int end, float[][] matrix) {
        return FloydAlgorithm.getInstance().findCheapestPath(begin, end, matrix);
    }

    public static List<Integer> getBestPathBetweenPointsByNearestNeighbour(float[][] matrix) {
        return TSPNearestNeighbour.getInstance().tsp(matrix);
    }

    public static List<Integer> getBestPathBetweenPointsByGeneticAlgorithm(float[][] matrix) {
        GeneticAlgorithm ga = GeneticAlgorithm.getInstance();
        ga.setAutoNextGeneration(true);
        ga.setMaxGeneration(200);
        int[] best = ga.tsp(matrix);

        List<Integer> result = new ArrayList<>(best.length);
        for (int i = 0; i < best.length; i++) {
            result.add(best[i]);
        }
        return result;
    }

    public static float getDegreeBetweenTwoPointsWithHorizontal(PointF start, PointF end) {
        float angle = 90.0f;
        if (start.x != end.x) {
            angle = (float) Math.toDegrees(Math.atan((end.y - start.y)
                    / (end.x - start.x)));
            if (end.x < start.x && end.y >= start.y) {
                angle = angle + 180.0f;
            } else if (end.x < start.x && end.y > start.y) {
                angle = angle - 180.f;
            }
        } else {
            if (start.y < end.y) {
                angle = 90.0f;
            } else if (start.y > end.y) {
                angle = -90.0f;
            }
        }
        return angle;
    }

    public static float getDegreeBetweenTwoPointsWithVertical(PointF start, PointF end) {
        float angle = 90.0f;
        if (start.y != end.y) {
            angle = -(float) Math.toDegrees(Math.atan((end.x - start.x)
                    / (end.y - start.y)));
            if (end.y > start.y && end.x >= start.x) {
                angle = angle + 180.0f;
            } else if (end.y > start.y && end.x > start.x) {
                angle = angle - 180.f;
            }
        } else {
            if (start.x < end.x) {
                angle = 90.0f;
            } else if (start.x > end.x) {
                angle = -90.0f;
            }
        }
        return angle;
    }

    public static float getDegreeBetweenTwoPoints(float x1, float y1, float x2, float y2) {
        double radians = Math.atan2(y1 - y2, x1 - x2);
        return (float) Math.toDegrees(radians);
    }

    public static float getDegreeBetweenTwoPoints(PointF start, PointF end) {
        return getDegreeBetweenTwoPoints(start.x, start.y, end.x, end.y);
    }

    public static PointF getMidPointBetweenTwoPoints(float x1, float y1, float x2, float y2) {
        return new PointF((x1 + x2) / 2, (y1 + y2) / 2);
    }

    public static PointF getMidPointBetweenTwoPoints(PointF start, PointF end) {
        return getMidPointBetweenTwoPoints(start.x, start.y, end.x, end.y);
    }

    public static PointF getEveryPointBetweenTwoPoints(PointF start, PointF end, float value) {
        // y=kx+b
        float x, y;
        // with slope
        if (start.x != end.x) {
            float k = (end.y - start.y) / (end.x - start.x);
            float b = end.y - k * end.x;

            if (end.x > start.x) {
                x = Math.min(end.x, start.x) + (end.x - start.x) * value;
            } else {
                x = Math.max(end.x, start.x) + (end.x - start.x) * value;
            }
            y = k * x + b;
        } else { // no slope
            x = start.x;
            if (end.y > start.y) {
                y = Math.min(end.y, start.y) + (end.y - start.y) * value;
            } else {
                y = Math.max(end.y, start.y) + (end.y - start.y) * value;
            }
        }
        return new PointF(x, y);
    }

    public static float getDistanceFromPointToLine(PointF point, PointF linePoint1, PointF
            linePoint2) {
        // y = kx + b;
        // d = |kx-y+b| / √(k^2+1)
        float d;
        if (linePoint1.x != linePoint2.x) { // with slope
            float k = (linePoint2.y - linePoint1.y) / (linePoint2.x - linePoint1.x);
            float b = linePoint2.y - k * linePoint2.x;
            d = Math.abs(k * point.x - point.y + b) / (float) Math.sqrt(k * k + 1);
        } else { // no slope
            d = Math.abs(point.x - linePoint1.x);
        }
        return d;
    }


    public static PointF getIntersectionCoordinatesFromPointToLine(PointF point, PointF linePoint1, PointF
            linePoint2) {
        float x, y;
        if (linePoint1.x != linePoint2.x) { // with slope
            float k = (linePoint2.y - linePoint1.y) / (linePoint2.x - linePoint1.x);
            float b = linePoint2.y - k * linePoint2.x;
            // The equation of point
            if (k != 0) {
                float kV = -1 / k;
                float bV = point.y - kV * point.x;
                x = (b - bV) / (kV - k);
                y = kV * x + bV;
            } else {
                x = point.x;
                y = linePoint1.y;
            }
        } else {
            x = linePoint1.x;
            y = point.y;
        }
        return new PointF(x, y);
    }

    public static boolean isObtuseAnglePointAndLine(PointF point, PointF linePoint1, PointF
            linePoint2) {
        // A*A + B*B < C*C
        float p_l1, p_l2, l1_l2;
        p_l1 = getDistanceBetweenTwoPoints(point, linePoint1);
        p_l2 = getDistanceBetweenTwoPoints(point, linePoint2);
        l1_l2 = getDistanceBetweenTwoPoints(linePoint1, linePoint2);

        return ((p_l1 * p_l1 + l1_l2 * l1_l2) < p_l2 * p_l2)
                || ((p_l2 * p_l2 + l1_l2 * l1_l2) < p_l1 * p_l1);
    }

}
