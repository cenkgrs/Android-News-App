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

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public ArrayList<news_item> getQuotes(String newspaper) {
        System.out.println("Got here");
        ArrayList<news_item> list = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM news WHERE source == 'Guardian'" , null);
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
            System.out.println("title"+title);
            System.out.println("title"+image);
            System.out.println("title"+url);
            item = new news_item();
            item.setTitle(title);
            item.setCategory(category);
            item.setImage(image);
            item.setUrl(url);
            item.setDescription(description);
            item.setAuthor(author);
            item.setTime(time);
            item.setContent(content);
            System.out.println("İtem bilgileri"+ item);
            list.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}