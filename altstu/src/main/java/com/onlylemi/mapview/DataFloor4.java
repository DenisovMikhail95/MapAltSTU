package com.onlylemi.mapview;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public final class DataFloor4 {

    private DataFloor4() {}

/*
    public static List<PointF> getMarks() {
        List<PointF> marks = new ArrayList<>();
        marks.add(new PointF(379, 1352));//0
        marks.add(new PointF(254, 1707));//1
        marks.add(new PointF(370, 1849));//2
        marks.add(new PointF(266, 2138));//3
        marks.add(new PointF(529, 2348));//4
        marks.add(new PointF(769, 1994));//5
        marks.add(new PointF(980, 1994));//6
        marks.add(new PointF(922, 2351));//7
        marks.add(new PointF(1195, 1992));//8
        marks.add(new PointF(1349, 2353));//9
        marks.add(new PointF(1414, 1989));//10
        marks.add(new PointF(1687, 2351));//11
        marks.add(new PointF(1927, 2355));//12
        marks.add(new PointF(1935, 1601));//13
        marks.add(new PointF(2171, 1970));//14
        marks.add(new PointF(2184, 1603));//15
        marks.add(new PointF(2385, 1605));//16
        marks.add(new PointF(2467, 1977));//17
        marks.add(new PointF(2517, 1604));//18
        marks.add(new PointF(2672, 1598));//19
        marks.add(new PointF(2666, 1975));//20
        marks.add(new PointF(2885, 1979));//21
        marks.add(new PointF(2954, 1611));//22
        marks.add(new PointF(3108, 1974));//23
        marks.add(new PointF(3252, 1973));//24
        marks.add(new PointF(3667, 755));//25
        marks.add(new PointF(3754, 1968));//26
        marks.add(new PointF(4354, 1972));//27
        marks.add(new PointF(4640, 1976));//28
        marks.add(new PointF(4228, 1613));//29
        marks.add(new PointF(4371, 1612));//30
        marks.add(new PointF(4521, 1610));//31
        marks.add(new PointF(4667, 1610));//32
        marks.add(new PointF(4873, 1612));//33
        marks.add(new PointF(5076, 1968));//34
        marks.add(new PointF(5150, 1612));//35
        marks.add(new PointF(5385, 1614));//36
        marks.add(new PointF(5400, 2356));//37
        marks.add(new PointF(5916, 1978));//38
        marks.add(new PointF(5706, 2356));//39
        marks.add(new PointF(6058, 2362));//40
        marks.add(new PointF(6130, 1987));//41
        marks.add(new PointF(6344, 2363));//42
        marks.add(new PointF(6408, 1989));//43
        marks.add(new PointF(6552, 2271));//44
        marks.add(new PointF(6758, 1714));//45
        marks.add(new PointF(7156, 1711));//46
        marks.add(new PointF(7160, 1574));//47
        marks.add(new PointF(6759, 1503));//48
        marks.add(new PointF(7154, 1439));//49
        marks.add(new PointF(7150, 1208));//50
        marks.add(new PointF(6761, 1219));//51
        marks.add(new PointF(7150, 1025));//52

        //лестницы
        marks.add(new PointF(5619, 1997));//4
        marks.add(new PointF(3952, 1405));//3
        marks.add(new PointF(3387, 1405));//2
        marks.add(new PointF(1714, 1998));//1

        return marks;
    }

    public static List<String> getMarksName() {
        List<String> marksName = new ArrayList<>();
        for (int i = 0; i < getMarks().size() - 4; i++) {
            marksName.add(Integer.toString(i + 400));
        }
        marksName.add("Лестница #4");
        marksName.add("Лестница #3");
        marksName.add("Лестница #2");
        marksName.add("Лестница #1");
        return marksName;
    }

 */



    public static List<PointF> getNodesList() {
        List<PointF> nodes = new ArrayList<>();
        nodes.add(new PointF(311, 1285));//0
        nodes.add(new PointF(570, 1685));//1
        nodes.add(new PointF(513, 2153));//2
        nodes.add(new PointF(1955, 2168));//3
        nodes.add(new PointF(1943, 1996));//4
        nodes.add(new PointF(1708, 2019));//5
        nodes.add(new PointF(1919, 1764));//6
        nodes.add(new PointF(3308, 1773));//7
        nodes.add(new PointF(255, 2175));//8
        nodes.add(new PointF(3419, 1606));//9
        nodes.add(new PointF(3365, 1402));//10
        nodes.add(new PointF(3667, 1343));//11
        nodes.add(new PointF(3929, 1590));//12
        nodes.add(new PointF(3979, 1399));//13
        nodes.add(new PointF(3714, 728));//14
        nodes.add(new PointF(4012, 1765));//15
        nodes.add(new PointF(5414, 1776));//16
        nodes.add(new PointF(5403, 1983));//17
        nodes.add(new PointF(5625, 2023));//18
        nodes.add(new PointF(5387, 2167));//19
        nodes.add(new PointF(6961, 2147));//20
        nodes.add(new PointF(6955, 1000));//21

        return nodes;
    }

    public static List<PointF> getNodesContactList() {
        List<PointF> nodesContact = new ArrayList<PointF>();
        nodesContact.add(new PointF(0, 1));
        nodesContact.add(new PointF(1, 2));
        nodesContact.add(new PointF(2, 3));
        nodesContact.add(new PointF(3, 4));
        nodesContact.add(new PointF(4, 5));
        nodesContact.add(new PointF(4, 6));
        nodesContact.add(new PointF(6, 7));
        nodesContact.add(new PointF(7, 9));
        nodesContact.add(new PointF(7, 15));
        nodesContact.add(new PointF(9, 10));
        nodesContact.add(new PointF(9, 11));
        nodesContact.add(new PointF(11, 14));
        nodesContact.add(new PointF(11, 12));
        nodesContact.add(new PointF(12, 13));
        nodesContact.add(new PointF(12, 15));
        nodesContact.add(new PointF(15, 16));
        nodesContact.add(new PointF(16, 17));
        nodesContact.add(new PointF(17, 18));
        nodesContact.add(new PointF(17, 19));
        nodesContact.add(new PointF(19, 20));
        nodesContact.add(new PointF(20, 21));

        return nodesContact;
    }
}
