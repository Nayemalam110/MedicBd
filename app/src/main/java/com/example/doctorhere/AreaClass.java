package com.example.doctorhere;

public class AreaClass {


    int id,dis_id;
    String name;

    public AreaClass(int id, int dis_id, String name)
    {

        this.id=id;
        this.dis_id=dis_id;
        this.name=name;

    }

    public AreaClass(){


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDis_id() {
        return dis_id;
    }

    public void setDis_id(int dis_id) {
        this.dis_id = dis_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
