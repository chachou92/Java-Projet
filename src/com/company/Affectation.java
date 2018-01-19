package com.company;



import java.util.ArrayList;
import static java.lang.Math.min;

/**
 * Classe qui gère l'affectattion des etudiants aux groupes de TD
 en tenant compte des contraintes de cours sur le même creneau.
 */
public abstract class Affectation {


    /**
     * Instancie les objets Etudiants a partir du fichier etu.csv
     * @return Une arrayliste d'étudiants.
     */
    public static ArrayList<Etudiant> listeEutudiants() {
        ArrayList<Etudiant> listeEtu = new ArrayList<Etudiant>();
        ArrayList<ArrayList<String>> ie = LectureFichiers.infoEtudiant();
        for (int i = 1; i < ie.size(); i++) {
            Etudiant e = new Etudiant(ie.get(i).get(0));
            listeEtu.add(e);
        }
        return listeEtu;
    }

    /**
     * Retourne la liste des etudiants qui ont des conflits.
     * @param max
     * @return Une arrayliste d'étudiants avec des cours en conflit.
     */
    public static ArrayList<Etudiant> listeEtudiantsAProbleme(int max) {
        ArrayList<Etudiant> etusAProblemes = new ArrayList<Etudiant>();
        for (int i = 0; i < listeEutudiants().size(); i++) {
            if (listeEutudiants().get(i).verifCoursEnConflit(max)) {
                etusAProblemes.add(listeEutudiants().get(i));
            }
        }
        return etusAProblemes;
    }

    /**
     * Retourne la liste des etudiants qui n'ont pas de conflits.
     * @param max
     * @return Une arrayliste d'étudiants sans cours en conflit.
     */
    public static ArrayList<Etudiant> listeEtudiantsSansProbleme(int max) {
        ArrayList<Etudiant> etusSansProblemes = new ArrayList<Etudiant>();
        for (int i = 0; i < listeEutudiants().size(); i++) {
            if (!(listeEutudiants().get(i).verifCoursEnConflit(max))) {
                etusSansProblemes.add(listeEutudiants().get(i));
            }
        }
        return etusSansProblemes;
    }

    /**
     * Echange deux identifiants d'etudiants d'une liste a l'autre.
     * @param e1
     * @param e2
     * @param al1
     * @param al2
     */
    public static void echangeEtudiants(Etudiant e1, Etudiant e2, ArrayList<String> al1, ArrayList<String> al2) {
        al1.remove(e1.getId());
        al2.add(e1.getId());
        al2.remove(e2.getId());
        al1.add(e2.getId());
    }

    /**
     * Indique si deux listes d'etudiants sont egales ou pas.
     * @param c1
     * @param c2
     * @return Vrai si deux listes sont égales, faux sinon.
     */
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


    /**
     * Nous indique si deux etudiants peuvent s'echanger leurs groupes de TD.
     * @param e1
     * @param e2
     * @param c
     * @param max
     * @return Vrai si deux étudiants peuvent changer de groupe de TD, faux sinon.
     */
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

    /**
     * Affecte les etudiants en tenant compte de tous les problemes.
     * @param max
     */
    public static void affectationFinale(int max){
        //On itere l'algorithme tant qu'il reste des etudiants a probleme
        ArrayList<Etudiant> al1 = listeEtudiantsAProbleme(max);
        System.out.println("Fait 1");
        ArrayList<Etudiant> al2 = listeEtudiantsSansProbleme(max);
        System.out.println("Fait 2");
        int chachou = 0;
        int taille = al2.size();
        while (al1.size() != 0){
            System.out.println("chachou: "+chachou);
            //On prend le premier etudiant de la liste d'etudiants a probleme
            Etudiant et = new Etudiant(al1.get(0).getId());
            //On regarde tous ses creneaux qui posent probleme
            for(int i = 0; i < et.coursProbleme(max).size();i++) {
                //On cherche dans la liste un etudiant pour qui ce creneau n'est pas un probleme
                //System.out.println("i = "+i);
                for (int k = 0; k < taille; k++) {
                   // System.out.println("k = "+k);
                    //On prend un etudiant de cette liste
                    Etudiant ch = new Etudiant(al2.get(k).getId());
                    Cours probleme = new Cours (et.coursProbleme(max).get(i).get(1).getNomCours());
                    //On regarde si les deux peuvent changer
                    if(peuventChanger(et,ch,et.coursProbleme(max).get(i), max)){
                        System.out.println("et: "+et);
                        System.out.println("ch: "+ch);
                        System.out.println("probleme machi: "+probleme.listeInscrits(max));
                        System.out.println("ch.numero truc: "+ch.numeroTDMatiere(probleme.CM(),max).listeInscrits(max));//Le probleme est la
                        echangeEtudiants(et,ch, probleme.listeInscrits(max), ch.numeroTDMatiere(probleme.CM(),max).listeInscrits(max));
                        //On met a jour la liste d'etudiant a probleme et sans probleme
                        al1.remove(al1.get(0));
                        System.out.println("al1: "+al1);
                        al2.add(et);
                        System.out.println("al2: "+al2);
                        break;
                    //Si la condition n'est pas remplie on passe au a l'etudiant sans probleme suivant
                    }
                }
            }
            chachou +=1;
        }
    }

}
