package com.onlylemi.mapview;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public final class DataFloor5 {

    private DataFloor5() {}

    public static List<PointF> getMarks() {
        List<PointF> marks = new ArrayList<>();

        marks.add(new PointF(180, 1725));//0
        marks.add(new PointF(565, 1725));//1
        marks.add(new PointF(180, 2145));//2
        marks.add(new PointF(565, 1885));//3
        marks.add(new PointF(465, 2345));//4
        marks.add(new PointF(565, 2010));//5
        marks.add(new PointF(905, 2345));//6
        marks.add(new PointF(777, 1990));//7
        marks.add(new PointF(1405, 2345));//8
        marks.add(new PointF(1055, 1985));
        marks.add(new PointF(1400, 1985));//9
        //marks.add(new PointF(1481, 2072));//10
        marks.add(new PointF(1765, 2345));//11
        marks.add(new PointF(1940, 2345));//12

        marks.add(new PointF(1885, 1600));//13

        marks.add(new PointF(2100, 1890));//14
        marks.add(new PointF(2430, 1890));//15
        marks.add(new PointF(2945, 1890));//16
        marks.add(new PointF(3405, 1890));//17
        marks.add(new PointF(3645, 1385));//18
        marks.add(new PointF(3855, 1890));//19
        marks.add(new PointF(4345, 1890));//20
        marks.add(new PointF(4645, 1890));//21
        marks.add(new PointF(4985, 1890));//22

        marks.add(new PointF(5421, 1600));//23

        marks.add(new PointF(5395, 2345));//24
        //marks.add(new PointF(5825, 2072));//25
        marks.add(new PointF(5895, 1985));//26
        marks.add(new PointF(5615, 2345));//27
        marks.add(new PointF(6180, 1985));//28
        marks.add(new PointF(5830, 2345));//29
        marks.add(new PointF(6475, 1985));//30
        marks.add(new PointF(6110, 2345));//31
        marks.add(new PointF(6385, 2345));//32
        marks.add(new PointF(6540, 2415));//33

        marks.add(new PointF(6940, 2345));//35


        //лестницы
        marks.add(new PointF(5670, 2009));//4
        marks.add(new PointF(3958, 1398));//3
        marks.add(new PointF(3391, 1401));//2
        marks.add(new PointF(1714, 2014));//1

        return marks;
    }

    public static List<String> getMarksName() {
        List<String> marksName = new ArrayList<>();
        for (int i = 0; i < getMarks().size() - 4; i++) {
            marksName.add(Integer.toString(i + 500));
        }

        marksName.add("Лестница #4");
        marksName.add("Лестница #3");
        marksName.add("Лестница #2");
        marksName.add("Лестница #1");

        return marksName;
    }

    public static List<PointF> getNodesList() {
        List<PointF> nodes = new ArrayList<>();
        nodes.add(new PointF(377,1725 ));//0 0
        nodes.add(new PointF(180, 1725));//1
        nodes.add(new PointF(565, 1725));//2
        nodes.add(new PointF(377, 1890));//1 3
        nodes.add(new PointF(565, 1885));//4
        nodes.add(new PointF(377,2005 ));//2 5
        nodes.add(new PointF(565, 2010));//6
        nodes.add(new PointF(377, 2140));//3 7
        nodes.add(new PointF(180, 2145));//8

        nodes.add(new PointF(650, 2140));//4 9
        nodes.add(new PointF(565, 2010));//10
        nodes.add(new PointF(465, 2345));//11
        nodes.add(new PointF(795, 2140));//5 12
        nodes.add(new PointF(905, 2345));//13
        nodes.add(new PointF(777, 1990));//14
        nodes.add(new PointF(890, 2140));//6 15
        nodes.add(new PointF(1055, 1985));//16
        nodes.add(new PointF(1230, 2140));//7 17
        nodes.add(new PointF(1405, 2345));//18
        nodes.add(new PointF(1055, 1985));//19
        nodes.add(new PointF(1305, 2140));//8 20
        nodes.add(new PointF(1400, 1985));//21
        nodes.add(new PointF(1475, 2140));//9 22
        nodes.add(new PointF(1481, 2072));//23
        nodes.add(new PointF(1655, 2140));//10 24
        nodes.add(new PointF(1405, 2345));//25
        nodes.add(new PointF(1790, 2140));//11 26
        nodes.add(new PointF(1765, 2345));//27
        nodes.add(new PointF(1925, 2140));//12 28
        nodes.add(new PointF(1940, 2345));//29

        nodes.add(new PointF(1925, 2005));//13 30
        nodes.add(new PointF(1695, 1995));//31
        nodes.add(new PointF(1925, 1785));//14 32
        nodes.add(new PointF(1885, 1600));//33
        nodes.add(new PointF(1977, 1605));//15 34

        nodes.add(new PointF(2100, 1605));//16 35
        nodes.add(new PointF(2100, 1890));//36
        nodes.add(new PointF(2240, 1605));//17 37
        nodes.add(new PointF(2430, 1890));//38
        nodes.add(new PointF(2455, 1605));//18 39
        nodes.add(new PointF(2695, 1605));//19 40
        nodes.add(new PointF(2430, 1890));//41
        nodes.add(new PointF(2785, 1605));//20 42
        nodes.add(new PointF(2945, 1890));//43
        nodes.add(new PointF(2970, 1605));//21 44
        nodes.add(new PointF(3130, 1605));//22 45
        nodes.add(new PointF(2945, 1890));//46
        nodes.add(new PointF(3230, 1605));//24 47
        nodes.add(new PointF(3405, 1890));//48
        nodes.add(new PointF(3385, 1605));//23 49
        nodes.add(new PointF(3377, 1390));//50
        nodes.add(new PointF(3560, 1605));//24 51
        nodes.add(new PointF(3405, 1890));//52
        nodes.add(new PointF(3640, 1605));//25 53
        nodes.add(new PointF(3700, 1605));//26 53
        nodes.add(new PointF(3855, 1890));//54
        nodes.add(new PointF(3700, 1605));//26 55
        nodes.add(new PointF(3645, 1385));//56
        nodes.add(new PointF(3755, 1605));//27 57
        nodes.add(new PointF(3835, 1605));//28 58
        nodes.add(new PointF(3645, 1385));//59
        nodes.add(new PointF(3960, 1605));//29 60
        nodes.add(new PointF(3950, 1390));//61
        nodes.add(new PointF(4095, 1605));//30 62
        nodes.add(new PointF(3855, 1890));//63
        nodes.add(new PointF(4190, 1605));//31 64
        nodes.add(new PointF(4345, 1890));//65

        nodes.add(new PointF(4375, 1605));//32 66
        nodes.add(new PointF(4535, 1605));//33 67
        nodes.add(new PointF(4345, 1890));//68
        nodes.add(new PointF(4620, 1605));//34 69
        nodes.add(new PointF(4645, 1890));//70
        nodes.add(new PointF(4905, 1605));//35 71
        nodes.add(new PointF(5237, 1605));//36 72
        nodes.add(new PointF(4985, 1890));//73

        nodes.add(new PointF(5335, 1605));//37 74
        nodes.add(new PointF(5375, 1785));//38 75
        nodes.add(new PointF(5421, 1600));//76
        nodes.add(new PointF(5375, 1997));//39 77
        nodes.add(new PointF(5670, 2009));//78

        nodes.add(new PointF(5375, 2140));//40 79
        nodes.add(new PointF(5395, 2345));//80
        nodes.add(new PointF(5535, 2140));//41 81
        nodes.add(new PointF(5615, 2345));//82
        nodes.add(new PointF(5680, 2140));//42 83
        nodes.add(new PointF(5830, 2140));//43 84
        nodes.add(new PointF(5825, 2072));//85
        nodes.add(new PointF(5830, 2345));//86
        nodes.add(new PointF(6015, 2140));//44 87
        nodes.add(new PointF(5825, 2072));//88
        nodes.add(new PointF(6095, 2140));//45 89
        nodes.add(new PointF(6180, 1985));//90
        nodes.add(new PointF(6280, 2140));//46 91
        nodes.add(new PointF(6110, 2345));//92
        nodes.add(new PointF(6380, 2140));//47 93
        nodes.add(new PointF(6385, 2345));//94
        nodes.add(new PointF(6555, 2140));//48 95
        nodes.add(new PointF(6475, 1985));//96
        nodes.add(new PointF(6555, 2285));//49 97
        nodes.add(new PointF(6540, 2415));//98
        nodes.add(new PointF(6940, 2345));//99

        return nodes;
    }

    public static List<PointF> getNodesContactList() {
        List<PointF> nodesContact = new ArrayList<PointF>();
        nodesContact.add(new PointF(0, 1));
        nodesContact.add(new PointF(0, 2));
        nodesContact.add(new PointF(0, 3));
        nodesContact.add(new PointF(3, 4));
        nodesContact.add(new PointF(3, 5));
        nodesContact.add(new PointF(5, 6));
        nodesContact.add(new PointF(5, 7));
        nodesContact.add(new PointF(7, 8));
        nodesContact.add(new PointF(7, 9));
        nodesContact.add(new PointF(9, 10));
        nodesContact.add(new PointF(9, 11));
        nodesContact.add(new PointF(9, 12));
        nodesContact.add(new PointF(12, 13));
        nodesContact.add(new PointF(12, 14));
        nodesContact.add(new PointF(12, 15));
        nodesContact.add(new PointF(15, 16));
        nodesContact.add(new PointF(15, 17));
        nodesContact.add(new PointF(17, 18));
        nodesContact.add(new PointF(17, 19));
        nodesContact.add(new PointF(17, 20));
        nodesContact.add(new PointF(20, 21));
        nodesContact.add(new PointF(20, 22));
        nodesContact.add(new PointF(22, 23));
        nodesContact.add(new PointF(22,24));
        nodesContact.add(new PointF(24, 25));
        nodesContact.add(new PointF(24, 26));
        nodesContact.add(new PointF(26, 27));
        nodesContact.add(new PointF(26, 28));
        nodesContact.add(new PointF(28, 29));
        nodesContact.add(new PointF(28, 30));
        nodesContact.add(new PointF(30, 31));
        nodesContact.add(new PointF(30, 32));
        nodesContact.add(new PointF(32, 33));
        nodesContact.add(new PointF(32, 34));
        nodesContact.add(new PointF(34, 35));
        nodesContact.add(new PointF(35, 36));
        nodesContact.add(new PointF(35, 37));
        nodesContact.add(new PointF(37, 38));
        nodesContact.add(new PointF(37, 39));
        nodesContact.add(new PointF(39, 40));
        nodesContact.add(new PointF(40, 41));
        nodesContact.add(new PointF(40, 42));
        nodesContact.add(new PointF(42, 43));
        nodesContact.add(new PointF(42, 44));
        nodesContact.add(new PointF(44, 45));
        nodesContact.add(new PointF(45, 46));
        nodesContact.add(new PointF(45, 47));
        nodesContact.add(new PointF(47, 48));
        nodesContact.add(new PointF(47, 49));
        nodesContact.add(new PointF(49, 50));
        nodesContact.add(new PointF(49, 51));
        nodesContact.add(new PointF(51, 52));
        nodesContact.add(new PointF(51, 53));
        nodesContact.add(new PointF(53, 54));
        nodesContact.add(new PointF(54, 55));
        nodesContact.add(new PointF(54, 56));
        nodesContact.add(new PointF(56, 57));
        nodesContact.add(new PointF(56, 58));
        nodesContact.add(new PointF(58, 59));
        nodesContact.add(new PointF(59, 60));
        nodesContact.add(new PointF(59, 61));
        nodesContact.add(new PointF(61, 62));
        nodesContact.add(new PointF(61, 63));
        nodesContact.add(new PointF(63, 64));
        nodesContact.add(new PointF(63, 65));
        nodesContact.add(new PointF(65, 66));
        nodesContact.add(new PointF(65, 67));
        nodesContact.add(new PointF(67, 68));
        nodesContact.add(new PointF(68, 69));

        nodesContact.add(new PointF(68, 70));
        nodesContact.add(new PointF(70, 71));
        nodesContact.add(new PointF(70, 72));
        nodesContact.add(new PointF(72, 73));
        nodesContact.add(new PointF(73, 74));
        nodesContact.add(new PointF(73, 75));
        nodesContact.add(new PointF(75, 76));
        nodesContact.add(new PointF(76, 77));
        nodesContact.add(new PointF(76, 78));
        nodesContact.add(new PointF(78, 79));
        nodesContact.add(new PointF(78, 80));
        nodesContact.add(new PointF(80, 81));
        nodesContact.add(new PointF(80, 82));
        nodesContact.add(new PointF(82, 83));
        nodesContact.add(new PointF(82, 84));
        nodesContact.add(new PointF(84, 85));
        nodesContact.add(new PointF(85, 86));
        nodesContact.add(new PointF(85, 87));
        nodesContact.add(new PointF(85, 88));
        nodesContact.add(new PointF(88, 89));
        nodesContact.add(new PointF(88, 90));
        nodesContact.add(new PointF(90, 91));
        nodesContact.add(new PointF(90, 92));
        nodesContact.add(new PointF(92, 93));
        nodesContact.add(new PointF(92, 94));
        nodesContact.add(new PointF(94, 95));
        nodesContact.add(new PointF(94, 96));
        nodesContact.add(new PointF(96, 97));
        nodesContact.add(new PointF(96, 98));
        nodesContact.add(new PointF(98, 99));
        nodesContact.add(new PointF(98, 100));

        return nodesContact;
    }
}
