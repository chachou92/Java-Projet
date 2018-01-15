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
        listeNomCours = LectureFichiers.infoEtudiant().get(0);
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
        ArrayList<ArrayList<String>> ie = LectureFichiers.infoEtudiant();
        for (int i = 1; i < (ie.size()); i++) {
            ArrayList<String> p = ie.get(i);
            System.out.println(p);
            if (p.get(indice()).equals("1")) {
                participe.add(p.get(0));
            }
        }
        return participe;
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
        ArrayList<ArrayList<String>> infoEdt = LectureFichiers.infoEmploisDuTemps();
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

    //Methode qui cree une liste de Cours qui correspond aux differents TDs
    public ArrayList<Cours> TD (){
        ArrayList<Cours> TD =  new ArrayList<Cours>();
        for (int i = 0; i < nombreTD(); i++){
            Cours c = new Cours(nomCours+"_"+(i+1));
            TD.add(c);
        }
        return TD;
    }

}