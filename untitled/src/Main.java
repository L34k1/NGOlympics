import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Athlete jack = new Athlete("Jack","mâle",99,"france");
        Athlete jean = new Athlete("Jean","mâle",237,"france");
        ArrayList<Athlete> athletes = new ArrayList<>();
        athletes.add(jack);
        athletes.add(jean);
        System.out.println(athletes.toString());
        Discipline discipline = new Discipline("Escrime",athletes);
        System.out.print(discipline.toString());
        Evenement EscrimeMixte = new Evenement(athletes, ,discipline,"EscrimeMixte");
    }
}
