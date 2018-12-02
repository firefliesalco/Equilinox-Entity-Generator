package com.firefliesalco.www.model;

import com.firefliesalco.www.Generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class ModelHandler {


    public static ArrayList<String> loadModels(){
        ArrayList<String> modelData = new ArrayList<>();
        ArrayList<Blueprint> blueprints = new ArrayList<>();
        while(Generator.requestBoolean("Has another model?")){
            File file = new File(Generator.requestString("Enter model file name (obj) with no extension") + ".obj");
            while(!file.exists()){
                System.out.println("File does not exist!");
                file = new File(Generator.requestString("Enter model file name (obj) with no extension") + ".obj");
            }
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            blueprints.add(new Blueprint(bufferedReader, Generator.requestFloat("Growth Factor?")));
        }

        modelData.add(""+blueprints.size());
        for(Blueprint blueprint : blueprints){
            modelData.add(blueprint.getMins() +";" + blueprint.getMaxs() + ";" + blueprint.getGrowthFactor());
            modelData.add(blueprint.getDataCount() + ";" + blueprint.subBlueprints.size());
            for(SubBlueprint subBlueprint : blueprint.subBlueprints){
                modelData.add(subBlueprint.getFinalData().size()/2+";"+subBlueprint.getR() + ";" + subBlueprint.getG() + ";" + subBlueprint.getB());
                modelData.add(subBlueprint.asString());
            }
        }


        return modelData;
    }


}
