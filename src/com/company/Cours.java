package com.company;

import java.io.*;

public class Cours {
}




    //Methode pour lire le fichier edt.csv
    public static String lireFichierEdt(){
        try {
            //Ouverture et lecture du fichier emploi du temps
            File f = new File (System.getProperty("user.dir")+"/edt.csv");
            InputStream in = new FileInputStream(f);
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            //On créé une String qui affiche les données du fichier edt.csv
            String fichier = "";
            while (line != null){
                line  = bufferedReader.readLine();
                fichier = fichier + line + "\n";
            }
            return fichier;
        }
        catch (IOException e) {
            e.printStackTrace();
            return "Exception dans la lecture du fichier edt.csv";
        }
    }