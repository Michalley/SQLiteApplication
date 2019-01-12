package com.example.michal.sqliteapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.michal.sqliteapplication.Name.TABLE_NAME;
import static com.example.michal.sqliteapplication.PersoInfo.TABLE_PERSOINFO;

public class SecondActivity extends AppCompatActivity {

    ArrayList <String> Data1 = new ArrayList<>();
    ArrayList <String> Data2 = new ArrayList<>();

    ListView lv;
    Switch sw;

    SQLiteDatabase db;
    HelperDB hdb;
    ArrayAdapter adp;
    Cursor c1,c2;

    int col11,col12,col13,col21,col22,col23,col24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = (ListView) findViewById(R.id.lv);
        sw = (Switch) findViewById(R.id.sw);

        hdb = new HelperDB(this);
        db = hdb.getReadableDatabase();

        c1 = db.query(Name.TABLE_NAME,null,null,null,null,null,null);
        c2 = db.query(TABLE_PERSOINFO,null,null,null,null,null,null);

        col11 = c1.getColumnIndex("_id");
        col12 = c1.getColumnIndex("FirstName");
        col13 = c1.getColumnIndex("LastName");

        col21 = c2.getColumnIndex("_id");
        col22 = c2.getColumnIndex("ID");
        col23 = c2.getColumnIndex("MathGrade");
        col24 = c2.getColumnIndex("EnglishGrade");

        c1.moveToFirst();
        while (!c1.isAfterLast()){
            String fname = c1.getString(col12);
            String lname = c1.getString(col13);
            String temp = fname+","+lname;
            Data1.add(temp);
            c1.moveToNext();
        }
        c2.moveToFirst();
        while (!c2.isAfterLast()){
            String id = c2.getString(col22);
            String mgrade = c2.getString(col23);
            String egrade = c2.getString(col24);
            String temp = id+","+mgrade+","+egrade;
            Data2.add(temp);
            c2.moveToNext();
        }
        adp = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Data1);
        lv.setAdapter(adp);
    }

    public void go(View view) {
        if (!sw.isChecked()){
            adp = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Data1);
            lv.setAdapter(adp);
        }
        else{
            adp = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Data2);
            lv.setAdapter(adp);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Back");
        menu.add("Credits");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String st=item.getTitle().toString();
        if (st.equals("Back")){
            Intent t = new Intent(this,MainActivity.class);
            startActivity(t);
        }
        if (st.equals("Credits")){
            Toast.makeText(this,"Application Of Michal Leybovich",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
