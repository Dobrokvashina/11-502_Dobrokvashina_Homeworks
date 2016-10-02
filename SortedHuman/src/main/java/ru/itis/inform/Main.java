package ru.itis.inform;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        HumanReaderWriter readerWriter = new HumanReaderWriter();

        File file = new File("C:\\Users/Саоша/HumanInput.txt");

        LinkedList<Human> humans = readerWriter.readHumans(file);


        HumanSorter sorter = new HumanSorter();

        humans = sorter.sort(humans);

        readerWriter.writeHumans("C:\\Users/Саоша/HumanOutput.txt", humans);

    }
}


