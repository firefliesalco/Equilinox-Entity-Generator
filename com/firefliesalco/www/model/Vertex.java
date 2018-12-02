package com.firefliesalco.www.model;

public class Vertex {

    private float x, y, z;

    public Vertex(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public static Vertex max(Vertex a, Vertex b){
        return new Vertex(Math.max(a.x, b.x), Math.max(a.y, b.y), Math.max(a.z, b.z));
    }

    public static Vertex min(Vertex a, Vertex b){
        return new Vertex(Math.min(a.x, b.x), Math.min(a.y, b.y), Math.min(a.z, b.z));
    }


}
