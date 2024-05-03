public class athlete {
    private String name;
    private String sex;
    private byte age;
    private String pays;

    public athlete(String name, String sex, byte age, String pays) {
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

    public byte getAge() {
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
}
