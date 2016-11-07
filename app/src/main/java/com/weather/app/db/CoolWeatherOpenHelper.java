package com.weather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hasee on 2016/11/7.
 */

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
    /*
    Province 建表语句
     */
    public static final String CREATE_PROVINCE="create table Province("
            +"id integer primary key autoincrement, "
            +"province_name text, "
            +"province_code text)";

    /*
    city建表
     */
    public static final String CREATE_CITY="create table City("
            +"id integer primary key autoincrement, "
            +"city_name text, "
            +"city_code text, "
            +"province_id integer)";

    /*
    Country建表
     */
    public static final String CREATE_COUNTRY="create table Country("
            +"id integer primary key autoincrement, "
            +"country_name text, "
            +"country_code text, "
            +"city_id integer)";

    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
