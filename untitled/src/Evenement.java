import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Evenement {
    private ArrayList athletes;
    private Calendar date;
    private Discipline discipline;
    private String nom;

    public Evenement(ArrayList athletes, Calendar date, Discipline discipline, String nom) {
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
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
                "athletes=" + athletes.toString() +
                ", date=" + date +
                ", discipline=" + discipline.toString() +
                ", nom='" + nom + '\'' +
                '}';
    }

    public static void createEvent(ArrayList athletes, Calendar date, Discipline discipline, String nom) {
        Evenement newevent = new Evenement(athletes, date, discipline, nom);
        newevent.toString();
        System.out.println("event created");
    }
}
