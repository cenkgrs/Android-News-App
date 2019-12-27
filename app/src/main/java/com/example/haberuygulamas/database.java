package com.example.haberuygulamas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class database extends SQLiteOpenHelper {

    public database(Context c){
        super(c, "haberler", null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        String sql =  "create table haberler ( " +
                "news_id integer primary key autoincrement" +
                ", news_header text " +
                ", news_description text " +
                ", news_source text " +
                ", news_date date)";

        db.execSQL(sql);
    }

    public ArrayList<HashMap<String, String>> haberler(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM haberler";
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<HashMap<String, String>> haberList = new ArrayList<HashMap<String, String>>();

        if(cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<cursor.getColumnCount(); i++)
                {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                haberList.add(map);
            } while (cursor.moveToNext());
        }
        db.close();

        return haberList;
    }

    public void onUpgrade(SQLiteDatabase db, int old, int yeni){

    }
}
