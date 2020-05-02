package com.onlylemi.mapview;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private List<Integer> list_id;
    private List<Integer> list_type;
    private List<PointF> list_pos;
    private List<String> list_name;
    private  List<String> list_desctription;
    private  List<PointF> list_nodes;
    private List<PointF> list_contacts;

    public Floor(){
        list_id = new ArrayList<>();
        list_type = new ArrayList<>();
        list_pos = new ArrayList<>();
        list_name = new ArrayList<>();
        list_desctription = new ArrayList<>();
        list_nodes = new ArrayList<>();
        list_contacts = new ArrayList<>();
    }

    public List<Integer> getListId() {
        return list_id;
    }

    public List<Integer> getListType() {
        return list_type;
    }

    public List<PointF> getListPos() {
        return list_pos;
    }

    public List<String> getListName() {
        return list_name;
    }

    public List<String> getListDesctription() {
        return list_desctription;
    }

    public List<PointF> getListNodes() { return list_nodes; }

    public List<PointF> getListContacts() { return list_contacts; }
}
