package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Menu

        //Options du menu
        System.out.println("Que voulez-vous faire?");

        //test
       Cours Graphes = new Cours ("Graphes");
       Cours Java = new Cours ("Java");
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
       System.out.println(Cours.coursEnMemeTemps());











        /*
        int reponse;

        do {
            System.out.println("0. Quitter le programme");
            System.out.println("1. Lire les fichiers d'entree par defaut");
            System.out.println("2. Lire les fichiers d'entrée en entrant le nom de chaque fichier au clavier");
            System.out.println("3. Afficher l'affectattion des eleves dans les TD");
            System.out.println("4. Changer le nombre maximal d'eleves par TD");

            System.out.println(Cours.participe());

            Scanner scan = new Scanner(System.in);
            reponse = scan.nextInt();

           //On verifie que la réponse est correcte
            try  {
                if ( !(reponse >=1 && reponse <=4) )
                    throw new ReponseException ();
            }
            catch (ReponseException e){
                System.out.println("Merci de taper 1,2,3 ou 4 et de relancer le programme");
                scan.close();
            }



            switch (reponse){
                case (1) :
                    String fichierEtudiant = Etudiant.lireFichierEtu();
                    String fichierEdt = Cours.lireFichierEdt();
                    break;

                case (2) :

                    break;

                case (3) :

                    break;

                case (4) :

                    break;

                default:
                    System.out.println("Veuillez taper 0, 1, 2, 3 ou 4 s'il vous plait");
            }

        } while (reponse !=0);

        System.out.println("Au revoir.");

    }

*/

    }
}
