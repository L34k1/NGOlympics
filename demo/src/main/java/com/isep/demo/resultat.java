package src.main.java.com.isep.demo;

public class resultat {
    private int score;
    private String medal;
    private boolean validated;

    public resultat(int score, String medal, boolean validated) {
        this.score = score;
        this.medal = medal;
        this.validated = validated;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
