package ru.itis.inform;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sorter {

    //сортировка массива чисел методом бинарного дерева
    public int[] sort(int[] array) {

        long timeBegin = System.nanoTime();
        Tree tree = new Tree();

        for (int i = 0; i < array.length; i++) {
            tree.insert(array[i]);
        }

        int[] out = new int[3];
        out[0] = array.length;

        array = tree.inOrderPrint();

        long timeEnd = System.nanoTime();

        int iter = tree.getCount();

        timeEnd -= timeBegin;

        out[1] = (int)timeEnd;
        out[2] = iter;

        return out;
    }

    //считывание массивов чисел из файлов
    public int[] read(String fileName, int n) {

        int[] array = new int[n];
        int i = 0;

        File file = new File(fileName);
        try {

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {

                int number = Integer.parseInt(scanner.next());
                array[i] = number;
                i++;
            }

            scanner.close();

        } catch (FileNotFoundException e) {

            new FileNotFoundException("There's no such file");

        }

        return array;
    }
}
