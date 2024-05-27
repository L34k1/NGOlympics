package com.isep.tentative;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Athlete {
    private IntegerProperty id;
    private StringProperty name;
    private BooleanProperty gender;
    private StringProperty country;
    private StringProperty birthdate;

    public Athlete(int id, String name, boolean gender, String country, LocalDate birthdate) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleBooleanProperty(gender);
        this.country = new SimpleStringProperty(country);
        this.birthdate = new SimpleStringProperty(String.valueOf(birthdate));
    }

    // Getters and setters for id
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    // Getters and setters for name
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    // Getters and setters for gender
    public boolean isGender() {
        return gender.get();
    }

    public void setGender(boolean gender) {
        this.gender.set(gender);
    }

    public BooleanProperty genderProperty() {
        return gender;
    }

    // Getters and setters for country
    public String getCountry() {
        return country.get();
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public StringProperty countryProperty() {
        return country;
    }

    // Getters and setters for birthdate
    public String getBirthdate() {
        return birthdate.get();
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate.set(String.valueOf(birthdate));
    }

    public StringProperty birthdateProperty() {
        return birthdate;
    }

    public StringProperty formattedDobProperty() {
        // Assuming birthdate is in a standard format, you can adjust this formatter as needed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = LocalDate.parse(birthdate.get(), formatter).format(formatter);
        return new SimpleStringProperty(formattedDate);
    }
}
