package com.onlylemi.mapview;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public final class DataFloor1 {

    private DataFloor1() {

    }


    //координаты меток помещений
    public static List<PointF> getMarks() {
        List<PointF> marks = new ArrayList<>();
        //помещения
        marks.add(new PointF(190, 1030));
        marks.add(new PointF(190, 1150));
        marks.add(new PointF(190, 1590));
        marks.add(new PointF(190, 2020));
        marks.add(new PointF(190, 2150));
        marks.add(new PointF(560, 1160));
        marks.add(new PointF(560, 1350));
        marks.add(new PointF(560, 1650));
        marks.add(new PointF(560, 1850));
        marks.add(new PointF(100, 2350));
        marks.add(new PointF(225, 2350));
        marks.add(new PointF(490, 2350));
        marks.add(new PointF(840, 2350));
        marks.add(new PointF(1045, 2350));
        marks.add(new PointF(1420, 2350));
        marks.add(new PointF(1775, 2350));
        marks.add(new PointF(1950, 2350));
        marks.add(new PointF(565, 2000));
        marks.add(new PointF(845, 2000));
        marks.add(new PointF(1125, 2000));
        marks.add(new PointF(1415, 2000));
        marks.add(new PointF(1750, 1750));
        marks.add(new PointF(2090, 1615));
        marks.add(new PointF(2225, 1615));
        marks.add(new PointF(2115, 1980));
        marks.add(new PointF(2230, 1980));
        marks.add(new PointF(2440, 1615));
        marks.add(new PointF(2735, 1615));
        marks.add(new PointF(2920, 1615));
        marks.add(new PointF(3050, 1615));
        marks.add(new PointF(2500, 1980));
        marks.add(new PointF(2930, 1980));
        marks.add(new PointF(3240, 1630));
        marks.add(new PointF(3265, 1980));
        marks.add(new PointF(4095, 1630));
        marks.add(new PointF(3955, 2020));
        marks.add(new PointF(3645, 1400));
        marks.add(new PointF(4300, 1620));
        marks.add(new PointF(4590, 1620));
        marks.add(new PointF(4800, 1620));
        marks.add(new PointF(5010, 1620));
        marks.add(new PointF(5225, 1620));
        marks.add(new PointF(4250, 1970));
        marks.add(new PointF(4540, 1970));
        marks.add(new PointF(4960, 1970));
        marks.add(new PointF(5225, 1970));
        marks.add(new PointF(5395, 1615));
        marks.add(new PointF(5400, 2370));
        marks.add(new PointF(5635, 2355));
        marks.add(new PointF(5940, 2355));
        marks.add(new PointF(6200, 2355));
        marks.add(new PointF(6480, 2355));
        marks.add(new PointF(6550, 2005));
        marks.add(new PointF(6395, 2005));
        marks.add(new PointF(6270, 2005));
        marks.add(new PointF(6165, 2005));
        marks.add(new PointF(5960, 2005));
        marks.add(new PointF(6785, 2345));
        marks.add(new PointF(7070, 2345));
        marks.add(new PointF(7135, 2145));
        marks.add(new PointF(7135, 1940));
        marks.add(new PointF(7140, 1730));
        marks.add(new PointF(6760, 1920));
        marks.add(new PointF(6755, 1480));
        marks.add(new PointF(7090, 1370));
        marks.add(new PointF(6760, 1145));
        //лестницы
        marks.add(new PointF(5628, 2007));//4
        marks.add(new PointF(3984, 1448));//3
        marks.add(new PointF(3357, 1465));//2
        marks.add(new PointF(1712, 2000));//1

        return marks;
    }

    public static List<String> getMarksName() {
        List<String> marksName = new ArrayList<>();
        for (int i = 0; i < getMarks().size() - 4; i++) {
            marksName.add(Integer.toString(i + 100));
        }
        marksName.add("Лестница #4");
        marksName.add("Лестница #3");
        marksName.add("Лестница #2");
        marksName.add("Лестница #1");

        return marksName;

    }


    //список узлов маршрута
    public static List<PointF> getNodesList() {
        List<PointF> nodes = new ArrayList<>();
        nodes.add(new PointF(141,1031 ));//0
        nodes.add(new PointF(381, 1025));//1
        nodes.add(new PointF(589,1040 ));//2
        nodes.add(new PointF(142, 1146));//3
        nodes.add(new PointF(384, 1116));//4
        nodes.add(new PointF(626, 1178));//5
        nodes.add(new PointF(147, 1500));//6
        nodes.add(new PointF(383, 1486));//7
        nodes.add(new PointF(627, 1408));//8
        nodes.add(new PointF(147, 1741));//9
        nodes.add(new PointF(382, 1742));//10
        nodes.add(new PointF(631, 1652));//11
        nodes.add(new PointF(617, 1872));//12
        nodes.add(new PointF(144, 2024));//13
        nodes.add(new PointF(383, 2017));//14
        nodes.add(new PointF(622, 2026));//15
        nodes.add(new PointF(85, 2375));//16
        nodes.add(new PointF(193, 2369));//17
        nodes.add(new PointF(133, 2165));//18
        nodes.add(new PointF(263, 2156));//19
        nodes.add(new PointF(391, 2152));//20
        nodes.add(new PointF(413, 2318));//21
        nodes.add(new PointF(651, 2153));//22
        nodes.add(new PointF(651, 2322));//23
        nodes.add(new PointF(771, 1970));//24
        nodes.add(new PointF(818, 2158));//25
        nodes.add(new PointF(808, 2358));//26
        nodes.add(new PointF(1056, 1970));//27
        nodes.add(new PointF(1049, 2159));//28
        nodes.add(new PointF(1029, 2373));//29
        nodes.add(new PointF(1317, 2166));//30
        nodes.add(new PointF(1351, 1969));//31
        nodes.add(new PointF(1643, 2157));//32
        nodes.add(new PointF(1641, 2309));//33
        nodes.add(new PointF(1313, 2309));//34
        nodes.add(new PointF(1779, 2162));//35
        nodes.add(new PointF(1782, 2309));//36
        nodes.add(new PointF(2004, 2159));//37
        nodes.add(new PointF(1922, 2383));//38
        nodes.add(new PointF(1940, 1997));//39
        nodes.add(new PointF(1681, 2034));//40
        nodes.add(new PointF(1935, 1815));//41
        nodes.add(new PointF(1722, 1807));//42
        nodes.add(new PointF(2107, 1765));//43
        nodes.add(new PointF(2073, 1597));//44
        nodes.add(new PointF(2085, 1993));//45
        nodes.add(new PointF(2180, 1587));//46
        nodes.add(new PointF(2203, 1761));//47
        nodes.add(new PointF(2211, 1992));//48
        nodes.add(new PointF(2484, 1769));//49
        nodes.add(new PointF(2407, 1593));//50
        nodes.add(new PointF(2412, 1983));//51
        nodes.add(new PointF(2761, 1767));//52
        nodes.add(new PointF(2705, 1591));//53
        nodes.add(new PointF(2904, 1764));//54
        nodes.add(new PointF(2844, 1989));//55
        nodes.add(new PointF(2891, 1590));//56
        nodes.add(new PointF(3021, 1765));//57
        nodes.add(new PointF(3012, 1590));//58
        nodes.add(new PointF(3380, 1742));//59
        nodes.add(new PointF(3470, 1888));//60
        nodes.add(new PointF(3221, 1934));//61
        nodes.add(new PointF(3361, 1609));//62
        nodes.add(new PointF(3213, 1640));//63
        nodes.add(new PointF(3333, 1442));//64
        nodes.add(new PointF(3590, 1596));//65
        nodes.add(new PointF(3579, 1364));//66
        nodes.add(new PointF(3967, 1599));//67
        nodes.add(new PointF(3960, 1422));//68
        nodes.add(new PointF(4112, 1642));//69
        nodes.add(new PointF(3995, 1779));//70
        nodes.add(new PointF(3930, 2036));//71
        nodes.add(new PointF(4283, 1769));//72
        nodes.add(new PointF(4249, 1580));//73
        nodes.add(new PointF(4204, 1989));//74
        nodes.add(new PointF(4682, 1771));//75
        nodes.add(new PointF(4590, 1999));//76
        nodes.add(new PointF(4642, 1582));//77
        nodes.add(new PointF(4804, 1769));//78
        nodes.add(new PointF(4837, 1583));//79
        nodes.add(new PointF(5116, 1772));//80
        nodes.add(new PointF(5054, 1562));//81
        nodes.add(new PointF(4876, 1981));//82
        nodes.add(new PointF(5396, 1759));//83
        nodes.add(new PointF(5417, 1571));//84
        nodes.add(new PointF(5340, 2183));//85
        nodes.add(new PointF(5595, 2164));//86
        nodes.add(new PointF(5572, 2378));//87
        nodes.add(new PointF(5850, 2166));//88
        nodes.add(new PointF(5834, 2384));//89
        nodes.add(new PointF(5990, 2163));//90
        nodes.add(new PointF(5974, 1951));//91
        nodes.add(new PointF(6296, 2161));//92
        nodes.add(new PointF(6293, 1956));//93
        nodes.add(new PointF(6242, 2388));//94
        nodes.add(new PointF(6440, 2157));//95
        nodes.add(new PointF(6445, 1951));//96
        nodes.add(new PointF(6567, 2158));//97
        nodes.add(new PointF(6535, 2410));//98
        nodes.add(new PointF(5663, 2028));//99
        nodes.add(new PointF(6803, 2157));//100
        nodes.add(new PointF(6837, 2395));//101
        nodes.add(new PointF(6982, 2167));//102
        nodes.add(new PointF(7014, 2397));//103
        nodes.add(new PointF(6965, 2006));//104
        nodes.add(new PointF(7178, 1996));//105
        nodes.add(new PointF(7158, 2171));//106
        nodes.add(new PointF(6935, 1869));//107
        nodes.add(new PointF(6725, 1965));//108
        nodes.add(new PointF(6951, 1728));//109
        nodes.add(new PointF(7175, 1791));//110
        nodes.add(new PointF(6733, 1707));//111
        nodes.add(new PointF(6791, 1275));//112
        nodes.add(new PointF(7109, 1207));//113
        nodes.add(new PointF(6734, 1166));//114
        nodes.add(new PointF(5369, 1984));//115

        return nodes;
    }

    //список смежности узлов маршрута
    public static List<PointF> getNodesContactList() {
        List<PointF> nodesContact = new ArrayList<PointF>();
        nodesContact.add(new PointF(0, 1));
        nodesContact.add(new PointF(1, 2));
        nodesContact.add(new PointF(1, 4));
        nodesContact.add(new PointF(3, 4));
        nodesContact.add(new PointF(4, 5));
        nodesContact.add(new PointF(4, 7));
        nodesContact.add(new PointF(6, 7));
        nodesContact.add(new PointF(7, 8));
        nodesContact.add(new PointF(7, 10));
        nodesContact.add(new PointF(9, 10));
        nodesContact.add(new PointF(10, 11));
        nodesContact.add(new PointF(11, 12));
        nodesContact.add(new PointF(10, 14));
        nodesContact.add(new PointF(13, 14));
        nodesContact.add(new PointF(14, 15));
        nodesContact.add(new PointF(14, 20));
        nodesContact.add(new PointF(20, 19));
        nodesContact.add(new PointF(19, 17));
        nodesContact.add(new PointF(19, 18));
        nodesContact.add(new PointF(18, 16));
        nodesContact.add(new PointF(20, 21));
        nodesContact.add(new PointF(20, 22));
        nodesContact.add(new PointF(22, 23));
        nodesContact.add(new PointF(22,25));
        nodesContact.add(new PointF(25, 24));
        nodesContact.add(new PointF(25, 26));
        nodesContact.add(new PointF(25, 28));
        nodesContact.add(new PointF(28, 27));
        nodesContact.add(new PointF(28, 29));
        nodesContact.add(new PointF(28, 30));
        nodesContact.add(new PointF(30, 31));
        nodesContact.add(new PointF(30, 32));
        nodesContact.add(new PointF(32, 33));
        nodesContact.add(new PointF(33, 34));
        nodesContact.add(new PointF(32, 37));
        nodesContact.add(new PointF(37, 39));
        nodesContact.add(new PointF(39, 40));
        nodesContact.add(new PointF(39, 41));
        nodesContact.add(new PointF(41, 42));
        nodesContact.add(new PointF(41, 43));
        nodesContact.add(new PointF(43, 44));
        nodesContact.add(new PointF(43, 45));
        nodesContact.add(new PointF(43, 47));
        nodesContact.add(new PointF(47, 46));
        nodesContact.add(new PointF(47, 48));
        nodesContact.add(new PointF(47, 49));
        nodesContact.add(new PointF(49, 50));
        nodesContact.add(new PointF(49, 51));
        nodesContact.add(new PointF(49, 52));
        nodesContact.add(new PointF(52, 53));
        nodesContact.add(new PointF(52, 54));
        nodesContact.add(new PointF(54, 56));
        nodesContact.add(new PointF(54, 55));
        nodesContact.add(new PointF(54, 57));
        nodesContact.add(new PointF(57, 58));
        nodesContact.add(new PointF(57, 59));
        nodesContact.add(new PointF(59, 60));
        nodesContact.add(new PointF(59, 70));
        nodesContact.add(new PointF(60, 61));
        nodesContact.add(new PointF(59, 62));
        nodesContact.add(new PointF(62, 63));
        nodesContact.add(new PointF(62, 64));
        nodesContact.add(new PointF(62, 65));
        nodesContact.add(new PointF(65, 66));
        nodesContact.add(new PointF(65, 67));
        nodesContact.add(new PointF(67, 68));
        nodesContact.add(new PointF(67, 69));
        nodesContact.add(new PointF(67, 70));
        nodesContact.add(new PointF(70, 71));
        nodesContact.add(new PointF(70, 72));
        nodesContact.add(new PointF(72, 73));
        nodesContact.add(new PointF(72, 74));
        nodesContact.add(new PointF(72, 75));
        nodesContact.add(new PointF(75, 76));
        nodesContact.add(new PointF(75, 77));
        nodesContact.add(new PointF(75, 78));
        nodesContact.add(new PointF(78, 79));
        nodesContact.add(new PointF(78, 80));
        nodesContact.add(new PointF(80, 81));
        nodesContact.add(new PointF(80, 82));
        nodesContact.add(new PointF(80, 83));
        nodesContact.add(new PointF(83, 84));
        nodesContact.add(new PointF(83, 115));
        nodesContact.add(new PointF(115, 85));
        nodesContact.add(new PointF(115, 99));
        nodesContact.add(new PointF(85, 86));
        nodesContact.add(new PointF(86, 87));
        nodesContact.add(new PointF(86, 88));
        nodesContact.add(new PointF(88, 89));
        nodesContact.add(new PointF(88, 90));
        nodesContact.add(new PointF(90, 91));
        nodesContact.add(new PointF(90, 92));
        nodesContact.add(new PointF(92, 94));
        nodesContact.add(new PointF(92, 95));
        nodesContact.add(new PointF(95, 97));
        nodesContact.add(new PointF(97, 98));
        nodesContact.add(new PointF(97, 100));
        nodesContact.add(new PointF(100, 101));
        nodesContact.add(new PointF(100, 102));
        nodesContact.add(new PointF(102, 103));
        nodesContact.add(new PointF(102, 104));
        nodesContact.add(new PointF(104, 105));
        nodesContact.add(new PointF(105, 106));
        nodesContact.add(new PointF(104, 107));
        nodesContact.add(new PointF(107, 108));
        nodesContact.add(new PointF(107, 109));
        nodesContact.add(new PointF(109, 110));
        nodesContact.add(new PointF(109, 111));
        nodesContact.add(new PointF(111, 112));
        nodesContact.add(new PointF(112, 113));
        nodesContact.add(new PointF(113, 114));

        return nodesContact;
    }

}
