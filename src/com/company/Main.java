
package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int max = 50;

    public static void main(String[] args) {

        //Menu

        //Options du menu
        System.out.println("Que voulez-vous faire?");

        int reponse;

        do {
            System.out.println("0. Quitter le programme");
            System.out.println("1. Lire les fichiers d'entree par defaut");
            System.out.println("2. Lire les fichiers d'entrée en entrant le nom de chaque fichier au clavier");
            System.out.println("3. Afficher l'affectattion des eleves dans les TD");
            System.out.println("4. Changer le nombre maximal d'eleves par TD");

            Scanner scan = new Scanner(System.in);
            reponse = scan.nextInt();
            //On donne une valeur par defaut a max

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
                        System.out.println("Saisir le nom du fichier d'etudiants.");
                        String bidon = scan.nextLine();
                        String reponse1 = scan.nextLine();
                        System.out.println("Saisir le nom du fichier d'emploi du temps.");
                        String reponse2 = scan.nextLine();

                        String fichierEtudiant2 = LectureFichiers.lireFichierEtu(reponse1);
                        String fichierEdt2 = LectureFichiers.lireFichierEdt(reponse2);
                        System.out.println("Le fichier des etudiants:");
                        System.out.println(fichierEtudiant2);
                        System.out.println("Le fichier de l'emploi du temps");
                        System.out.println(fichierEdt2);

                        break;

                    case (3) :

                        //Normalement, on lance l'affectattion et on affiche dans le fichier de sortie.
                        //Affectation.affectationFinale(max);

                        //On cree le fichier de sortie avec l'affectation
                        //FichierSortie.fichierSortie(max);
                        //System.out.println(FichierSortie.fichierFinal(max));

                        break;

                    case (4) :
                        System.out.println("Saisir le nombre maximum d'eleves par TD.");
                        int nb= scan.nextInt();
                        max = nb;

                        break;

                }
            }
            catch (ReponseException e){
                System.out.println("Merci de taper 0, 1, 2, 3 ou 4 et de relancer le programme");
                scan.close();
            }




        } while (reponse !=0);

        System.out.println("Au revoir.");

    }
}