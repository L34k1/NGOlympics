import java.util.ArrayList;

public class discipline {
    private String nom;
    private ArrayList athletes;

    public discipline(String nom, ArrayList athletes) {
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
}
