package com.company;
import java.io.*;
import java.util.ArrayList;

/* On créé une classe Etudiant qui a comme attribut un Id, et qui donne des informations sur l'emploi du temps
et les conflits de chaque etudiant.
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

    //Methode qui donne les cours et les TD auquel est inscrit a priori un etudiant
    public ArrayList<Cours> coursInscrit (int max){
        //On instancie nos objets Cours a partir du fichier etu.
        ArrayList<Cours> coursInscrit = new ArrayList<Cours>();
        ArrayList<Cours> listeCours = new ArrayList<Cours>();
        ArrayList<String> participe = new ArrayList<String>();
        ArrayList<ArrayList<String>> ie = LectureFichiers.infoEtudiant();
        participe = ie.get(0);
        for (int i = 1; i < participe.size(); i++){
            Cours c = new Cours(participe.get(i));
            listeCours.add(c);
        }
        //On regarde si l'etudiant est inscrit au cours.
        for (int i = 0; i < listeCours.size(); i++){
            if (listeCours.get(i).participe().contains(id)){
                coursInscrit.add(listeCours.get((i)));
            }
        }
        //On regarde si l'etuduant est affecte au TD.
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
    public ArrayList<ArrayList<Cours>> coursProbleme (int max){
        ArrayList<ArrayList<Cours>> coursProbleme = new ArrayList<ArrayList<Cours>>();
        ArrayList<Cours> coursInscrit = coursInscrit(max);
        for (int i = 0; i < coursInscrit.size(); i++){
            ArrayList<Cours> coursEnConflit = coursInscrit.get(i).coursEnConflit();
            if (coursEnConflit.size() != 0){
                for (int j = i+1; j < coursInscrit.size(); j++){
                    //ligne rajoutee pour tester nouvelle methode
                    if (coursInscrit.get(j).contenuDansListe(coursEnConflit)){
                        ArrayList<Cours> aux = new ArrayList<Cours>();
                        Cours e = new Cours(coursInscrit(max).get(i).getNomCours());
                        aux.add(e);
                        int indice =coursInscrit.get(j).indiceDansListe(coursEnConflit);
                        //System.out.println("indice = "+indice);
                        Cours f = new Cours (coursEnConflit.get(indice).getNomCours());
                        aux.add(f);
                        coursProbleme.add(aux);
                    }

                   /* for (int k = 0; k < coursEnConflit.size(); k++){
                        if(coursEnConflit.get(k).getNomCours().equals(coursInscrit.get(j).getNomCours())){
                            ArrayList<Cours> aux = new ArrayList<Cours>();
                            Cours e = new Cours(coursInscrit.get(i).getNomCours());
                            aux.add(e);
                            Cours f = new Cours(coursEnConflit.get(k).getNomCours());
                            aux.add(f);
                            coursProbleme.add(aux);
                        }
                    }*/
                }
            }
        }
        return coursProbleme;

    }

    //Methode booleene qui nous verifie si l'etudiant a des cours en conflit
    public boolean verifCoursEnConflit (int max){
        if (coursProbleme(max).size() !=0){
            return true;
        }
        else{
            return false;
        }
    }

    //Methode pour savoir a quel TD participe l'etudiant pour une matiere donnee
    public Cours numeroTDMatiere (Cours c){
        ArrayList<Cours> TD = c.TD();
        for (int i = 0; i < TD.size(); i++){
            if (TD.get(i).participe().contains(getId())){
                return TD.get(i);
            }
        }
        return null;
    }

}
