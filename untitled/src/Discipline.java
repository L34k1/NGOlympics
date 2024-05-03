import java.util.ArrayList;

public class Discipline {
    private String nom;
    private ArrayList athletes;

    public Discipline(String nom, ArrayList athletes) {
        this.nom = nom;
        this.athletes = athletes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList getAthletes() {
        return athletes;
    }

    public void setAthletes(ArrayList athletes) {
        this.athletes = athletes;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "nom='" + nom + '\'' +
                ", athletes=" + athletes.toString() +
                '}';
    }
}
