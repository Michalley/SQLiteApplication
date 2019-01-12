package com.example.michal.sqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import static com.example.michal.sqliteapplication.Name.FIRST_NAME;
import static com.example.michal.sqliteapplication.Name.KEY_IDN;
import static com.example.michal.sqliteapplication.Name.LAST_NAME;
import static com.example.michal.sqliteapplication.Name.TABLE_NAME;
import static com.example.michal.sqliteapplication.PersoInfo.ENGLISH_GRADE;
import static com.example.michal.sqliteapplication.PersoInfo.IDENTITY;
import static com.example.michal.sqliteapplication.PersoInfo.KEY_IDPI;
import static com.example.michal.sqliteapplication.PersoInfo.MATH_GRADE;
import static com.example.michal.sqliteapplication.PersoInfo.TABLE_PERSOINFO;

public class HelperDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 1;
    String strCreate, strDelete;

    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);}
    @Override
    public void onCreate(SQLiteDatabase db) {

        strCreate="CREATE TABLE "+TABLE_NAME;
        strCreate+=" ("+KEY_IDN+" INTEGER PRIMARY KEY,";
        strCreate+=" "+FIRST_NAME+" TEXT,";
        strCreate+=" "+LAST_NAME+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);


        strCreate="CREATE TABLE "+TABLE_PERSOINFO;
        strCreate+=" ("+KEY_IDPI+"INTEGER PRIMARY KEY,";
        strCreate+=""+IDENTITY+" TEXT,";
        strCreate+=""+MATH_GRADE+" TEXT,";
        strCreate+=""+ENGLISH_GRADE+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        strDelete="DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(strDelete);

        strDelete="DROP TABLE IF EXISTS "+TABLE_PERSOINFO;
        db.execSQL(strDelete);

        onCreate(db);
    }
    public boolean insertData1 (String firstName, String lastName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME,firstName);
        contentValues.put(LAST_NAME,lastName);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean insertData2 (String ID, String mathGrade, String englishGrade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDENTITY,ID);
        contentValues.put(MATH_GRADE,mathGrade);
        contentValues.put(ENGLISH_GRADE,englishGrade);
        long result = db.insert(TABLE_PERSOINFO,null,contentValues);

        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }
}
