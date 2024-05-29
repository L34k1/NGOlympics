package com.isep.tentative;

import java.time.LocalDate;

public class Epreuve {
    private int id;
    private LocalDate date;
    private String location;
    private Discipline discipline;
    private String name;
    private int discipline_ID; // Corrected property name
    private String athlete_ID_List;

    public Epreuve(int id, LocalDate date, String location, Discipline discipline, String name, int discipline_ID, String athlete_ID_List) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.discipline = discipline;
        this.name = name;
        this.discipline_ID = discipline_ID;
        this.athlete_ID_List = athlete_ID_List;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscipline_ID() {
        return discipline_ID;
    }

    public void setDiscipline_ID(int discipline_ID) {
        this.discipline_ID = discipline_ID;
    }

    public String getAthlete_ID_List() {
        return athlete_ID_List;
    }

    public void setAthlete_ID_List(String athlete_ID_List) {
        this.athlete_ID_List = athlete_ID_List;
    }
}
