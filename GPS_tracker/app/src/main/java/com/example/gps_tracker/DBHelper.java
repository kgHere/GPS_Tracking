package com.example.gps_tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";
    public static final String TABLENAME = "Users";
    public DBHelper(Context context) {
        super(context,DBNAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLENAME + "( username TEXT primary key , password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");

    }
    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("username",username);
        c.put("password",password);
        long res = MyDB.insert(TABLENAME,null,c);

        if(res == -1){
            return false;
        }
        else
            return true;

    }
    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from " + TABLENAME + " where username = ? ",new String[] {username});

        if(cursor.getCount() > 0){
            return true;
        }else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery(" select * from " + TABLENAME + " where username = ? and password = ?", new String[] {username,password});

        if(cursor.getCount() > 0){
            return true;
        }else
            return false;

    }

}