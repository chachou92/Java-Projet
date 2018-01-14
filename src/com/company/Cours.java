package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Cours {
    private String nomCours;
    //private int coursOuTD; //Si égal à 1 alors TD sinon, cours

    //Constructeurs

    public Cours(String nomCours) {
        this.nomCours = nomCours;
    }

    public Cours() {
        nomCours = "";
    }

    //Méthodes get()
    public String getNomCours() {
        return nomCours;
    }

    //public int getCoursOuTD () { return coursOuTD; }

//On va creer une méthode qui retourne la liste des etudiants qui participent au cours
//D'abord on va chercher l'index du cours dans notre liste infoEtu

    //Methode indice()
    public int indice() {
        int index;
        ArrayList<String> listeNomCours = new ArrayList<String>();
        listeNomCours = Etudiant.infoEtudiant().get(0);
        index = listeNomCours.indexOf(getNomCours());
        return index;
    }

    //Methode toString pour afficher les Cours
    @Override
    public String toString() {
        return nomCours;
    }

    //Méthode participe()
    public ArrayList<String> participe() {
        ArrayList<String> participe = new ArrayList<String>();
        ArrayList<ArrayList<String>> ie = Etudiant.infoEtudiant();
        for (int i = 1; i < (ie.size()); i++) {
            ArrayList<String> p = ie.get(i);
            System.out.println(p);
            if (p.get(indice()).equals("1")) {
                participe.add(p.get(0));
            }
        }
        return participe;
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


    //Méthode pour avoir les infos sur l'emploi du temps
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

    //Méthode pour savoir si c'est un cours un TD
    public void infoCoursOuTD (){
        int coursOuTD;
        if (nomCours.contains("_"))
            coursOuTD = 1;
        else
            coursOuTD = 0;
    }

    //Méthode pour compter nombreTD
    public int nombreTD (){
        ArrayList<ArrayList<String>> infoEdt = infoEmploisDuTemps();
        int s = 0; //Compteur qui s'incrémente des qu'il trouve un TD correspondant à un cours
        ArrayList<String> verif = new ArrayList<String>(); //On ajoute les éléments qu'on trouve dans cette liste et grace à elle on vérifie qu'on n'incrémente pas s quand on tombe deux fois sur le même cours mais qui a lieu par exemple deux heures de suite
        for (int i = 0; i < infoEdt.size() ; i++){
                for (int j = 0; j < infoEdt.get(i).size() ; j++){
                    if(infoEdt.get(i).get(j).contains(" ")){
                        String [] Espace = infoEdt.get(i).get(j).split(" "); //Compter les TD de la même matière mais qui ont lieu en même temps
                        for (int k = 0; k < Espace.length; k++){
                            if(Espace[k].contains(nomCours+"_") && verif.indexOf(Espace[k])==-1){
                                s += 1;
                                verif.add(Espace[k]);
                            }
                        }
                    }
                    else{
                        if (infoEdt.get(i).get(j).contains(nomCours+"_") && verif.indexOf(infoEdt.get(i).get(j)) ==-1){
                            s += 1;
                            verif.add(infoEdt.get(i).get(j));
                        }
                    }

                }

        }
        return s;
    }

    //Méthode pour savoir quels cours se passent en même temps
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


}