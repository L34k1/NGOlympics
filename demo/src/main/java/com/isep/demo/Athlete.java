package com.isep.demo;

public class Athlete {
    private String name;
    private String sex;
    private int age;
    private String pays;

    public Athlete(String name, String sex, int age, String pays) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.pays = pays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "src.main.java.com.isep.demo.athlete{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", pays='" + pays + '\'' +
                '}';
    }

    public static void createAthlete(String name, String sex, byte age, String pays) {
        Athlete newathlete = new Athlete(name,sex,age,pays);
        newathlete.toString();
        System.out.println("src.main.java.com.isep.demo.athlete created");
    }
}
