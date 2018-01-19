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
            if (p.get(indice()).equals("1")) {
                participe.add(p.get(0));
            }
        }
        return participe;
    }


    //Méthode pour savoir si c'est un cours un TD
    public int infoCoursOuTD (){
        int coursOuTD;
        if (nomCours.contains("_"))
            coursOuTD = 1;
        else
            coursOuTD = 0;
        return coursOuTD;
    }

    //Methode pour avoir la liste des etudiants inscrits que ce soit dans des TDs ou des CMs
    public ArrayList<String> listeInscrits (int max){
        if (infoCoursOuTD() == 0){
            return participe();
        }
        else
        {
            ArrayList<String> listeInscrits = new ArrayList<String>();
            ArrayList<ArrayList<String>> affectationTD = CM().affecteTD(max);
            char indicechar = getNomCours().charAt(getNomCours().length() - 1);
            String indiceString = ""+indicechar;
            int indiceint = Integer.parseInt(indiceString) - 1;
            listeInscrits.addAll(affectationTD.get(indiceint));
            return listeInscrits;
        }

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

    //Méthode qui affecte "grossierement" (sans tenir compte des conflits de creneau) les etudiants a un TD pour la matiere
    public  ArrayList<ArrayList<String>> affecteTD(int max){
        ArrayList<ArrayList<String>> affectationTD = new ArrayList<ArrayList<String>>();
        int debut = 0;
        int x;
        if (participe().size()%nombreTD() == 0){
            x = (participe().size())/nombreTD();
        }
        else{
            x = (participe().size())/nombreTD() + 1 ;
        }
        if (x <= max){
            for (int i=0;i< nombreTD()-1;i++){
                ArrayList<String> al = new ArrayList<String>(participe().subList(debut,(debut+x)));
                debut = debut + x;
                affectationTD.add(al);
            }
            ArrayList<String> al = new ArrayList<String>(participe().subList(debut, participe().size()));
            affectationTD.add(al);
        }
        else{
            System.out.println("La taille maximale des TDs est trop basse pour le cours " + nomCours + ". Merci de l'augmenter avec l'option 4 du menu, a partir de " + x +".");
            return null;
        }
        return affectationTD;
    }

    //Methode qui nous retourne la liste des Cours qui ont lieu en même temps que notre Cours donne
    public ArrayList<Cours> coursEnConflit (){
        ArrayList<Cours> coursEnConflit = new ArrayList<Cours>();
        ArrayList<ArrayList<Cours>> aux = new ArrayList<ArrayList<Cours>>();
        ArrayList<ArrayList<Cours>> coursEnMemeTemps = LectureFichiers.coursEnMemeTemps();
        for (int i = 0; i < coursEnMemeTemps.size(); i++){
           for (int j = 0; j < coursEnMemeTemps.get(i).size(); j++){
               if (this.getNomCours().equals(coursEnMemeTemps.get(i).get(j).getNomCours())){
                   aux.add(coursEnMemeTemps.get(i));
               }
           }
        }
        for (int i = 0; i < aux.size(); i++){
            for (int j = 0; j < aux.get(i).size(); j++){
                if(aux.get(i).get(j).getNomCours().equals(this.getNomCours())){
                    aux.get(i).remove(aux.get(i).get(j));
                }
            }
        }
        for (int i = 0; i < aux.size(); i++){
            for (int j = 0; j < aux.get(i).size(); j++){
                coursEnConflit.add(aux.get(i).get(j));
            }
        }
        return coursEnConflit;
    }

    //Methode qui nous retourne le CM d'un TD
    public Cours CM (){
        if (infoCoursOuTD() == 0){
            return this;
        }
        else{
            String nom = "";
            int i = 0;
            while (getNomCours().charAt(i)!= '_'){
                nom = nom + getNomCours().charAt(i);
                i = i+1;
            }

            Cours CM = new Cours(nom);
            return CM;
        }
    }

    public boolean equals (Cours c){
        if (c.getNomCours().equals(getNomCours())){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean contenuDansListe (ArrayList<Cours> c){
        int i = 0;
        boolean verif = false;
        while (i < c.size() && verif == false){
            if(this.equals(c.get(i))){
                verif = true;
            }
            i = i+1;
        }
        return verif;
    }

}