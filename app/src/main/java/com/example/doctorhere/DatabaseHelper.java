package com.example.doctorhere;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DatabaseHelper extends SQLiteOpenHelper {

    String DB_PATH = null;
    private static String DB_NAME = "mydatabase";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 11);
        this.myContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DB_PATH);
    }


    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();

            }
    }



    public Cursor displayAllData(){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from d_district",null);
        return cursor;

    }
    public Cursor displayAreadata(int selectvalue){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from d_area where district_id = "+selectvalue+"",null);
        return cursor;

    }
    public Cursor displayambAreadata(int selectvalue){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from d_amb_area where district_id = "+selectvalue+"",null);
        return cursor;

    }
    public Cursor displayHospitalData(int areaidint){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from d_hospitals where area = "+areaidint+"",null);
        return cursor;

    }
    public Cursor displayambareaData(int areaidint){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from dir_ambulance_service where area = "+areaidint+"",null);
        return cursor;

    }

    public Cursor displayallHospitalData(int areaidint){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from d_hospitals where district_id = "+areaidint+"",null);
        return cursor;

    }
    public Cursor displayallamblData(int areaidint){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from dir_ambulance_service where district_id = "+areaidint+"",null);
        return cursor;

    }

    public Cursor displayambAllData(){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from d_ambdis",null);
        return cursor;

    }
    public Cursor displayallambbydisData(int id){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from dir_ambulance_service where district_id = "+id+"",null);
        return cursor;

    }
    public Cursor displayallambbyareaData(int areaidint){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from dir_ambulance_service where area = "+areaidint+"",null);
        return cursor;

    }
    public Cursor displayblodisData(){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from d_dis_blo",null);
        return cursor;

    }
    public Cursor displaybloareaData(int i){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from d_area_blo where district_id = "+i+"",null);
        return cursor;

    }
    public Cursor displayallblodisData(int i){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from d_blood_bank where district_id = "+i+"",null);
        return cursor;

    }
    public Cursor displayallbloareaData(int i){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from d_blood_bank where area = "+i+"",null);
        return cursor;

    }

    public Cursor displayallemedicine(){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from t_drug_brand",null);
        return cursor;

    }
    public Cursor displaymedicinebybrandid(int i){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from t_drug_brand where brand_id= "+i+"",null);
        return cursor;

    }
    public Cursor displayphydisData(){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from d_dis_drug",null);
        return cursor;

    }
    public Cursor displayphyareaData(int i){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from d_area_drug where district_id = "+i+"",null);
        return cursor;

    }
    public Cursor displayallphydatabydis(int i){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from d_drug_store where district_id = "+i+"",null);
        return cursor;

    }
    public Cursor displayallphydatabyarea(int i){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from d_drug_store where area = "+i+"",null);
        return cursor;

    }
    public Cursor displaymedicinebygenid(int i){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from t_drug_generic where generic_id = "+i+"",null);
        return cursor;

    }
    public Cursor displayallprob(int aid){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from homdoc where category_id ="+aid+"",null);
        return cursor;

    }
    public Cursor displayallcat(){

        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from cat",null);
        return cursor;

    }

    public Cursor displayallprobdetails(int id) {
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from homdoc where id ="+id+"",null);
        return cursor;
    }
}