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
        String[] parts = disciplineString.split(":");
        if (parts.length == 1) {
            // If only the name is provided, assume ID as 0
            return new Discipline(0, parts[0]);
        } else if (parts.length == 2) {
            try {
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                return new Discipline(id, name);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid discipline ID: " + parts[0], e);
            }
        } else {
            throw new IllegalArgumentException("Invalid discipline string format: " + disciplineString);
        }
    }


}
