package com.company;

import jdk.management.cmm.SystemResourcePressureMXBean;

import javax.print.DocFlavor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class FichierSortie {

    //On prend la liste de tous les cours
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


    //On ecrit dans un fichier sortie.csv
    public static File fichierSortie(int max) {
        try {
            File file = new File(System.getProperty("user.dir") + "/fichierSortie.csv");
            for (int i = 0; i < fichierFinal(max).size(); i++) {
                for (String str : fichierFinal(max).get(i)) {

                    //File file = new File(System.getProperty("user.dir")+"/fichierSortie.csv");
                    file.createNewFile();

                    FileWriter fw = new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(str);
                    //bw.write("\n");
                    bw.close();
                }
            }
            return file;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
