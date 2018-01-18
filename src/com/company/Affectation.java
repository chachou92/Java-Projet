package com.company;


/*Dans cette classe on gere l'affectattion des etudiants aux groupes de TD
en tenant compte des contraintes de cours sur le même creneau.*/

import java.util.ArrayList;

import static java.lang.Math.min;

public abstract class Affectation {

    //Methode pour instancier tous les objets Etudiants a partir du fichier etu
    public static ArrayList<Etudiant> listeEutudiants (){
        ArrayList<Etudiant> listeEtu = new ArrayList<Etudiant>();
        ArrayList<ArrayList<String>> ie = LectureFichiers.infoEtudiant();
        for(int i=1;i<ie.size();i++){
            Etudiant e = new Etudiant(ie.get(i).get(0));
            listeEtu.add(e);
        }
    return listeEtu;
    }

    //Methode pour savoir si un etudiant pose un probleme pour un cours ou plus
    public static ArrayList<Etudiant> listeEtudiantsAProbleme (int max){
        ArrayList<Etudiant> etusAProblemes = new ArrayList<Etudiant>();
        for (int i = 0; i < listeEutudiants().size(); i++){
            if (listeEutudiants().get(i).verifCoursEnConflit(max)){
                etusAProblemes.add(listeEutudiants().get(i));
            }
        }
        return etusAProblemes;
    }

    //Methode qui nous retourne la liste des etudiants qui n'ont pas de problemes
    public static ArrayList<Etudiant> listeEtudiantsSansProbleme (int max){
        ArrayList<Etudiant> etusSansProblemes = new ArrayList<Etudiant>();
        for (int i = 0; i < listeEutudiants().size(); i++){
            if (!(listeEutudiants().get(i).verifCoursEnConflit(max))){
                etusSansProblemes.add(listeEutudiants().get(i));
            }
        }
        return etusSansProblemes;
    }

    //Methode qui echange deux etudiants de leurs TDs respectifs
    public static void echangeEtudiants (Etudiant e1, Etudiant e2, ArrayList<Etudiant> al1, ArrayList<Etudiant> al2){
        al1.remove(e1);
        al2.add(e1);
        al2.remove(e2);
        al1.add(e2);
    }

    public static boolean listesEgales (ArrayList<Cours> c1, ArrayList<Cours> c2){
        int min = min(c1.size(),c2.size());
        boolean verif = true;
        for (int i = 0; i < min; i++){
            if (!(c1.get(i).equals(c2.get(i)))){
                verif = false;
            }
        }
        return verif;
    }

    //Methode pour savoir si deux etudiants peuvent changer ou pas
    public static boolean peuventChanger (Etudiant e1, Etudiant e2, ArrayList<Cours> c, int max){
        boolean test1 = false;
        //boolean test1 = true;
        for (int i = 0; i< e1.coursProbleme(max).size(); i++){
            if(listesEgales(c, e1.coursProbleme(max).get(i))) {
                test1 = true;
            }
           /* int k = 0;
            while(k < c.size() && test1 == true){
                if (!(c.get(k).getNomCours().equals(e1.coursProbleme(max).get(i).get(k).getNomCours()))){
                    test1 = false;
                }
                k = k+1;
            }*/
        }
        boolean test2 = false;
        //boolean test2 = true;
        for (int i = 0; i< e2.coursProbleme(max).size(); i++){
            if(listesEgales(c,e2.coursProbleme(max).get(i))) {
                test2 = true;
            }
            /*int k = 0;
            while(k < c.size() && test2 == true){
                if (!(c.get(k).getNomCours().equals(e2.coursProbleme(max).get(i).get(k).getNomCours()))){
                    test2 = false;
                }
                k = k+1;
            }*/
        }
        boolean test3 = false;
        //boolean test3 = true;
        for (int i = 0; i< e2.coursInscrit(max).size(); i++){
            for (int k = 1; k<c.size();k++) {
                if (c.get(k).equals(e2.coursInscrit(max).get(i).CM())){
                    test3 = true;
                }
                /*int l = 0;
                while(l < c.size() && test3 == true){
                    if (!(c.get(l).getNomCours().equals(e2.coursInscrit(max).get(i).CM().getNomCours()))){
                        test3 = false;
                    }
                    l = l+1;
                }*/

            }
        }
        if(test1==true && test2 == false && test3==true){
            return true;
        }
        else {
            return false;
        }
    }

    /*//Methode qui regle les prochemes de tous les etudiants (en principe)
    public void affectationFinale(int max){
        //On itere l'algorithme tant qu'il reste des etudiants a probleme
        while (listeEtudiantsAProbleme(max).size() != 0){
            Etudiant et = new Etudiant(listeEtudiantsAProbleme(max).get(0).getId());
            for(int i = 0; i < et.coursProbleme(max).size();i++){
                Etudiant ch = new Etudiant(listeEtudiantsSansProbleme(max).get(0).getId());

            }

        }
    }*/

}

