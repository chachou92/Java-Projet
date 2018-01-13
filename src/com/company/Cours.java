package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Cours {
    private String nomCours;
    private int nombreTd;

    //Constructeurs

    public Cours(String nomCours, int nombreTd) {
        this.nomCours = nomCours;
        this.nombreTd = nombreTd;
    }

    public Cours() {
        nomCours = "";
        nombreTd = 0;
    }

    //Méthodes get()
    public String getNomCours() {
        return nomCours;
    }

    public int getNombreTd() {
        return nombreTd;
    }

//On va cree une méthode qui retourne la liste des etudiants qui participent au cours
//D'abord on va chercher l'index du cours dans notre liste infoEtu

    //Methode indice()
    public int indice() {
        int index;
        ArrayList<String> listeNomCours = new ArrayList<String>();
        listeNomCours = Etudiant.infoEtudiant().get(0);
        index = listeNomCours.indexOf(getNomCours());
        return index;
    }

    //Méthode participe()
    public ArrayList<String> participe() {
        ArrayList<String> participe = new ArrayList<String>();
        ArrayList<ArrayList<String>>ie = Etudiant.infoEtudiant();
        for (int i = 1; i < (ie.size()); i++) {
            ArrayList<String> p = ie.get(i);
            System.out.println(p);
            if (p.get(indice()).equals("1")){
                participe.add(p.get(0));
            }
        }
        return participe;
    }
}























/*
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

}

*/


