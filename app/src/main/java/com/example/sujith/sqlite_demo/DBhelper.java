package com.example.sujith.sqlite_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="MyData.db";
    public static final String TABLE_NAME="Data";
    public static final String NAME="Name";
    public static final String NUMBER="Contacts";

    public DBhelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("create table Data(Name text primary key,Contacts text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("Drop table if exists Data");
        onCreate(db);
        Log.e("innn","Table Exist");

    }

    public boolean insertdata(String name,String numb)
    {
        SQLiteDatabase sq=this.getWritableDatabase();

        ContentValues cv=new ContentValues();

        cv.put("Name",name);
        cv.put("Contacts",numb);

        sq.insert(TABLE_NAME,null,cv);

        return true;
    }

    public ArrayList<String> getAllNames()
    {
        ArrayList<String> arr_n=new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor ca=db.rawQuery("select *from Data",null);
        ca.moveToFirst();

        while (ca.isAfterLast()==false)
        {
            arr_n.add(ca.getString(ca.getColumnIndex("Name")));
            ca.moveToNext();
        }

        return arr_n;
    }

    public boolean updatedata(String name,String num,String n)
    {

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("Name",name);
        cv.put("Contacts",num);

        db.update("Data",cv,"Name=?",new String[]{n});
        return true;
    }

    public Integer deleteContact(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        return db.delete("Data","Name=?",new String[]{name});
    }

    public ArrayList<String> getAllNumber()
    {
        ArrayList<String> arr_num=new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor ca=db.rawQuery("select *from Data",null);
        ca.moveToFirst();

        while (ca.isAfterLast()==false)
        {
            arr_num.add(ca.getString(ca.getColumnIndex("Contacts")));
            ca.moveToNext();
        }


        return arr_num;
    }

    public Cursor getData(String s)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor r=db.rawQuery("select *from Data where Name='"+s+"'",null);

        return r;
    }


}
