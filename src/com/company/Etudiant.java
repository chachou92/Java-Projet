package com.company;
import java.io.*;
import java.util.ArrayList;

/* On créé une classe Etudiant qui:
- lit le fichier etu.csv et retourne un tableau de String contentant les informations du fichier pour chaque etudiant
- créé une liste avec les informations étudiant par étudiant
*/

public class Etudiant {

    //Methode pour lire le fichier etu.csv
    public static String lireFichierEtu(){

        try {

            //Ouverture et lecture du fichier etudiant
            File f = new File (System.getProperty("user.dir")+"/etu.csv");
            InputStream in = new FileInputStream(f);
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);

            //On créé une String qui affiche les données du fichier etu.csv
            String line= "";
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

    //Méthode pour avoir chaque information sur chaque etudiant : retourne une Arraylist d'Arraylist
    public static ArrayList<ArrayList<String>> infoEtudiant() {

        ArrayList<ArrayList<String>> infoEtu = new ArrayList<ArrayList<String>>();

        String fichier = lireFichierEtu();

        for (String ligne : fichier.split("\n")) {
            ArrayList<String> ligneEtu = new ArrayList<String>();
            String[] pointVirgule = ligne.split(";");
            for (int i = 0; i< pointVirgule.length ; i++){
                ligneEtu.add(pointVirgule[i]);
            }

            infoEtu.add(ligneEtu);

            }
            //On enleve la derniere liste correspondant à [null]
            infoEtu.remove(infoEtu.size()-1);

        return infoEtu;
    }
}
