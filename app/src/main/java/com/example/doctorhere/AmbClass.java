package com.example.doctorhere;

public class AmbClass {

    int id;
    String name,phone,address;

    public AmbClass(int id, String name ,  String phone , String address)
    {
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.address=address;

    }

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
}
