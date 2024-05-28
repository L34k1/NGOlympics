package com.isep.tentative;

public class Resultat {
    private int id;
    private char medaille;
    private boolean validation;

    public Resultat(int id, char medaille, boolean validation) {
        this.id = id;
        this.medaille = medaille;
        this.validation = validation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getMedaille() {
        return medaille;
    }

    public void setMedaille(char medaille) {
        this.medaille = medaille;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }
}
