package com.firefliesalco.www.model;

import com.firefliesalco.www.Generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Blueprint {

    public ArrayList<SubBlueprint> subBlueprints;
    private Vertex min, max;
    private ArrayList<Vertex> vertices;
    private ArrayList<Vertex> normals;
    private float growthFactor;

    public Blueprint(BufferedReader reader, float growthFactor){
        vertices = new ArrayList<>();
        normals = new ArrayList<>();
        this.growthFactor = growthFactor;
        subBlueprints = new ArrayList<>();
        min = new Vertex(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);
        max = new Vertex(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE);
        int currentSub = -1;
        try {
            String text = reader.readLine();
            while(currentSub == -1){
                if(text == null){
                    break;
                }
                if(text.startsWith("usemtl")){
                    subBlueprints.add(new SubBlueprint(Generator.requestFloat("Red Value 0-1"),Generator.requestFloat("Green Value 0-1"),Generator.requestFloat("Blue Value 0-1")));
                    currentSub++;
                }
                else if(text.startsWith("vn")){
                    String[] stuff = text.split(" ");
                    Vertex normal = new Vertex(Float.parseFloat(stuff[1]), Float.parseFloat(stuff[2]), Float.parseFloat(stuff[3]));
                    addNormal(normal);
                }
                else if(text.startsWith("v")) {
                    String[] stuff = text.split(" ");
                    Vertex vertex = new Vertex(Float.parseFloat(stuff[1]), Float.parseFloat(stuff[2]), Float.parseFloat(stuff[3]));
                    min = Vertex.min(vertex, min);
                    max = Vertex.max(vertex, max);
                    addVertex(vertex);
                }
                text = reader.readLine();
            }
            while(text != null){
                if(text.startsWith("usemtl")){
                    subBlueprints.add(new SubBlueprint(Generator.requestFloat("Red Value 0-1"),Generator.requestFloat("Green Value 0-1"),Generator.requestFloat("Blue Value 0-1")));
                    currentSub++;
                }
                if(text.startsWith("f")){
                    String[] stuff = text.split(" ");
                    String[] s1 = stuff[1].split("//");
                    String[] s2 = stuff[2].split("//");
                    String[] s3 = stuff[3].split("//");


                    subBlueprints.get(currentSub).addData(getVertices().get(Integer.parseInt(s1[0])-1));
                    subBlueprints.get(currentSub).addData(getNormals().get(Integer.parseInt(s1[1])-1));
                    subBlueprints.get(currentSub).addData(getVertices().get(Integer.parseInt(s2[0])-1));
                    subBlueprints.get(currentSub).addData(getNormals().get(Integer.parseInt(s2[1])-1));
                    subBlueprints.get(currentSub).addData(getVertices().get(Integer.parseInt(s3[0])-1));
                    subBlueprints.get(currentSub).addData(getNormals().get(Integer.parseInt(s3[1])-1));

                    if(stuff.length==5) {
                        String[] s4 = stuff[4].split("//");
                        subBlueprints.get(currentSub).addData(getVertices().get(Integer.parseInt(s2[0])-1));
                        subBlueprints.get(currentSub).addData(getNormals().get(Integer.parseInt(s2[1])-1));
                        subBlueprints.get(currentSub).addData(getVertices().get(Integer.parseInt(s3[0])-1));
                        subBlueprints.get(currentSub).addData(getNormals().get(Integer.parseInt(s3[1])-1));
                        subBlueprints.get(currentSub).addData(getVertices().get(Integer.parseInt(s4[0])-1));
                        subBlueprints.get(currentSub).addData(getNormals().get(Integer.parseInt(s4[1])-1));
                    }
                }
                text = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getDataCount(){
        int count = 0;
        for(SubBlueprint sb : subBlueprints){
            count += sb.getFinalData().size()/2;
        }
        return count;
    }

    public void addVertex(Vertex v){
        vertices.add(v);
    }

    public void addNormal(Vertex v){
        normals.add(v);
    }

    public ArrayList<Vertex> getNormals(){
        return normals;
    }

    public ArrayList<Vertex> getVertices(){
        return vertices;
    }

    public float getGrowthFactor(){
        return growthFactor;
    }

    public String getMins(){
        return min.getX() + ";" + min.getY() + ";" + min.getZ();
    }

    public String getMaxs(){
        return max.getX() + ";" + max.getY() + ";" + max.getZ();
    }

}
