package com.example.kotlin.java;

import java.util.ArrayList;

public class JavaTest {

    public ArrayList<String> list   =   new ArrayList();
    public ArrayList<Object> objects=   new ArrayList();

    public void fun(){
        //objects = list;       //error
        //list    = objects;    //error

        final String name = "eee";
        objects.add(name);
        objects.add(1);
    }


}
