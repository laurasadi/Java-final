package com.rick.morty;

import java.io.Serializable;

public class Rick implements Serializable {
    private String name;
    private String gender;
    private String image;
    private int id;


    // skirtas duomenims is JSON
    public Rick(String name, String gender, String image, int id) {
        this.name = name;
        this.gender = gender;
        this.image = image;
        this.id= id;
    }
    // skirtas duomenims is new entry
    public Rick(String gender, String image, int id) {
        this.gender = gender;
        this.image = image;
        this.id= id;

    }


    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getgender() {
        return gender;
    }

    public void setgender(String gender) {
        this.gender = gender;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id= id;
    }



    @Override
    public String toString() {
        return "Corona{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", image='" + image + '\'' +
                ", id=" + id+
                '}';
    }
}
