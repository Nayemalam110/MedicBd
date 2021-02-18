package com.example.doctorhere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class HDoc extends AppCompatActivity {

    ListView listView;
    ArrayList<CatClass> arrayList;
    DatabaseHelper myDbHelper;
    int id;
    CatAdapter catAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_doc);

        listView=findViewById(R.id.probid);
        arrayList=new ArrayList<>();

        loaddata();

    }
    private void loaddata(){

        myDbHelper = new DatabaseHelper(HDoc.this);
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

        Cursor c = myDbHelper.displayallcat();
        if (c.getCount()==0)
        {

            Toast.makeText(getApplicationContext(),"No data",Toast.LENGTH_LONG).show();

        }
        else {

            while (c.moveToNext())
            {
                 id= c.getInt(0);
                 String name =c.getString(1);

                CatClass catClass =new CatClass(id,name);
                arrayList.add(catClass);

            }


        }

        catAdapter = new CatAdapter(this,arrayList);
        listView.setAdapter(catAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int aid=arrayList.get(position).getId();
                Intent intent = new Intent(HDoc.this,HDocAct.class);
                intent.putExtra("aid",aid);

                startActivity(intent);

            }
        });


        //adapter =new ArrayAdapter<String>(SpinnerClass.this,R.layout.list_item,R.id.listitemid,listData);
    /*    medicinelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int brand_id = medicinelistdata.get(i).getBrandid();
                //Toast.makeText(getApplicationContext(),"Brandid"+brand_id,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MedAct.this,MedicineDetailsClass.class);
                intent.putExtra("position",i);
                intent.putExtra("brand_id",brand_id);
                startActivity(intent);

            }
        });*/


    }
}