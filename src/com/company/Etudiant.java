package com.company;

import java.util.ArrayList;

public class Etudiant {
    private int id;
    private ArrayList<String> listeCours;

    public Etudiant (int numero, ArrayList<String> liste){
        id = numero;
        listeCours = liste;
    }
    public Etudiant (){
        id = "";
        listeCours = new ArrayList<String>();
    }
}
