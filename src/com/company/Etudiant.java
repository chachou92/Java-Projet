package com.company;
import java.io.*;
import java.util.ArrayList;


/**
 * Classe qui donne les informations utiles des étudiants.
 */
public class Etudiant {

    private String id;

    /**
     * Construit un etudiant a partir d'un identifiant.
     * @param identifiant
     */
    public Etudiant (String identifiant){
        id = identifiant;
    }
    public Etudiant (){
        id ="";
    }

    /**
     * Retourne l'identifiant d'un etudiant.
     * @return L'identifiant de l'étudiant.
     */
    public String getId () {return id;}

    /**
     * Affiche l'etudiant.
     * @return L'identifiant de l'etudiant sous forme de String.
     */
    @Override
    public String toString (){
        return id;
    }

    /**
     * Affiche la liste des cours dans lesquels l'etudiant est inscrit.
     * @param max
     * @return Une arrayliste "coursInscrit"
     */
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


    /**
     * Retourne la liste des cours que l'etudiant suit et qui posent probleme.
     * @param max
     * @return Une arrayliste "coursProbleme"
     */
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

                }
            }
        }
        return coursProbleme;

    }

    /**
     * Indique si l'etudiant a des cours en conflit.
     * @param max
     * @return Vrai si l'étudiant a un cours en conflit et faux sinon.
     */
    public boolean verifCoursEnConflit (int max){
        if (coursProbleme(max).size() !=0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Indique le TD dans lequel l'etudiant est inscrit.
     * @param c
     * @param max
     * @return Le numero du TD de l'étuddiant pour le cours c.
     */
    public Cours numeroTDMatiere (Cours c, int max){
        ArrayList<Cours> TD = c.TD();
        for (int i = 0; i < TD.size(); i++){
            if (TD.get(i).listeInscrits(max).contains(getId())){
                return TD.get(i);
            }
        }
        return null;
    }

}
