package ru.itis.inform;

import java.io.File;

public class Main {


    public static void main(String[] args) {

        WordReaderWriter readerWriter = new WordReaderWriter();

        File file = new File("C:\\Users/Саоша/WordsInput.txt");

        LinkedList<String> words = readerWriter.readWords(file);


        WordSorter sorter = new WordSorter();

        words = sorter.sort(words);

        Iterator<String> iterator = words.iterator();


        readerWriter.writeWords("C:\\Users/Саоша/WordsOutput.txt", words);

    }
}
