package com.onlylemi.mapview;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public final class DataFloor2 {

    private DataFloor2() {}

    public static List<PointF> getMarks() {

        List<PointF> marks = new ArrayList<>();
        marks.add(new PointF(1929, 1585));//0
        marks.add(new PointF(182, 1123));//1
        marks.add(new PointF(575, 1172));//2
        marks.add(new PointF(181, 1394));//3
        marks.add(new PointF(575, 1377));//4
        marks.add(new PointF(183, 1671));//5
        marks.add(new PointF(579, 1664));//6
        marks.add(new PointF(179, 1962));//7
        marks.add(new PointF(579, 1872));//8
        marks.add(new PointF(575, 2022));//9
        marks.add(new PointF(247, 2171));//10
        marks.add(new PointF(772, 2008));//11
        marks.add(new PointF(296, 2367));//12
        marks.add(new PointF(613, 2374));//13
        marks.add(new PointF(783, 2374));//14
        marks.add(new PointF(930, 2376));//15
        marks.add(new PointF(1060, 2007));//16
        marks.add(new PointF(1216, 2371));//17
        marks.add(new PointF(1408, 2000));//18
        marks.add(new PointF(1498, 2368));//19
        marks.add(new PointF(1633, 2368));//20
        marks.add(new PointF(1775, 2373));//21
        marks.add(new PointF(1941, 2377));//22
        marks.add(new PointF(2116, 1978));//23
        marks.add(new PointF(1942, 1706));//24
        marks.add(new PointF(2188, 1625));//25
        marks.add(new PointF(2258, 1964));//26
        marks.add(new PointF(2423, 1916));//27
        marks.add(new PointF(2408, 2040));//28
        marks.add(new PointF(2545, 1621));//29
        marks.add(new PointF(2610, 1980));//30
        marks.add(new PointF(2815, 1625));//31
        marks.add(new PointF(2960, 1625));//32
        marks.add(new PointF(3096, 1618));//33
        marks.add(new PointF(2882, 1978));//34
        marks.add(new PointF(3084, 1981));//35
        marks.add(new PointF(3249, 1982));//36
        marks.add(new PointF(3410, 1984));//37
        marks.add(new PointF(3717, 1979));//38
        marks.add(new PointF(3733, 1122));//39
        marks.add(new PointF(3384, 1055));//40
        marks.add(new PointF(3990, 1056));//41
        marks.add(new PointF(3662, 693));//42
        marks.add(new PointF(3342, 274));//43
        marks.add(new PointF(3498, 283));//44
        marks.add(new PointF(3680, 256));//45
        marks.add(new PointF(3671, 375));//46
        marks.add(new PointF(3855, 281));//47
        marks.add(new PointF(3959, 1980));//48
        marks.add(new PointF(4089, 1987));//49
        marks.add(new PointF(4320, 1994));//50
        marks.add(new PointF(4231, 1621));//51
        marks.add(new PointF(4370, 1622));//52
        marks.add(new PointF(4519, 1615));//53
        marks.add(new PointF(4526, 1994));//54
        marks.add(new PointF(4657, 1616));//55
        marks.add(new PointF(4666, 1987));//56
        marks.add(new PointF(4800, 1616));//57
        marks.add(new PointF(4816, 1985));//58
        marks.add(new PointF(4955, 1615));//59
        marks.add(new PointF(4965, 1980));//60
        marks.add(new PointF(5097, 1609));//61
        marks.add(new PointF(5169, 1982));//62
        marks.add(new PointF(5233, 1602));//63
        marks.add(new PointF(5410, 1639));//64
        marks.add(new PointF(5409, 2373));//65
        marks.add(new PointF(5577, 2365));//66
        marks.add(new PointF(5712, 2363));//67
        marks.add(new PointF(5924, 2367));//68
        marks.add(new PointF(5954, 2000));//69
        marks.add(new PointF(6135, 2360));//70
        marks.add(new PointF(6209, 2003));//71
        marks.add(new PointF(6277, 2361));//72
        marks.add(new PointF(6415, 2365));//73
        marks.add(new PointF(6493, 2005));//74
        marks.add(new PointF(6576, 2368));//75
        marks.add(new PointF(6711, 2366));//76
        marks.add(new PointF(7018, 2364));//77
        marks.add(new PointF(7148, 2166));//78
        marks.add(new PointF(6779, 2011));//79
        marks.add(new PointF(7154, 1944));//80
        marks.add(new PointF(6769, 1790));//81
        marks.add(new PointF(7153, 1669));//82
        marks.add(new PointF(6768, 1589));//83
        marks.add(new PointF(7158, 1456));//84
        marks.add(new PointF(6772, 1376));//85
        marks.add(new PointF(7160, 1233));//86
        marks.add(new PointF(6768, 1159));//87
        marks.add(new PointF(7164, 1035));//88

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
            marksName.add(Integer.toString(i + 200));
        }
        marksName.add("Лестница #1");
        marksName.add("Лестница #2");
        marksName.add("Лестница #3");
        marksName.add("Лестница #4");

        return marksName;
    }

    public static List<PointF> getNodesList() {
        List<PointF> nodes = new ArrayList<>();
        nodes.add(new PointF(621, 1067));//0
        nodes.add(new PointF(394, 1042));//1
        nodes.add(new PointF(380, 1181));//2
        nodes.add(new PointF(594, 1147));//3
        nodes.add(new PointF(150, 1169));//4
        nodes.add(new PointF(392, 1314));//5
        nodes.add(new PointF(607, 1348));//6
        nodes.add(new PointF(216, 1417));//7
        nodes.add(new PointF(377, 1603));//8
        nodes.add(new PointF(391, 1737));//9
        nodes.add(new PointF(598, 1729));//10
        nodes.add(new PointF(154, 1649));//11
        nodes.add(new PointF(392, 1873));//12
        nodes.add(new PointF(604, 1906));//13
        nodes.add(new PointF(550, 2046));//14
        nodes.add(new PointF(160, 2005));//15
        nodes.add(new PointF(380, 2001));//16
        nodes.add(new PointF(417, 2193));//17
        nodes.add(new PointF(218, 2203));//18
        nodes.add(new PointF(259, 2360));//19
        nodes.add(new PointF(619, 2194));//20
        nodes.add(new PointF(653, 2383));//21
        nodes.add(new PointF(795, 2344));//22

        nodes.add(new PointF(782, 2172));//23
        nodes.add(new PointF(750, 1939));//24
        nodes.add(new PointF(966, 2180));//25
        nodes.add(new PointF(901, 2382));//26
        nodes.add(new PointF(1053, 2184));//27
        nodes.add(new PointF(1080, 1998));//28
        nodes.add(new PointF(1237, 2365));//29
        nodes.add(new PointF(1284, 2181));//30
        nodes.add(new PointF(1438, 2009));//31
        nodes.add(new PointF(1468, 2178));//32
        nodes.add(new PointF(1518, 2371));//33
        nodes.add(new PointF(1757, 2176));//34
        nodes.add(new PointF(1770, 2311));//35
        nodes.add(new PointF(1616, 2329));//36
        nodes.add(new PointF(1751, 2394));//37
        nodes.add(new PointF(1966, 2330));//38
        nodes.add(new PointF(1948, 2179));//39
        nodes.add(new PointF(1962, 2021));//40
        nodes.add(new PointF(1701, 2040));//41
        nodes.add(new PointF(2015, 1790));//42
        nodes.add(new PointF(1905, 1717));//43
        nodes.add(new PointF(1959, 1577));//44
        nodes.add(new PointF(2191, 1783));//45
        nodes.add(new PointF(2216, 1630));//46
        nodes.add(new PointF(2085, 2002));//47
        nodes.add(new PointF(2245, 1987));//48
        nodes.add(new PointF(2435, 1898));//49
        nodes.add(new PointF(2430, 2031));//50
        nodes.add(new PointF(2537, 1793));//51
        nodes.add(new PointF(2643, 1980));//52
        nodes.add(new PointF(2681, 1784));//53
        nodes.add(new PointF(2525, 1630));//54
        nodes.add(new PointF(2800, 1791));//55
        nodes.add(new PointF(2908, 1974));//56
        nodes.add(new PointF(2833, 1624));//57
        nodes.add(new PointF(2976, 1665));//58
        nodes.add(new PointF(3074, 1775));//59
        nodes.add(new PointF(3116, 1621));//60
        nodes.add(new PointF(3108, 1978));//61

        nodes.add(new PointF(3366, 1765));//62
        nodes.add(new PointF(3431, 1975));//63
        nodes.add(new PointF(3245, 1953));//64
        nodes.add(new PointF(3404, 1585));//65
        nodes.add(new PointF(3726, 1955));//66
        nodes.add(new PointF(3955, 1921));//67
        nodes.add(new PointF(4111, 1956));//68
        nodes.add(new PointF(4058, 1766));//69
        nodes.add(new PointF(3970, 1588));//70
        nodes.add(new PointF(3697, 1569));//71
        nodes.add(new PointF(3982, 1394));//72
        nodes.add(new PointF(3375, 1393));//73
        nodes.add(new PointF(3766, 1121));//74
        nodes.add(new PointF(4019, 1084));//75
        nodes.add(new PointF(3375, 1087));//76
        nodes.add(new PointF(3616, 696));//77
        nodes.add(new PointF(3314, 266));//78
        nodes.add(new PointF(3519, 326));//79
        nodes.add(new PointF(3663, 255));//80
        nodes.add(new PointF(3690, 389));//81
        nodes.add(new PointF(3875, 301));//82

        nodes.add(new PointF(4669, 1777));//83
        nodes.add(new PointF(4674, 1879));//84
        nodes.add(new PointF(4488, 1983));//85
        nodes.add(new PointF(4843, 1946));//86
        nodes.add(new PointF(5368, 1789));//87
        nodes.add(new PointF(5443, 1600));//88
        nodes.add(new PointF(5461, 1950));//89
        nodes.add(new PointF(5699, 1985));//90
        nodes.add(new PointF(5440, 2198));//91
        nodes.add(new PointF(5381, 2376));//92
        nodes.add(new PointF(6958, 2186));//93

        nodes.add(new PointF(7040, 2358));//94
        nodes.add(new PointF(6955, 2038));//95
        nodes.add(new PointF(6746, 2048));//96
        nodes.add(new PointF(6960, 1005));//97
        nodes.add(new PointF(6759, 1065));//98
        nodes.add(new PointF(7179, 2202));//99



        return nodes;
    }

    public static List<PointF> getNodesContactList() {
        List<PointF> nodesContact = new ArrayList<PointF>();
        nodesContact.add(new PointF(0, 1));
        nodesContact.add(new PointF(1, 2));
        nodesContact.add(new PointF(2, 3));
        nodesContact.add(new PointF(2, 4));
        nodesContact.add(new PointF(4, 7));
        nodesContact.add(new PointF(2, 5));
        nodesContact.add(new PointF(5, 6));
        nodesContact.add(new PointF(5, 8));
        nodesContact.add(new PointF(8, 11));
        nodesContact.add(new PointF(8, 9));
        nodesContact.add(new PointF(9, 10));
        nodesContact.add(new PointF(9, 12));
        nodesContact.add(new PointF(12, 13));
        nodesContact.add(new PointF(13, 14));
        nodesContact.add(new PointF(12, 15));
        nodesContact.add(new PointF(12, 16));
        nodesContact.add(new PointF(16, 17));
        nodesContact.add(new PointF(17, 18));
        nodesContact.add(new PointF(17, 19));
        nodesContact.add(new PointF(17, 20));
        nodesContact.add(new PointF(20, 21));
        nodesContact.add(new PointF(21, 22));

        nodesContact.add(new PointF(20, 23));
        nodesContact.add(new PointF(23, 24));
        nodesContact.add(new PointF(23, 25));
        nodesContact.add(new PointF(25, 26));
        nodesContact.add(new PointF(25, 27));
        nodesContact.add(new PointF(27, 28));
        nodesContact.add(new PointF(27, 29));
        nodesContact.add(new PointF(27, 30));
        nodesContact.add(new PointF(30, 31));
        nodesContact.add(new PointF(30, 32));
        nodesContact.add(new PointF(32, 33));
        nodesContact.add(new PointF(32, 34));
        nodesContact.add(new PointF(34, 35));
        nodesContact.add(new PointF(35, 36));
        nodesContact.add(new PointF(34, 37));
        nodesContact.add(new PointF(35, 38));
        nodesContact.add(new PointF(34, 39));
        nodesContact.add(new PointF(39, 40));
        nodesContact.add(new PointF(40, 41));
        nodesContact.add(new PointF(40, 42));
        nodesContact.add(new PointF(42, 43));
        nodesContact.add(new PointF(43, 44));
        nodesContact.add(new PointF(42, 45));
        nodesContact.add(new PointF(45, 46));
        nodesContact.add(new PointF(45, 47));
        nodesContact.add(new PointF(45, 48));
        nodesContact.add(new PointF(48, 49));
        nodesContact.add(new PointF(48, 50));
        nodesContact.add(new PointF(45, 51));
        nodesContact.add(new PointF(51, 52));
        nodesContact.add(new PointF(51, 53));
        nodesContact.add(new PointF(53, 54));
        nodesContact.add(new PointF(53, 55));
        nodesContact.add(new PointF(55, 56));
        nodesContact.add(new PointF(55, 57));
        nodesContact.add(new PointF(57, 58));
        nodesContact.add(new PointF(55, 59));
        nodesContact.add(new PointF(59, 60));
        nodesContact.add(new PointF(59, 61));

        nodesContact.add(new PointF(59, 62));
        nodesContact.add(new PointF(62, 63));
        nodesContact.add(new PointF(63, 64));
        nodesContact.add(new PointF(63, 66));
        //nodesContact.add(new PointF(66, 67));
        nodesContact.add(new PointF(67, 68));
        nodesContact.add(new PointF(68, 69));
        nodesContact.add(new PointF(69, 70));
        nodesContact.add(new PointF(62, 69));
        nodesContact.add(new PointF(62, 65));
        nodesContact.add(new PointF(65, 71));
        nodesContact.add(new PointF(71, 70));
        nodesContact.add(new PointF(65, 73));
        nodesContact.add(new PointF(70, 72));
        nodesContact.add(new PointF(71, 74));
        nodesContact.add(new PointF(74, 75));
        nodesContact.add(new PointF(74, 76));
        nodesContact.add(new PointF(74, 77));
        nodesContact.add(new PointF(77, 78));
        nodesContact.add(new PointF(78, 79));
        nodesContact.add(new PointF(79, 81));
        nodesContact.add(new PointF(81, 80));
        nodesContact.add(new PointF(81, 82));

        nodesContact.add(new PointF(69, 83));
        nodesContact.add(new PointF(83, 84));
        nodesContact.add(new PointF(84, 85));
        nodesContact.add(new PointF(84, 86));
        nodesContact.add(new PointF(83, 87));
        nodesContact.add(new PointF(87, 88));
        nodesContact.add(new PointF(87, 89));
        nodesContact.add(new PointF(89, 90));
        nodesContact.add(new PointF(89, 91));
        nodesContact.add(new PointF(91, 92));
        nodesContact.add(new PointF(91, 93));

        nodesContact.add(new PointF(93, 94));
        nodesContact.add(new PointF(93, 95));
        nodesContact.add(new PointF(95, 96));
        nodesContact.add(new PointF(95, 97));
        nodesContact.add(new PointF(97, 98));
        nodesContact.add(new PointF(93, 99));

        return nodesContact;
    }
}
