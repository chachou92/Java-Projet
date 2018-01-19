package com.company;


/*Dans cette classe on gere l'affectattion des etudiants aux groupes de TD
en tenant compte des contraintes de cours sur le même creneau.*/

import java.util.ArrayList;

import static java.lang.Math.min;

public abstract class Affectation {

    //Methode pour instancier tous les objets Etudiants a partir du fichier etu
    public static ArrayList<Etudiant> listeEutudiants() {
        ArrayList<Etudiant> listeEtu = new ArrayList<Etudiant>();
        ArrayList<ArrayList<String>> ie = LectureFichiers.infoEtudiant();
        for (int i = 1; i < ie.size(); i++) {
            Etudiant e = new Etudiant(ie.get(i).get(0));
            listeEtu.add(e);
        }
        return listeEtu;
    }

    //Methode pour savoir si un etudiant pose un probleme pour un cours ou plus
    public static ArrayList<Etudiant> listeEtudiantsAProbleme(int max) {
        ArrayList<Etudiant> etusAProblemes = new ArrayList<Etudiant>();
        for (int i = 0; i < listeEutudiants().size(); i++) {
            if (listeEutudiants().get(i).verifCoursEnConflit(max)) {
                etusAProblemes.add(listeEutudiants().get(i));
            }
        }
        return etusAProblemes;
    }

    //Methode qui nous retourne la liste des etudiants qui n'ont pas de problemes
    public static ArrayList<Etudiant> listeEtudiantsSansProbleme(int max) {
        ArrayList<Etudiant> etusSansProblemes = new ArrayList<Etudiant>();
        for (int i = 0; i < listeEutudiants().size(); i++) {
            if (!(listeEutudiants().get(i).verifCoursEnConflit(max))) {
                etusSansProblemes.add(listeEutudiants().get(i));
            }
        }
        return etusSansProblemes;
    }

    //Methode qui echange deux etudiants de leurs TDs respectifs
    public static void echangeEtudiants(Etudiant e1, Etudiant e2, ArrayList<String> al1, ArrayList<String> al2) {
        al1.remove(e1.getId());
        al2.add(e1.getId());
        al2.remove(e2.getId());
        al1.add(e2.getId());
    }

    //methode qui compare deux listes
    public static boolean listesEgales(ArrayList<Cours> c1, ArrayList<Cours> c2) {
        int min = min(c1.size(), c2.size());
        boolean verif = true;
        for (int i = 0; i < min; i++) {
            if (!(c1.get(i).equals(c2.get(i)))) {
                verif = false;
            }
        }
        return verif;
    }


    //Methode pour savoir si deux etudiants peuvent changer ou pas
    public static boolean peuventChanger(Etudiant e1, Etudiant e2, ArrayList<Cours> c, int max) {
        //Test si l'Arraylist de Cours pose probleme a l'etudiant 1
        boolean test1 = false;
        //boolean test1 = true;
        for (int i = 0; i < e1.coursProbleme(max).size(); i++) {
            if (listesEgales(c, e1.coursProbleme(max).get(i))) {
                test1 = true;
            }
        }
        //Test si l'Arraylist de Cours ne pose pas problème a l'etudiant 2
        boolean test2 = false;
        for (int i = 0; i < e2.coursProbleme(max).size(); i++) {
            if (listesEgales(c, e2.coursProbleme(max).get(i))) {
                test2 = true;
            }

        }
        //Test si l'etudiant 2 participe bien au cours a echanger
        boolean test3 = false;
            for (int k = 0; k < c.size(); k++) {
                if (c.get(k).CM().contenuDansListe(e2.coursInscrit(max))) {
                    test3 = true;
                }
            }
        if (test1 == true && test2 == false && test3 == true) {
            return true;
        } else {
            return false;
        }
    }

    //Affectation
    //Methode qui regle les problemes de tous les etudiants (en principe) --> probleme.
    public static void affectationFinale(int max){
        //On itere l'algorithme tant qu'il reste des etudiants a probleme
        ArrayList<Etudiant> al = new ArrayList<Etudiant>();
        al.addAll(listeEtudiantsAProbleme(max));
        System.out.println("Fait 1");
        ArrayList<Etudiant> al2 = new ArrayList<Etudiant>();
        al2.addAll(listeEtudiantsSansProbleme(max));
        System.out.println("Fait 2");
        while (al.size() != 0){
            //On prend le premier etudiant de la liste d'etudiants a probleme
            Etudiant et = new Etudiant(al.get(0).getId());
            //On regarde tous ses creneaux qui posent probleme
            for(int i = 0; i < et.coursProbleme(max).size();i++) {
                //On cherche dans la liste un etudiant pour qui ce creneau n'est pas un probleme
                System.out.println("i: "+i);
                for (int k = 0; k < al2.size(); k++) {
                    //On prend un etudiant de cette liste
                    Etudiant ch = new Etudiant(al2.get(k).getId());
                    Cours probleme = new Cours (et.coursProbleme(max).get(i).get(1).getNomCours());
                    System.out.println("k: "+k);
                    //On regarde si les deux peuvent changer
                    if (!peuventChanger(et,ch,et.coursProbleme(max).get(i), max)) {
                        echangeEtudiants(et, ch, probleme.participe(), ch.numeroTDMatiere(probleme.CM()).participe());
                        //On met a jour la liste d'etudiant a probleme et sans probleme
                        al.remove(et);
                        System.out.println(al);
                        al2.add(et);
                        break;
                    }
                    //Si la condition n'est pas remplie on passe au a l'etudiant sans probleme suivant
                }
            }
        }
    }

}
