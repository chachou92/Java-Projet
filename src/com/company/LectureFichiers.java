package com.company;

import java.io.*;
import java.util.ArrayList;

/**
 *  Classe qui gere la lecture et la recuperation des informations
des fichiers edt.csv et etu.csv.
 */

public abstract class LectureFichiers {

    //Methode pour lire le fichier etu.csv

    /**
     * Permet de lire le fichier etu.csv
     * @return Le fichier sous forme de String.
     */
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


    /**
     *Retourne chaque information sur chaque etudiant.
     * @return Le fichier sous forme d'arraylistes de String.
     */
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

    /**
     * Lit le fichier edt.csv.
     * @return Le fichier sous forme de String.
     */
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


    //Méthode pour avoir les infos sur l'emploi du temps

    /**
     * Retourne les informations sur l'emploi du temps.
     * @return Le fichier sous forme d'arraylistes de String.
     */
    public static ArrayList<ArrayList<String>> infoEmploisDuTemps() {

        ArrayList<ArrayList<String>> infoEdt = new ArrayList<ArrayList<String>>();

        String fichier = lireFichierEdt();

        for (String ligne : fichier.split("\n")) {
            ArrayList<String> ligneEdt = new ArrayList<String>();
            String[] pointVirgule = ligne.split(";");
            for (int i = 0; i< pointVirgule.length ; i++){
                ligneEdt.add(pointVirgule[i]);
            }

            infoEdt.add(ligneEdt);

        }
        //On enleve la derniere liste correspondant à [null]
        infoEdt.remove(infoEdt.size()-1);

        return infoEdt;
    }


    /**
     * Retourne la liste des cours qui se passent en meme temps.
     * @return fichier
     */
    public static ArrayList<ArrayList<Cours>> coursEnMemeTemps (){
        ArrayList<ArrayList<String>> infoEdt = infoEmploisDuTemps();
        ArrayList<ArrayList<Cours>> coursEnMemeTemps = new ArrayList<ArrayList<Cours>>();
        for (int i = 0; i < infoEdt.size(); i++){
            for (int j = 0; j < infoEdt.get(i).size(); j++){
                if (infoEdt.get(i).get(j).contains((" "))){
                    ArrayList<Cours> memeCreneau = new ArrayList<Cours>();
                    String [] creneau = infoEdt.get(i).get(j).split(" ");
                    for (int k = 0; k < creneau.length; k++){
                        Cours c = new Cours(creneau[k]);
                        memeCreneau.add(c);
                    }
                    coursEnMemeTemps.add(memeCreneau);
                }

            }
        }
        return coursEnMemeTemps;
    }

    //Lecture de fichiers qu'on tape à la main:

    //Methode pour lire le fichier détudiant tapé à la main

    /**
     * Lit les fichiers tapes a la main.
     * @param nom
     * @return fichier
     */
    public static String lireFichierEtu(String nom){

        try {

            //Ouverture et lecture du fichier etudiant
            File f = new File (System.getProperty("user.dir")+"/"+nom);
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
            return "Exception dans la lecture du fichier d'étudiant";
        }
    }

    //Méthode pour avoir chaque information sur chaque etudiant : retourne une Arraylist d'Arraylist

    /**
     * Retourne chaque information sur chaque etudiant du fichier tape a la main.
     * @param nom
     * @return Le fichier sous forme d'arraylistes de String.
     */
    public static ArrayList<ArrayList<String>> infoEtudiant(String nom) {

        ArrayList<ArrayList<String>> infoEtu = new ArrayList<ArrayList<String>>();

        String fichier = lireFichierEtu(nom);

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

    //Methode pour lire le fichier edt.csv

    /**
     * Lit le fichier emploi du temps tape a la main.
     * @param nom
     * @return fichier
     */
    public static String lireFichierEdt(String nom){
        try {
            //Ouverture et lecture du fichier emploi du temps
            File f = new File (System.getProperty("user.dir")+"/"+nom);
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
            return "Exception dans la lecture du fichier d'emploi du temps";
        }
    }


    //Méthode pour avoir les infos sur l'emploi du temps

    /**
     * Retourne les informations sur l'emploi du temps tape a la main.
     * @param nom
     * @return Le fichier sous forme d'arraylistes de String.
     */
    public static ArrayList<ArrayList<String>> infoEmploisDuTemps(String nom) {

        ArrayList<ArrayList<String>> infoEdt = new ArrayList<ArrayList<String>>();

        String fichier = lireFichierEdt(nom);

        for (String ligne : fichier.split("\n")) {
            ArrayList<String> ligneEdt = new ArrayList<String>();
            String[] pointVirgule = ligne.split(";");
            for (int i = 0; i< pointVirgule.length ; i++){
                ligneEdt.add(pointVirgule[i]);
            }

            infoEdt.add(ligneEdt);

        }
        //On enleve la derniere liste correspondant à [null]
        infoEdt.remove(infoEdt.size()-1);

        return infoEdt;
    }

    //Méthode pour savoir quels cours se passent en même temps

    /**
     * Retourne la liste des cours qui se passent en meme temps depuis le fichier tape a la main.
     * @param nom
     * @return Un arrayliste d'arrayliste des cours en meme temps
     */
    public static ArrayList<ArrayList<Cours>> coursEnMemeTemps (String nom){
        ArrayList<ArrayList<String>> infoEdt = infoEmploisDuTemps(nom);
        ArrayList<ArrayList<Cours>> coursEnMemeTemps = new ArrayList<ArrayList<Cours>>();
        for (int i = 0; i < infoEdt.size(); i++){
            for (int j = 0; j < infoEdt.get(i).size(); j++){
                if (infoEdt.get(i).get(j).contains((" "))){
                    ArrayList<Cours> memeCreneau = new ArrayList<Cours>();
                    String [] creneau = infoEdt.get(i).get(j).split(" ");
                    for (int k = 0; k < creneau.length; k++){
                        Cours c = new Cours(creneau[k]);
                        memeCreneau.add(c);
                    }
                    coursEnMemeTemps.add(memeCreneau);
                }

            }
        }
        return coursEnMemeTemps;
    }
}
