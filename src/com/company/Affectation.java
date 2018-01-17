package com.company;


/*Dans cette classe on gere l'affectattion des etudiants aux groupes de TD
en tenant compte des contraintes de cours sur le même creneau.*/

import java.util.ArrayList;

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

    //Methode pour savoir si deux etudiants peuvent changer ou pas
    public static boolean peuventChanger (Etudiant e1, Etudiant e2, ArrayList<Cours> c, int max){
        boolean test1 = false;
        for (int i = 0; i< e1.coursProbleme(max).size(); i++){
            if(c == e1.coursProbleme(max).get(i)) {
                test1 = true;
            }
        }
        boolean test2 = false;
        for (int i = 0; i< e2.coursProbleme(max).size(); i++){
            if(c == e2.coursProbleme(max).get(i)) {
                test2 = true;
            }
        }
        boolean test3 = false;
        for (int i = 0; i< e2.coursInscrit(max).size(); i++){
            for (int k = 1; k<c.size();k++) {
                if (c.get(k) == e2.coursInscrit(max).get(i).CM()){
                    test3 = true;
                }
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

