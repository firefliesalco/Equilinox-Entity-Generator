package com.firefliesalco.www.model;

import java.util.ArrayList;

public class SubBlueprint {


    private ArrayList<Vertex> finalData;
    private float r, g, b;


    public SubBlueprint(float r, float g, float b){

        finalData = new ArrayList<>();
        this.r = r;
        this.g = g;
        this.b = b;
    }



    public ArrayList<Vertex> getFinalData(){
        return finalData;
    }

    public void addData(Vertex v){
        finalData.add(v);
    }

    public float getR(){
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public String asString(){
        ArrayList<String> list = new ArrayList<>();
        for(Vertex v : getFinalData()){
            list.add(""+v.getX());
            list.add(""+v.getY());
            list.add(""+v.getZ());
        }
        return String.join(";", list.toArray(new String[0]));
    }

}
