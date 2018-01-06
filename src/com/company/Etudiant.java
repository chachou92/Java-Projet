package com.company;

import java.io.*;
import java.util.ArrayList;


public class Etudiant {
    private int id;
    private ArrayList<String> listeCours;

    public Etudiant (int numero, ArrayList<String> liste){
        id = numero;
        listeCours = liste;
    }
    public Etudiant (){
        id = 0;
        listeCours = new ArrayList<String>();
    }

    //Méthode getId à faire et get listeCours



    public static String lireFichierEtu(){
        try {
            //Ouverture et lecture du fichier étudiant
            File f = new File (System.getProperty("user.dir")+"/etu.csv");
            InputStream in = new FileInputStream(f);
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            //On créé une String qui affiche les données du fichier etu.csv
            String fichier = "";
            while (line != null){
                line  = bufferedReader.readLine();
                fichier = fichier + line + "\n";
            }
            return fichier;
        }
        catch (IOException e) {
            e.printStackTrace();
            return "Exception dans la lecture du fichier etu.csv";
        }
    }

}
