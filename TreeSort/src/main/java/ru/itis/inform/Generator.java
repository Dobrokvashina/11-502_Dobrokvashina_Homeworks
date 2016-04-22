package ru.itis.inform;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Generator {

    //создание массивов случайных чисел
    public static void main(String[] args) {
        int n = 100;

        while (n <= 10000) {

            generate(n);
            n +=100;
        }
    }

    // генератор случайных чисел
    private static void generate(int n) {

        Random ran = new Random();

        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = ran.nextInt(100000);
        }

        out(array);
    }

    //запись массива в файл .txt
    private static void out(int[] array) {
        String filename = "C:/Users/Саоша/IdeaProjects/TreeSort/src/main/input/Array";
        filename += array.length;
        filename += ".txt";

        File file = new File(filename);

        try {
            if(!file.exists()){
                file.createNewFile();
            }


            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {

                for (int i = 0; i < array.length; i++) {
                    out.println(array[i]);
                }
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
