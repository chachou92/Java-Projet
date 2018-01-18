package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Menu

        //Options du menu
        System.out.println("Que voulez-vous faire?");


      //test
       Cours Graphes = new Cours ("Graphes_2");
       /*Cours Java = new Cours ("Java");
       Cours Communication = new Cours ("Communication");
       Cours GenieLogiciel = new Cours ("GenieLogiciel");
       Cours LV2Espagnol = new Cours ("LV2Espagnol");
       int i = Graphes.indice();
       System.out.println("Java");
       System.out.println(Java.nombreTD());
       System.out.println("Communication");
       System.out.println(Communication.nombreTD());
       System.out.println("GenieLogiciel");
       System.out.println(GenieLogiciel.nombreTD());
       System.out.println("LV2Espagnol");
       System.out.println(LV2Espagnol.nombreTD());

       System.out.println("Les cours qui ont les memes creneaux sont:");
       System.out.println(LectureFichiers.coursEnMemeTemps());

       System.out.println("TD de Communication");
       System.out.println(Java.TD());

       System.out.println("AffecteTD:");
       System.out.println(Java.affecteTD(39));
       System.out.println("Participe:");
       System.out.println(Java.participe());
       System.out.println((Java.participe()).size());
       Cours LV2Espagnol_1 = new Cours ("LV2Espagnol_1");
       System.out.println("courEnConflit de LV2Espagnol_1");
       System.out.println(LV2Espagnol_1.coursEnConflit());
       System.out.println(LectureFichiers.coursEnMemeTemps());*/

       Etudiant Axel = new Etudiant ("1");
       /*System.out.println("Cours inscrit:");
       System.out.println(Axel.coursInscrit(40));
       System.out.println("Cours probleme:");
       System.out.println(Axel.coursProbleme(40));*/

       System.out.println("Etudiants");
       //System.out.println(Affectation.listeEutudiants());
       //System.out.println(Axel.verifCoursEnConflit(40));
       System.out.println("Etudiants a problemes");
       //System.out.println(Affectation.listeEtudiantsAProbleme(39));
       //System.out.println("Etudiants sans problemes");
       //System.out.println(Affectation.listeEtudiantsSansProbleme(39));
        //System.out.println(Graphes.CM());

        Etudiant Elie = new Etudiant ("92");
       // System.out.println(Elie.coursProbleme(40));

        Etudiant Elo = new Etudiant ("4");
       // System.out.print(Elo.coursProbleme(40));
        //System.out.println(Elo.coursInscrit(40));
        ArrayList<Cours> c = new ArrayList<Cours>();
        Cours J = new Cours("Java_2");
        Cours IA = new Cours ("IA_3");
        c.add(J);
        c.add(IA);

        ArrayList<Cours> d = new ArrayList<Cours>();
        d.add(J);
        d.add(IA);

        //System.out.println(J.contenuDansListe(c));
        Cours Be = new Cours("Java_2");
        //System.out.println(Be.equals(J));
        /*System.out.println(c);
        System.out.println(d);
        System.out.println(c == d);*/
        System.out.println(Affectation.peuventChanger(Elie, Elo,c,40));






       /* int reponse;

        do {
            System.out.println("0. Quitter le programme");
            System.out.println("1. Lire les fichiers d'entree par defaut");
            System.out.println("2. Lire les fichiers d'entrée en entrant le nom de chaque fichier au clavier");
            System.out.println("3. Afficher l'affectattion des eleves dans les TD");
            System.out.println("4. Changer le nombre maximal d'eleves par TD");



            Scanner scan = new Scanner(System.in);
            reponse = scan.nextInt();

           //On verifie que la réponse est correcte
            try  {
                if ( !(reponse >=0 && reponse <=4) )
                    throw new ReponseException ();

               switch (reponse){
                  case (1) :
                     String fichierEtudiant = LectureFichiers.lireFichierEtu();
                     String fichierEdt = LectureFichiers.lireFichierEdt();
                     System.out.println("Le fichier des etudiants:");
                     System.out.println(fichierEtudiant);
                     System.out.println("Le fichier de l'emploi du temps");
                     System.out.println(fichierEdt);

                     break;

                  case (2) :

                     break;

                  case (3) :

                     break;

                  case (4) :

                     break;

               }
            }
            catch (ReponseException e){
                System.out.println("Merci de taper 0, 1, 2, 3 ou 4 et de relancer le programme");
                scan.close();
            }




        } while (reponse !=0);

        System.out.println("Au revoir.");

    } */


    }
}

