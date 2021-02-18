package com.example.doctorhere;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.io.IOException;

public class HomDocDetails extends AppCompatActivity {

    int id;
    TextView name,intro,casus,sym,treat;
    DatabaseHelper myDbHelper;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hom_doc_details);

        name=findViewById(R.id.prnameid);
        intro=findViewById(R.id.introid);
        sym=findViewById(R.id.symid);
        casus=findViewById(R.id.causesid);
        treat=findViewById(R.id.treatid);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("aid");
        }


        loaddetails();
    }

    private void loaddetails() {


        myDbHelper = new DatabaseHelper(HomDocDetails.this);
  /*      try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
*/

        c = myDbHelper.displayallprobdetails(id);

        if (c.getCount() == 0) {

            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();

        } else {

            while (c.moveToNext()) {



                String name1=c.getString(2);
                String intro1=c.getString(3);
                String cay1=c.getString(4);
                String sym1=c.getString(7);
                String treat1=c.getString(8);
                name.setText(name1);
                intro.setText(intro1);
                casus.setText(cay1);
                sym.setText(sym1);
                treat.setText(treat1);



            }

        }


    }
}
