package src.main.java.com.isep.demo;

import java.util.ArrayList;
import java.util.Date;

public class Evenement {
    private ArrayList athletes;
    private Date date;
    private discipline discipline;
    private String nom;

    public Evenement(ArrayList athletes, Date date, discipline discipline, String nom) {
        this.athletes = athletes;
        this.date = date;
        this.discipline = discipline;
        this.nom = nom;
    }

    public ArrayList getAthletes() {
        return athletes;
    }

    public void setAthletes(ArrayList athletes) {
        this.athletes = athletes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(discipline discipline) {
        this.discipline = discipline;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "src.main.java.com.isep.demo.evenement{" +
                "athletes=" + athletes +
                ", date=" + date +
                ", discipline=" + discipline +
                ", nom='" + nom + '\'' +
                '}';
    }

    public static void createEvent(ArrayList athletes, Date date, discipline discipline, String nom) {
        src.main.java.com.isep.demo.Evenement newevent = new src.main.java.com.isep.demo.Evenement(athletes, date, discipline, nom);
        newevent.toString();
        System.out.println("event created");
    }
}
