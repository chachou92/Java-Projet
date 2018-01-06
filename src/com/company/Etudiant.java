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



    public void LireFichierEtu(File f){
        try {
            //Ouverture du fichier étudiant
            File f = new File (System.getProperty("user.dir")+"/etu.csv");
            InputStreamReader reader = new InputStreamReader(f);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null){
                line  = bufferedReader.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
