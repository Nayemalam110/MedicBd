package com.example.doctorhere;

public class HosClass {


    int id;
    String name,type,sepcialty,phone,address,web;

    public HosClass(int id, String name , String type, String sepcialty, String phone , String address, String web)
    {
        this.id=id;
        this.name=name;
        this.type=type;
        this.sepcialty=sepcialty;
        this.phone=phone;
        this.address=address;
        this.web=web;


    }

 /*   public HosClass()
    {

    }*/


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSepcialty() {
        return sepcialty;
    }

    public void setSepcialty(String sepcialty) {
        this.sepcialty = sepcialty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
