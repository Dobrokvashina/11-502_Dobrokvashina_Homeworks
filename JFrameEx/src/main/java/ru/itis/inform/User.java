package ru.itis.inform;

/**
 * Created by Саоша on 25.11.2016.
 */
public class User {
    private int id;
    private String name;
    private String surname;
    private int group;
    private boolean isKazan;

    public User(int id, String name, String surname, int group, boolean isKazan) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.isKazan = isKazan;
    }

    public User(String name, String surname, int group, boolean isKazan) {
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.isKazan = isKazan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public boolean isKazan() {
        return isKazan;
    }

    public void setKazan(boolean kazan) {
        isKazan = kazan;
    }

    public int getId() {
        return id;
    }

    public String[] toStringAr() {
        String[] str = null;
        str[0] = "" + id;
        str[1] = name;
        str[2] = surname;
        str[3] = "" +group;
        str[4] = String.valueOf(isKazan);
        return str;
    }
}
