
package com.example.doctorhere;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public class HosDetailsClass extends AppCompatActivity {
    ArrayList<HosClass> Hosdatailsdata;
    int i,areaid,disvalue;
    TextView hosname,hoscata, hosphone, hoslocation;
    Cursor c;
    ImageView imageView;
    private String numbers;
    private String []strArray;

    DatabaseHelper myDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hos_details_class);
        hosname =findViewById(R.id.hosnameid);
        hosphone =findViewById(R.id.hosphoneid);
        hoslocation =findViewById(R.id.hoslocationid);
        hoscata =findViewById(R.id.hoscataid);
        imageView=findViewById(R.id.ambimg);


        Hosdatailsdata = new ArrayList<>();
        //Toast.makeText(getApplicationContext(),"Hosdetails is working",Toast.LENGTH_LONG).show();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            i = bundle.getInt("Position");
            areaid=bundle.getInt("areaid");
            disvalue=bundle.getInt("disvalue");

        }
        //Toast.makeText(getApplicationContext(),"Position"+i +"area id "+areaid,Toast.LENGTH_LONG).show();

         loadetails();
    }

   private void loadetails() {


        myDbHelper = new DatabaseHelper(HosDetailsClass.this);
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
        if(areaid==0){

            c = myDbHelper.displayallHospitalData(disvalue);
        }
        else {

            //Toast.makeText(getApplicationContext(), "Come on", Toast.LENGTH_LONG).show();

           c = myDbHelper.displayHospitalData(areaid);
        }
        if (c.getCount() == 0) {

            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();

        } else {

            while (c.moveToNext()) {

                int id = c.getInt(0);
                String name = c.getString(1);
                String type = c.getString(2);
                String sepcialty = c.getString(3);
                String phone = c.getString(4);
                String address = c.getString(5);
                String web = c.getString(6);
                HosClass hosclass = new HosClass(id, name, type, sepcialty, phone, address, web);
                Hosdatailsdata.add(hosclass);

            }


        }

        String k = Hosdatailsdata.get(i).getName();
        hosname.setText(k);

        hoslocation.setText(Hosdatailsdata.get(i).getAddress());
        hosphone.setText(Hosdatailsdata.get(i).getPhone());
        hoscata.setText(Hosdatailsdata.get(i).getSepcialty());

       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //Toast.makeText(getApplicationContext(),"Phone",Toast.LENGTH_LONG).show();
               numbers=null;
               numbers=Hosdatailsdata.get(i).getPhone();

               strArray=numbers.split(",");
               AlertDialog.Builder builder = new AlertDialog.Builder(HosDetailsClass.this);
               builder.setTitle("Choose a number");

               builder.setItems(strArray, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dial(strArray,which);
                   }
               });

// create and show the alert dialog
               AlertDialog dialog = builder.create();
               dialog.show();
           }
       });


   }
    private void dial(String[] strArray, int which) {

        String dial = "tel:" + strArray[which];
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
    }
}
