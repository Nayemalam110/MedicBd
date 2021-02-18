package com.example.doctorhere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class HDocAct extends AppCompatActivity {

    Cursor c;
    ListView listView;
    DatabaseHelper myDbHelper;
    int aid;
    int id;
    HdocAdapter hdocAdapter;
    ArrayList<HdocClass>arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_doc2);
        listView=findViewById(R.id.prob2id);

        arrayList=new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            aid = bundle.getInt("aid");


        }
        //Toast.makeText(getApplicationContext(),"Problemid  == "+aid,Toast.LENGTH_LONG).show();
        loadproblem();
    }
    private void loadproblem(){


        myDbHelper = new DatabaseHelper(HDocAct.this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }


            c = myDbHelper.displayallprob(aid);

        if (c.getCount() == 0) {

            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();

        } else {

            while (c.moveToNext()) {

                 id=c.getInt(0);
                 int catid=c.getInt(1);
                 String name=c.getString(2);
                 String intro=c.getString(3);
                String cay=c.getString(4);
                String sym=c.getString(7);
                String treat=c.getString(8);


            HdocClass hdocClass = new HdocClass(id,catid,name,intro,cay,sym,treat);
            arrayList.add(hdocClass);

            }
           hdocAdapter=new HdocAdapter(this,arrayList);
            listView.setAdapter(hdocAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int aid=arrayList.get(position).getId();
                    Intent intent = new Intent(HDocAct.this,HomDocDetails.class);
                    intent.putExtra("aid",aid);

                    startActivity(intent);

                }
            });

        }


    }
}