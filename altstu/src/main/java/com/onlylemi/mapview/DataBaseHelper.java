package com.onlylemi.mapview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.PointF;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.onlylemi.mapview/databases/";
    private static String DB_NAME = "altstudb";
    private SQLiteDatabase myDataBase;
    private final Context mContext;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }
    private boolean checkDataBase(){
        File db = new File(DB_PATH + DB_NAME); //Get the file name of the database
        if (db.exists()) return true; // If it exists then return doing nothing
        // Get the parent (directory in which the database file would be)
        File dbdir = db.getParentFile();
        // If the directory does not exits then make the directory (and higher level directories)
        if (!dbdir.exists()) {
            db.getParentFile().mkdirs();
            dbdir.mkdirs();
        }
        return false;
    }

    private void copyDataBase() throws IOException{
        //Open your local db as the input stream
        InputStream myInput = mContext.getAssets().open(DB_NAME);
        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;
        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        return myDataBase;
    }

    public Cursor getTablecontents(String table) {
        String q = "SELECT * FROM " + table;
        Cursor mCursor = myDataBase.rawQuery(q, null);
        return mCursor;
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Floor getDataFloor(String building, String floor){

        Floor floor_data = new Floor();
        String q = "SELECT id FROM building WHERE name = '" + building + "'";
        Cursor c = myDataBase.rawQuery(q, null);
        c.moveToFirst();
        int id_building = c.getInt(0);
        c.close();

        q = "SELECT id FROM floor WHERE id_building = '" + id_building + "' AND name = '" + floor  + "'";
        c = myDataBase.rawQuery(q, null);
        c.moveToFirst();
        int id_floor = c.getInt(0);
        c.close();

        q = "SELECT * FROM object_on_floor WHERE id_floor = '" + id_floor + "' ORDER BY id";
        c = myDataBase.rawQuery(q, null);
        int idInd = c.getColumnIndex("id");
        int typeInd = c.getColumnIndex("id_type_object");
        int nameInd = c.getColumnIndex("name");
        int posxInd = c.getColumnIndex("pos_x");
        int posyInd = c.getColumnIndex("pos_y");
        int descInd = c.getColumnIndex("description");
        c.moveToFirst();
        do {
            floor_data.getListId().add(c.getInt(idInd));
            floor_data.getListType().add(c.getInt(typeInd));
            floor_data.getListName().add(c.getString(nameInd));
            floor_data.getListPos().add(new PointF(c.getFloat(posxInd),c.getFloat(posyInd)));
            if(c.getString(descInd) != null)
                floor_data.getListDesctription().add(c.getString(descInd));
            else
                floor_data.getListDesctription().add("");
        } while (c.moveToNext());
        c.close();

        q = "SELECT pos_x,pos_y FROM node WHERE id_floor = '" + id_floor + "' ORDER BY CAST(number AS INTEGER)";
        c = myDataBase.rawQuery(q, null);
        c.moveToFirst();
        do {
            floor_data.getListNodes().add(new PointF(c.getFloat(0),c.getFloat(1)));
        } while (c.moveToNext());
        c.close();

        q = "SELECT number_from,number_to FROM contact WHERE id_floor = '" + id_floor + "'";
        c = myDataBase.rawQuery(q, null);
        c.moveToFirst();
        do {
            floor_data.getListContacts().add(new PointF(c.getFloat(0),c.getFloat(1)));
        } while (c.moveToNext());
        c.close();

        return floor_data;
    }

    public List<String> getMarksName(String building, String floor){
        List<String> names = new ArrayList<>();
        String q = "SELECT id FROM building WHERE name = '" + building + "'";
        Cursor c = myDataBase.rawQuery(q, null);
        c.moveToFirst();
        int id_building = c.getInt(0);
        c.close();

        q = "SELECT id FROM floor WHERE id_building = '" + id_building + "' AND name = '" + floor  + "'";
        c = myDataBase.rawQuery(q, null);
        c.moveToFirst();
        int id_floor = c.getInt(0);
        c.close();

        q = "SELECT name FROM object_on_floor WHERE id_floor = '" + id_floor + "' ORDER BY id";
        c = myDataBase.rawQuery(q, null);
        c.moveToFirst();
        do {
            names.add(c.getString(0));
        } while (c.moveToNext());
        c.close();

        return names;
    }

    public List<String> getAllTypes(){
        List<String> types = new ArrayList<>();
        String q = "SELECT name FROM type_object ORDER BY id";
        Cursor c = myDataBase.rawQuery(q, null);
        c.moveToFirst();
        do {
            types.add(c.getString(0));
        } while (c.moveToNext());
        return types;
    }

    public void updateObject(int id, int id_type_object, String name, String desc){
        ContentValues cv = new ContentValues();
        cv.put("id_type_object", id_type_object);
        cv.put("name", name);
        cv.put("description", desc);
        myDataBase.update("object_on_floor", cv, "id = ?", new String[] {String.valueOf(id)});
    }

}
