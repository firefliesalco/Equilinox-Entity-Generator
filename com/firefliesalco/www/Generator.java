package com.firefliesalco.www;

import com.firefliesalco.www.model.ModelHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Scanner;

public class Generator {

    public static Generator gen;

    private static Scanner scan;
    private File outputFile;

    public static void main(String args[]){
        gen = new Generator();
        gen.generate();
    }

    public void generate() {
        scan = new Scanner(System.in);
        System.out.println("What would you would like the output file to be called? (no extension)");
        outputFile = new File(scan.nextLine() + ".txt");
        while (outputFile.exists()) {
            if(requestBoolean("File already exists!  Overwrite?"))
                break;
            System.out.println("Please input the name of a new file.");
            outputFile = new File(scan.nextLine() + ".txt");
        }

        BufferedWriter writer = null;

        try {
            outputFile.createNewFile();
            writer = new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String firstLine = "" + requestFloat("Size?");
        if(requestBoolean("Provide Override Name?")){
            firstLine += ";" + requestInt("Override Name ID");
            if(requestBoolean("Provide Override Description?")){
                firstLine += ";" + requestInt("Override Description ID");
                if(requestBoolean("Provide Randomization Boolean?")){
                    firstLine += ";" + requestBoolean("Randomization Boolean");
                }
            }
        }
        String line2 = requestString("Classification?");
        String line3 = "" + (requestBoolean("Can swim?")?1:0) + ";" + (requestBoolean("Can walk on land?")?1:0) + (requestBoolean("Provide offset?")?";"+requestFloat("Offest"):"");

        ArrayList<String> modelData = ModelHandler.loadModels();

        try {

            writer.write(firstLine);
            writer.newLine();
            writer.write(line2);
            writer.newLine();
            writer.write(line3);
            for(String line : modelData){
                writer.newLine();
                writer.write(line);
            }
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    public static String requestString(String message){
        System.out.println(message + " (string)");
        return scan.nextLine();
    }

    public static boolean requestBoolean(String message){
        System.out.println(message + " (boolean)");
        String read = scan.nextLine();
        while(!checkBoolean(read)){
            System.out.println("Invalid boolean.  Please input one of the following: true, t, 1, false, f, 0");
            read = scan.nextLine();
        }
        return parseBoolean(read);
    }

    public static int requestInt(String message){
        System.out.println(message + " (int)");
        int output = 0;
        while(true){
            try {
                output = Integer.parseInt(scan.nextLine());
                return output;
            } catch(Exception e){
                System.out.print("Invalid integer.  Please input a valid integer.");
            }
        }
    }

    public static float requestFloat(String message){
        System.out.println(message + " (float)");
        float output = 0;
        while(true){
            try {
                output = Float.parseFloat(scan.nextLine());
                return output;
            } catch(Exception e){
                System.out.print("Invalid float.  Please input a valid float.");
            }
        }
    }

    private static boolean checkBoolean(String input){
        return input.equalsIgnoreCase("true") || input.equalsIgnoreCase("t") || input.equalsIgnoreCase("false") || input.equalsIgnoreCase("f") || input.equals("1") || input.equals("0");
    }

    private static boolean parseBoolean(String input){
        return input.equalsIgnoreCase("true") || input.equalsIgnoreCase("t") || input.equals("1");
    }







}
