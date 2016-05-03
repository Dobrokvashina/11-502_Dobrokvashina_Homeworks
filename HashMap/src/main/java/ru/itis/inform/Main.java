package ru.itis.inform;


public class Main {
    public static void main(String[] args) {

        HashMap map = new HashMap(3);

        System.out.println("----Исходник----");

        map.put("cat", "Kitty");
        map.put("cat", "Marshmalloww");
        map.put("god", "tuk");
        map.put("dog", "tuk");
        map.put("ogd", "whhtooo");
        map.put("gdo", "ded pehto");
        map.put("cow", "Mumu");
        map.put("man", "Jack");
        map.put("nam", "Alex");

        map.show();

        System.out.println("---Удаление Барбоса и Джека -----");

        map.delete("dog", "tuk");
        map.delete("man", "Jack");

        map.show();

        System.out.println("----Попытка удалить элемент, которого нет в map----");
        map.delete("Dog", "Mumu");
    }
}
