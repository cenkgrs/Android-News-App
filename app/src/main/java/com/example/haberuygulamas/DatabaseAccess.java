package com.example.haberuygulamas;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }


    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public ArrayList<news_item> getNews(String newspaper, String cat) {
        ArrayList<news_item> list = new ArrayList<>();
        String sql = "SELECT * FROM news WHERE category ='"+cat+"' and source = '"+newspaper+"'";
        Cursor cursor = database.rawQuery(sql , null);
        cursor.moveToFirst();
        news_item item;
        while (!cursor.isAfterLast()) {
            Integer id = cursor.getInt(0);
            String category = cursor.getString(1);
            String title = cursor.getString(2);
            String image = cursor.getString(8);
            String description = cursor.getString(3);
            String url = cursor.getString(9);
            String source = cursor.getString(5);
            String author = cursor.getString(6);
            String time = cursor.getString(7);
            String content = cursor.getString(4);

            item = new news_item();
            item.setTitle(title);
            item.setCategory(category);
            item.setImage(image);
            item.setUrl(url);
            item.setDescription(description);
            item.setAuthor(author);
            item.setTime(time);
            item.setContent(content);

            list.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}