package com.company;
import java.io.*;
import java.util.ArrayList;

/* On créé une classe Etudiant qui:

*/

public class Etudiant {

    private String id;

    public Etudiant (String identifiant){
        id = identifiant;
    }
    public Etudiant (){
        id ="";
    }

    public String getId () {return id;}

    @Override
    public String toString (){
        return id;
    }

    public ArrayList<Cours> coursInscrit (int max){
        ArrayList<Cours> coursInscrit = new ArrayList<Cours>();
        ArrayList<Cours> listeCours = new ArrayList<Cours>();
        ArrayList<String> participe = new ArrayList<String>();
        ArrayList<ArrayList<String>> ie = LectureFichiers.infoEtudiant();
        participe = ie.get(0);
        for (int i = 1; i < participe.size(); i++){
            Cours c = new Cours(participe.get(i));
            listeCours.add(c);
        }
        for (int i = 0; i < listeCours.size(); i++){
            if (listeCours.get(i).participe().contains(id)){
                coursInscrit.add(listeCours.get((i)));
            }
        }
        for (int i = 0; i < listeCours.size(); i++){
            if (listeCours.get(i).TD().size() !=0){
                ArrayList<ArrayList<String>> affect = listeCours.get(i).affecteTD(max);
                ArrayList<Cours> TD = listeCours.get(i).TD();
                for (int j = 0; j < affect.size(); j++){
                    if(affect.get(j).contains(getId())){
                        coursInscrit.add(TD.get(j));
                    }
                }
            }

        }
        return coursInscrit;
    }

    //On gere les problemes d'affectation associes a l'etudiant
    //Methode pour savoir si un etudiant pose probleme
    public ArrayList<ArrayList> coursProbleme (int max){

    }

}
