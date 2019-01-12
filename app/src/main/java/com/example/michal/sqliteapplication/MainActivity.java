package com.example.michal.sqliteapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    HelperDB hlp;

    EditText etf,etl,etid,etmg,eteg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hlp = new HelperDB(this);

        etf = (EditText) findViewById(R.id.etf);
        etl = (EditText) findViewById(R.id.etl);
        etid = (EditText) findViewById(R.id.etid);
        etmg = (EditText) findViewById(R.id.etmg);
        eteg = (EditText) findViewById(R.id.eteg);
    }

    public void Enterf(View view) {
        db = hlp.getWritableDatabase();
        String stf,stl;
        stf = etf.getText().toString();
        stl = etl.getText().toString();
        boolean tof1 = hlp.insertData1(stf,stl);
        if (tof1){
            etf.setText("");
            etl.setText("");
        }
        db.close();
    }

    public void Enters(View view) {
        db = hlp.getWritableDatabase();
        String stid,stmg,steg;
        stid = etid.getText().toString();
        stmg = etmg.getText().toString();
        steg = eteg.getText().toString();
        boolean tof2 = hlp.insertData2(stid,stmg,steg);
        if (tof2){
            etid.setText("");
            etmg.setText("");
            eteg.setText("");
        }
        db.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Next");
        menu.add("Credits");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String st=item.getTitle().toString();
        if (st.equals("Next")){
            Intent t = new Intent(this,SecondActivity.class);
            startActivity(t);
        }
        if (st.equals("Credits")){
            Toast.makeText(this,"Application Of Michal Leybovich",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
