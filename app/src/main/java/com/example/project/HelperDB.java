package com.example.project;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class HelperDB extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "items.db"; private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ITEMS = "items";
    private static final String COL_ID = "id";
    private static final String COL_TITLE="title";
    private static final String COL_DESCRIPTION = "description"; private static final String COL_IMAGE_URI= "imageUriString";
    public HelperDB (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_ITEMS + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_TITLE + " TEXT, "
                + COL_DESCRIPTION +" TEXT, "
                + COL_IMAGE_URI +"TEXT)";

                db.execSQL(sql);
    }

@Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void addItem(Item item)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_TITLE,item.getName());
        values.put(COL_DESCRIPTION,item.getInfo());
        values.put(COL_IMAGE_URI,item.getUri());
        db.insert(TABLE_ITEMS,null,values);
        db.close();
    }
    private Item curserToItem(Cursor cursor)
    {
        return new Item(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
    }
    public ArrayList<Item> getAllItems()
    {
        ArrayList<Item>items=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT "+COL_ID+", "+COL_TITLE+","+COL_DESCRIPTION+","+COL_IMAGE_URI+"FROM"+TABLE_ITEMS,null);
    while (cursor.moveToNext())
    {
        items.add(curserToItem(cursor));
    }
    cursor.close();
        db.close();
        return items;
    }
    public void updateItem(int id,String name,String info,String uri)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(COL_TITLE,name);
        values.put(COL_DESCRIPTION,info);
        values.put(COL_IMAGE_URI,uri);

        db.update(TABLE_ITEMS,values,COL_ID+"=?",new String[]{String.valueOf(id)});
    }
    public void deleteItem(int id)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_ITEMS,COL_ID+"=?",new String[]{String.valueOf(id)});
    }
}