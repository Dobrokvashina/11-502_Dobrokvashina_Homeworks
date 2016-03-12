package ru.itis.inform;

public class Human {
    private String name;
    private int age;

    public boolean compareTo(Human human){
       return this.age == human.getAge();
    }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        String nameAge = this.name + " " + this.age;
        return nameAge;

    }
}
