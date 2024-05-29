package com.isep.tentative;

public class Resultat {
    private int id;
    private char medaille;
    private boolean validation;
    private int athlete_ID;
    private int epreuve_ID;
    private String score;

    public Resultat(int id, char medaille, boolean validation, int athlete_ID, int epreuve_ID, String score) {
        this.id = id;
        this.medaille = medaille;
        this.validation = validation;
        this.athlete_ID = athlete_ID;
        this.epreuve_ID = epreuve_ID;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public char getMedaille() {
        return medaille;
    }

    public boolean isValidation() {
        return validation;
    }

    public int getAthlete_ID() {
        return athlete_ID;
    }

    public int getEpreuve_ID() {
        return epreuve_ID;
    }

    public String getScore() {return score;}
}
