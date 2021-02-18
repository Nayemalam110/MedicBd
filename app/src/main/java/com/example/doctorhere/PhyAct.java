package com.example.doctorhere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class PhyAct extends AppCompatActivity implements View.OnClickListener {

    Spinner spinnerdis,spinnerarea;
    Button buttonphysearch;
    ListView phylistview;

    DatabaseHelper myDbHelper;
    DisAdaptar disAdaptar;
    int disid,areaid;
    ArrayList<DisClass> dislist;
    ArrayList<PhyClass> phylistdata;
    ArrayList<AreaClass> ArealistData;
    AreaAdapter areaAdapter;

    PhyAdapter phyAdapter;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phy);

        spinnerdis=findViewById(R.id.disspineerid);
        spinnerarea=findViewById(R.id.arspinnerid);
        buttonphysearch =findViewById(R.id.drugstoresearchbuttonbid);
        phylistview =findViewById(R.id.phylistviewid);

        buttonphysearch.setOnClickListener(this);
        dislist = new ArrayList<>();
        phylistdata =new ArrayList<>();
        // adapter=new ArrayAdapter<String>();
        loadDis();

    }
    private void loadDis() {

        myDbHelper = new DatabaseHelper(PhyAct.this);
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

        Cursor cursor = myDbHelper.displayphydisData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();

        } else {
            while (cursor.moveToNext()) {

                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                DisClass disClass = new DisClass(id, name);
                dislist.add(disClass);

            }

        }
        //District spinner set

        disAdaptar = new DisAdaptar(this, dislist);
        spinnerdis.setAdapter(disAdaptar);
        spinnerdis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                disid= dislist.get(i).getId();
               // Toast.makeText(getApplicationContext(),"Data:"+ disid,Toast.LENGTH_LONG).show();
                loadArea();
            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
    private void loadArea() {
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
        ArealistData = new ArrayList<>();

        Cursor c = myDbHelper.displayphyareaData(disid);
        if (c.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"2nd time no data",Toast.LENGTH_LONG).show();

        }
        else {
            //Toast.makeText(getApplicationContext(),"2nd spine is working",Toast.LENGTH_LONG).show();

            int x=0;
            int y=0;
            String ar ="ANY";
            AreaClass areaClass = new AreaClass(x,y,ar);
            ArealistData.add(areaClass);
            while (c.moveToNext())
            {

                int id= c.getInt(0);
                int dis_id=c.getInt(1);
                String name = c.getString(2);
                AreaClass areaClass1 = new AreaClass(id,dis_id,name);
                ArealistData.add(areaClass1);

            }

        }
        // 2nd spinner set
        areaAdapter = new AreaAdapter(this,ArealistData);
        spinnerarea.setAdapter(areaAdapter);
        spinnerarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                areaid=ArealistData.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        {


            phylistdata.clear();

            if(areaid==0)
            {
                //Toast.makeText(getApplicationContext(),"You Clicked ANY",Toast.LENGTH_LONG).show();

                ArrayList<String> listData = new ArrayList<>();
                myDbHelper = new DatabaseHelper(PhyAct.this);
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

                Cursor c = myDbHelper.displayallphydatabydis(disid);
                if (c.getCount()==0)
                {

                    Toast.makeText(getApplicationContext(),"No data",Toast.LENGTH_LONG).show();

                }
                else {

                    while (c.moveToNext())
                    {
                        int id= c.getInt(0);
                        String name =c.getString(1);
                        // String type= c.getString(2);
                        // String sepcialty= c.getString(3);
                        String phone= c.getString(3);
                        String address= c.getString(4);
                        // String web= c.getString(6);
                        PhyClass bloclass= new PhyClass(id,name,phone,address);
                        phylistdata.add(bloclass);

                    }


                }


                phyAdapter =new PhyAdapter(this, phylistdata);
                phylistview.setAdapter(phyAdapter);
                phylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                      Intent intent = new Intent(PhyAct.this,PhyDetailsClass.class);
                        intent.putExtra("Position",i );
                        intent.putExtra("disvalue",disid);
                        startActivity(intent);

                    }
                });




            }
            else


            {


                myDbHelper = new DatabaseHelper(PhyAct.this);
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

                Cursor c = myDbHelper.displayallphydatabyarea(areaid);
                if (c.getCount()==0)
                {

                    Toast.makeText(getApplicationContext(),"No data",Toast.LENGTH_LONG).show();

                }
                else {

                    while (c.moveToNext())
                    {

                        int id= c.getInt(0);
                        String name =c.getString(1);
                        // String type= c.getString(2);
                        // String sepcialty= c.getString(3);
                        String phone= c.getString(3);
                        String address= c.getString(4);
                        // String web= c.getString(6);
                        PhyClass bloclass= new PhyClass(id,name,phone,address);
                        phylistdata.add(bloclass);

                    }


                }


                phyAdapter =new PhyAdapter(this, phylistdata);
                phylistview.setAdapter(phyAdapter);
                phylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        // Toast.makeText(getApplicationContext(),"Select value : " +name1,Toast.LENGTH_LONG).show();

                       Intent intent = new Intent(PhyAct.this,PhyDetailsClass.class);

                        intent.putExtra("Position",i );
                        intent.putExtra("areaid",areaid);
                        startActivity(intent);
                    }
                });



            }
        }

    }
}