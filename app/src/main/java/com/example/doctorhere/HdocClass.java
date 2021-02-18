package com.example.doctorhere;

public class HdocClass {

    int id,catid;

    String name,intro,causes,sym,treat;

    public HdocClass(int id,int catid,String name, String intro,String causes, String sym, String treat)
    {

        this.id=id;
        this.catid=catid;
        this.name=name;
        this.intro=intro;
        this.causes=causes;
        this.sym=sym;
        this.treat=treat;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCauses() {
        return causes;
    }

    public void setCauses(String causes) {
        this.causes = causes;
    }

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }

    public String getTreat() {
        return treat;
    }

    public void setTreat(String treat) {
        this.treat = treat;
    }
}
