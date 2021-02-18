package com.example.doctorhere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView hospitalbutton,bloodbankbutoon,medicinbutoon,ambulancebutton,doctorbutton,dgstorebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hospitalbutton=findViewById(R.id.hospitalbuttonid);
        bloodbankbutoon=findViewById(R.id.bloodbankbuttonid);
        medicinbutoon=findViewById(R.id.medicinebuttonid);
        ambulancebutton=findViewById(R.id.ambulancebuttonid);
        doctorbutton=findViewById(R.id.doctorbuttonid);
        dgstorebutton=findViewById(R.id.drugstorebuttonid);

        dgstorebutton.setOnClickListener(this);
        hospitalbutton.setOnClickListener(this);
        bloodbankbutoon.setOnClickListener(this);
        medicinbutoon.setOnClickListener(this);
        ambulancebutton.setOnClickListener(this);
        doctorbutton.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {


        if(view.getId()==R.id.hospitalbuttonid)
        {
            //Toast.makeText(getApplicationContext(),"Hospital Button is clicked",Toast.LENGTH_LONG).show();
            Intent intenth = new Intent(MainActivity.this, Amb.class);
            startActivity(intenth);

        }
        else if(view.getId()==R.id.ambulancebuttonid)
        {
           // Toast.makeText(getApplicationContext(),"Ambulance Button is clicked",Toast.LENGTH_LONG).show();
            Intent intenta = new Intent(MainActivity.this,AmbAct.class);
            startActivity(intenta);

        }
        else if(view.getId()==R.id.doctorbuttonid)
        {
            //Toast.makeText(getApplicationContext(),"Doctor Button is clicked",Toast.LENGTH_LONG).show();
            Intent intentd = new Intent(MainActivity.this,HDoc.class);
            startActivity(intentd);


        }
        else if(view.getId()==R.id.bloodbankbuttonid)
        {
           // Toast.makeText(getApplicationContext(),"Blood Bank Button is clicked",Toast.LENGTH_LONG).show();
            Intent intentb = new Intent(MainActivity.this,BloAct.class);
            startActivity(intentb);

        }
        else if(view.getId()==R.id.medicinebuttonid)
        {
            //Toast.makeText(getApplicationContext(),"Medicine Button is clicked",Toast.LENGTH_LONG).show();
            Intent intentm = new Intent(MainActivity.this,MedAct.class);
            startActivity(intentm);

        }
        else if(view.getId()==R.id.drugstorebuttonid)
        {
           // Toast.makeText(getApplicationContext(),"Drug Store Button is clicked",Toast.LENGTH_LONG).show();
            Intent intentm = new Intent(MainActivity.this,PhyAct.class);
            startActivity(intentm);

        }


    }
}