package com.example.a18016236task2d;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class StudentHealth implements Serializable {
    String name;
    int temp;

    public StudentHealth(String name, int temp) {
        this.name = name;
        this.temp = temp;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public StudentHealth(){

    }

    @Override
    public String toString(){
        return name;
    }
}
