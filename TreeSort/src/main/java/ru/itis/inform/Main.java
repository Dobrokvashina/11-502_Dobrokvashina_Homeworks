package ru.itis.inform;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {

        int[][] output = new int[200][3];
        Sorter sorter = new Sorter();

        for (int i = 0; i < 100; i++) {
            int number = 100 * (i + 1);
            String fileName = "C:/Users/Саоша/IdeaProjects/TreeSort/src/main/input/Array" + number + ".txt";
            int[] array = sorter.read(fileName, number);
            output[i] = sorter.sort(array);
        }


        //вывод полученных данных (размера массива, времени работы и кол-ва итераций) в файл
        String fileName = "C:/Users/Саоша/IdeaProjects/TreeSort/src/main/otput.txt";
        File file = new File(fileName);

        try {
            if(!file.exists()){
                file.createNewFile();
            }


            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {

                out.println("size       time          iteration");
                for (int i = 0; i < 100; i++) {
                    out.println(output[i][0] + "        " + output[i][1] + "      " + output[i][2]);
                }
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

    }
}
