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

public class BloAct extends AppCompatActivity implements View.OnClickListener {
    Spinner spinnerdis,spinnerarea;
    Button buttonblosearch;
    ListView blolistview;

    DatabaseHelper myDbHelper;
    DisAdaptar disAdaptar;
    int disid,areaid;
    ArrayList<DisClass> dislist;
    ArrayList<BloClass> blolistdata;
    ArrayList<AreaClass> ArealistData;
    AreaAdapter areaAdapter;

    BloAdapter bloAdapter;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blo);


        spinnerdis=findViewById(R.id.disspineerid);
        spinnerarea=findViewById(R.id.arspinnerid);
        buttonblosearch =findViewById(R.id.blosearchbuttonbid);
        blolistview =findViewById(R.id.blolistviewid);

        buttonblosearch.setOnClickListener(this);
        dislist = new ArrayList<>();
        blolistdata =new ArrayList<>();
        // adapter=new ArrayAdapter<String>();
        loadDis();

    }
    private void loadDis() {

        myDbHelper = new DatabaseHelper(BloAct.this);
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

        Cursor cursor = myDbHelper.displayblodisData();
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
                //Toast.makeText(getApplicationContext(),"Data:"+ disid,Toast.LENGTH_LONG).show();
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

        Cursor c = myDbHelper.displaybloareaData(disid);
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


               blolistdata.clear();

            if(areaid==0)
            {
                //Toast.makeText(getApplicationContext(),"You Clicked ANY",Toast.LENGTH_LONG).show();

                ArrayList<String> listData = new ArrayList<>();
                myDbHelper = new DatabaseHelper(BloAct.this);
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

                Cursor c = myDbHelper.displayallblodisData(disid);
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
                        String phone= c.getString(2);
                        String address= c.getString(3);
                        // String web= c.getString(6);
                        BloClass bloclass= new BloClass(id,name,phone,address);
                        blolistdata.add(bloclass);

                    }


                }


                bloAdapter =new BloAdapter(this, blolistdata);
                blolistview.setAdapter(bloAdapter);
                blolistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        Intent intent = new Intent(BloAct.this,BloDetailsClass.class);
                        intent.putExtra("Position",i );
                        intent.putExtra("disvalue",disid);
                        startActivity(intent);

                    }
                });




            }
            else


            {


                myDbHelper = new DatabaseHelper(BloAct.this);
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

                Cursor c = myDbHelper.displayallbloareaData(areaid);
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
                        String phone= c.getString(2);
                        String address= c.getString(3);
                        // String web= c.getString(6);
                        BloClass bloclass= new BloClass(id,name,phone,address);
                        blolistdata.add(bloclass);

                    }


                }


                bloAdapter =new BloAdapter(this, blolistdata);
                //adapter =new ArrayAdapter<String>(SpinnerClass.this,R.layout.list_item,R.id.listitemid,listData);
                blolistview.setAdapter(bloAdapter);
                blolistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        // Toast.makeText(getApplicationContext(),"Select value : " +name1,Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(BloAct.this,BloDetailsClass.class);

                        intent.putExtra("Position",i );
                        intent.putExtra("areaid",areaid);
                        startActivity(intent);
                    }
                });



            }
        }

    }
}