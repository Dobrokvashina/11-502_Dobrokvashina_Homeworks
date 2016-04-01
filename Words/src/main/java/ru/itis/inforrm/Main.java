package ru.itis.inforrm;

public class Main {

    public static void main(String[] args) {

        String[] words = new String[11];

        words[0] = "Hello";
        words[1] = "apple";
        words[2] = "Hurray";
        words[3] = "ABBA";
        words[4] = "MoreSleep";
        words[5] = "tdadt";
        words[6] = "kill";
        words[7] = "Bill";
        words[8] = "who";
        words[9] = "watches";
        words[10] = "Watchmen";

        WordSet test = new WordSet(words);


        System.out.println("------Основной массив слов------");

        test.show();
        System.out.println("------Добавление слов bulka и elseWhat------");

        test.insert("bulka");
        test.insert("elseWhat");
        test.insert("Who");
        test.insert("abba");

        test.show();
        System.out.println("-----Удаление палиндромов-----");

        test.removePalindrom();

        test.show();
        System.out.println("------Выделение массива слов длиной 5 из исходного------");

        WordSet newSet5 = test.newWordSetByWordLength(5);

        newSet5.show();
        System.out.println("------Разделение на два массива по первой букве (гласная)------");

        WordSet[] array = test.vowelDivide();

        array[0].show();
        System.out.println("------(согласная)------");

        array[1].show();
        System.out.println("------Слияние двух предыдущих массивов------");

        WordSet again = new WordSet(array[0], array[1]);

        again.show();
        System.out.println("------Удаление слова MoreSleep------");

        test.delete("MoreSleep");
        test.delete("okey");

        test.show();
        System.out.println("------Запись массива в файл TestOutput.txt------");

        test.out("C:/Users/Саоша/IdeaProjects/Words/src/main/TestOutput.txt");
    }
}
