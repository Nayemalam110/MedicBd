package com.example.doctorhere;

public class DisClass {
    int id;
    String disname;



    public DisClass(int id, String disname){

   this.id= id;
   this.disname=disname;

    }
    public DisClass(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisname() {
        return disname;
    }

    public void setDisname(String disname) {
        this.disname = disname;
    }
}
