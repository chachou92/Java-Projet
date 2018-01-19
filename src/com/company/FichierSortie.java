package com.company;

import jdk.management.cmm.SystemResourcePressureMXBean;

import javax.print.DocFlavor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class FichierSortie {


    /**
     * Retourne la liste de tous les cours.
     * @param max
     * @return listeFinale
     */
    public static ArrayList<ArrayList<String>> fichierFinal(int max) {
        ArrayList<Cours> listeCours = new ArrayList<Cours>();
        ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>>();
        ArrayList<String> ligne0 = new ArrayList<String>();
        ArrayList<ArrayList<String>> ie = LectureFichiers.infoEtudiant();
        ligne0 = ie.get(0);
        for (int i = 1; i < ligne0.size(); i++) {
            Cours c = new Cours(ligne0.get(i));
            listeCours.add(c);
            if (c.TD().size() != 0) {
                for (int k = 0; k < c.TD().size(); k++) {
                    listeCours.add(c.TD().get(k));
                }
            }
        }

        for (int i = 0; i < listeCours.size(); i++) {
            ArrayList<String> aux = new ArrayList<String>();
            aux.add(listeCours.get(i).getNomCours());
            if (listeCours.get(i).infoCoursOuTD() == 0) {
                aux.addAll(listeCours.get(i).participe());
                al.add(aux);
            } else {
                ArrayList<ArrayList<String>> affecteTD = new ArrayList<ArrayList<String>>();
                affecteTD = listeCours.get(i).CM().affecteTD(max);
                char indicechar = listeCours.get(i).getNomCours().charAt(listeCours.get(i).getNomCours().length() - 1);
                String indiceString = "";
                indiceString = indiceString + indicechar;
                int indiceint = Integer.parseInt(indiceString) - 1;
                aux.addAll(affecteTD.get(indiceint));
                al.add(aux);
            }
        }

        return al;
    }


    /**
     * Ecrit dans le fichier sortie.csv qui sera le fichier de sortie du programme.
     * @param max
     * @return fichierSortie
     */
    public static File fichierSortie(int max) {
        String resultat = "";
        //On met un point virgule entre chaque element de la sous liste et un retour a la ligne a la fin
        for(ArrayList<String> liste : FichierSortie.fichierFinal(max)){
            for(String str : liste){
                resultat = resultat+str + ";";
            }
            resultat= resultat+"\n";
        }
        //On met toutes les lignes dans une liste
        ArrayList<String> al = new ArrayList<String>(Arrays.asList(resultat.split("\n")));
        try {
            File file = new File (System.getProperty("user.dir") + "/fichierSortie.csv");
            file.createNewFile();
            Path path = Paths.get(System.getProperty("user.dir") + "/fichierSortie.csv");
            if(!(Files.exists(path))){
                //On cree le fichier si il n'existe pas encore
                Files.createFile(path);
                //On ecrit le contenu de la liste dans le fichier
                Files.write(path,al);
            }
            else{
                //Si il existe deja, on reecrit le nouveau contenu de la liste dans le fichier de sortie
                Files.write(path,al);
            }
            return file;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println ("Erreur dans la creation du fichier de sortie.");
        }
        return null;
    }

}
