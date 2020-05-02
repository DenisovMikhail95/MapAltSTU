package com.onlylemi.mapview;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public final class DataFloor3 {

    private DataFloor3() {}


    public static List<PointF> getMarks() {
        List<PointF> marks = new ArrayList<>();
        marks.add(new PointF(180, 1032));//0
        marks.add(new PointF(571, 1228));//1
        marks.add(new PointF(187, 1305));//2
        marks.add(new PointF(571, 1513));//3
        marks.add(new PointF(177, 1657));//4
        marks.add(new PointF(573, 1746));//5
        marks.add(new PointF(190, 1907));//6
        marks.add(new PointF(569, 1958));//7
        marks.add(new PointF(190, 2155));//8
        marks.add(new PointF(461, 2352));//9
        marks.add(new PointF(778, 1990));//10
        marks.add(new PointF(851, 2356));//11
        marks.add(new PointF(1005, 1989));//12
        marks.add(new PointF(1142, 2354));//13
        marks.add(new PointF(1217, 1988));//14
        marks.add(new PointF(1424, 2355));//15
        marks.add(new PointF(1415, 1986));//16
        marks.add(new PointF(1702, 2356));//17
        marks.add(new PointF(1940, 2354));//18
        marks.add(new PointF(1937, 1609));//19
        marks.add(new PointF(2177, 1961));//20
        marks.add(new PointF(2247, 1608));//21
        marks.add(new PointF(2462, 1964));//22
        marks.add(new PointF(2539, 1609));//23
        marks.add(new PointF(2689, 1609));//24
        marks.add(new PointF(2751, 1963));//25
        marks.add(new PointF(2967, 1611));//26
        marks.add(new PointF(3037, 1961));//27
        marks.add(new PointF(3541, 1963));//28
        marks.add(new PointF(4041, 1971));//29
        marks.add(new PointF(4293, 1969));//30
        marks.add(new PointF(4216, 1614));//31
        marks.add(new PointF(4362, 1616));//32
        marks.add(new PointF(4576, 1976));//33
        marks.add(new PointF(4510, 1613));//34
        marks.add(new PointF(4653, 1612));//35
        marks.add(new PointF(4870, 1972));//36
        marks.add(new PointF(4790, 1611));//37
        marks.add(new PointF(4927, 1611));//38
        marks.add(new PointF(5147, 1973));//39
        marks.add(new PointF(5074, 1613));//40
        marks.add(new PointF(5211, 1609));//41
        marks.add(new PointF(5380, 2362));//42
        marks.add(new PointF(5621, 2359));//43
        marks.add(new PointF(5899, 2358));//44
        marks.add(new PointF(5914, 1984));//45
        marks.add(new PointF(6260, 2355));//46
        marks.add(new PointF(6118, 1989));//47
        marks.add(new PointF(6331, 1994));//48
        marks.add(new PointF(6541, 2361));//49
        marks.add(new PointF(6553, 1995));//50
        marks.add(new PointF(6938, 2355));//51
        marks.add(new PointF(7134, 2157));//52
        marks.add(new PointF(7133, 2012));//53
        marks.add(new PointF(6800, 2005));//54
        marks.add(new PointF(6755, 1858));//55
        marks.add(new PointF(7139, 1709));//56
        marks.add(new PointF(6751, 1533));//57
        marks.add(new PointF(7140, 1441));//58
        marks.add(new PointF(6750, 1299));//59
        marks.add(new PointF(7141, 1307));//60
        marks.add(new PointF(6748, 1150));//61
        marks.add(new PointF(7142, 1155));//62
        marks.add(new PointF(7141, 1018));//63

        //лестницы
        marks.add(new PointF(5611, 1996));//4
        marks.add(new PointF(3964, 1408));//3
        marks.add(new PointF(3384, 1408));//2
        marks.add(new PointF(1712, 1996));//1


        return marks;
    }

    public static List<String> getMarksName() {
        List<String> marksName = new ArrayList<>();
        for (int i = 0; i < getMarks().size() - 4; i++) {
            marksName.add(Integer.toString(i + 300));
        }
        marksName.add("Лестница #4");
        marksName.add("Лестница #3");
        marksName.add("Лестница #2");
        marksName.add("Лестница #1");

        return marksName;
    }


    public static List<PointF> getNodesList() {
        List<PointF> nodes = new ArrayList<>();
        nodes.add(new PointF(383, 1013));//0
        nodes.add(new PointF(383, 2036));//1
        nodes.add(new PointF(388, 2164));//2
        nodes.add(new PointF(1943, 2161));//3
        nodes.add(new PointF(1931, 1986));//4
        nodes.add(new PointF(1703, 2022));//5
        nodes.add(new PointF(1921, 1768));//6
        nodes.add(new PointF(3174, 1764));//7
        nodes.add(new PointF(1912, 1582));//8
        nodes.add(new PointF(5321, 1782));//9
        nodes.add(new PointF(5377, 2008));//10
        nodes.add(new PointF(5619, 2021));//11
        nodes.add(new PointF(5366, 2183));//12
        nodes.add(new PointF(6968, 2181));//13
        nodes.add(new PointF(6950, 2041));//14
        nodes.add(new PointF(6794, 2032));//15
        nodes.add(new PointF(6953, 1003));//16
        nodes.add(new PointF(3398, 1768));//17
        nodes.add(new PointF(3972, 1770));//18
        nodes.add(new PointF(3367, 1404));//19
        nodes.add(new PointF(3951, 1403));//20

        return nodes;
    }

    public static List<PointF> getNodesContactList() {
        List<PointF> nodesContact = new ArrayList<PointF>();
        nodesContact.add(new PointF(0, 1));
        nodesContact.add(new PointF(1, 2));
        nodesContact.add(new PointF(2, 3));
        nodesContact.add(new PointF(0, 1));
        nodesContact.add(new PointF(3, 4));
        nodesContact.add(new PointF(4, 5));
        nodesContact.add(new PointF(4, 6));
        nodesContact.add(new PointF(6, 7));
        nodesContact.add(new PointF(6, 8));
        nodesContact.add(new PointF(7, 17));
        nodesContact.add(new PointF(17, 19));
        nodesContact.add(new PointF(17, 18));
        nodesContact.add(new PointF(18, 20));
        nodesContact.add(new PointF(18, 9));
        nodesContact.add(new PointF(9, 10));
        nodesContact.add(new PointF(10, 11));
        nodesContact.add(new PointF(10, 12));
        nodesContact.add(new PointF(12, 13));
        nodesContact.add(new PointF(13, 14));
        nodesContact.add(new PointF(14, 15));
        nodesContact.add(new PointF(14, 16));

        return nodesContact;
    }
}
