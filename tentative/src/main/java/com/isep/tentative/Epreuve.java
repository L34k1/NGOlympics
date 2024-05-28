package com.isep.tentative;

import java.time.LocalDate;

public class Epreuve {
    private int id;
    private LocalDate date;
    private String location;
    private Discipline discipline;

    public Epreuve(int id, LocalDate date, String location, Discipline discipline) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.discipline = discipline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
