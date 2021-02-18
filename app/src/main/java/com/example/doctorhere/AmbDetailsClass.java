package com.example.doctorhere;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import java.io.IOException;
import java.util.ArrayList;

public class AmbDetailsClass extends AppCompatActivity {
    ArrayList<AmbClass> amblistdata;
    int i,areaid,disvalue;
    TextView ambname, ambphone, ambadress;
    Cursor c;
    DatabaseHelper myDbHelper;
    ImageView imageView;
    private String numbers;
    private String []strArray;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amb_details_class);
        ambname =findViewById(R.id.ambnameid);
        ambphone =findViewById(R.id.ambphoneid);
        ambadress =findViewById(R.id.amblocationid);
        imageView=findViewById(R.id.ambimg);




        amblistdata = new ArrayList<>();
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


        myDbHelper = new DatabaseHelper(AmbDetailsClass.this);
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

            c = myDbHelper.displayallambbydisData(disvalue);
        }
        else {
            c = myDbHelper.displayallambbyareaData(areaid);
        }
        if (c.getCount() == 0) {

            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();

        } else {

            while (c.moveToNext()) {

                int id = c.getInt(0);
                String name = c.getString(1);
               // String type = c.getString(2);
                //String sepcialty = c.getString(3);
                phone = c.getString(2);
                String address = c.getString(3);
               // String web = c.getString(6);
                AmbClass ambclass = new AmbClass(id, name, phone, address);
                amblistdata.add(ambclass);

            }


        }

        String k = amblistdata.get(i).getName();
        ambname.setText(k);

        ambadress.setText(amblistdata.get(i).getAddress());
        ambphone.setText(amblistdata.get(i).getPhone());
       // hoscata.setText(amblistdata.get(i).getSepcialty());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Phone",Toast.LENGTH_LONG).show();
                numbers=null;
               numbers=amblistdata.get(i).getPhone();

                strArray=numbers.split(",");
                AlertDialog.Builder builder = new AlertDialog.Builder(AmbDetailsClass.this);
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
