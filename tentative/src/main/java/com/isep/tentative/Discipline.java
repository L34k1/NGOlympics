package com.isep.tentative;

public class Discipline {
    private int id;
    private String name;

    public Discipline(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    // Static factory method to create a Discipline from a String
    public static Discipline fromString(String disciplineString) {
        // Assuming the string format is "id:name"
        String[] parts = disciplineString.split(":");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        return new Discipline(id, name);
    }
}
