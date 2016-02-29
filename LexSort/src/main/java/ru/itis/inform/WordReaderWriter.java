package ru.itis.inform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WordReaderWriter {

    public static LinkedList<String> readWords(File fileName) {

        LinkedList<String> list = new LinkedList<String>();

        try {

            Scanner scanner = new Scanner(fileName);

            while (scanner.hasNext()) {

                String word = scanner.next();

                list.add(word);
            }

            scanner.close();

        } catch (FileNotFoundException e) {

            new FileNotFoundException("There's no such file");

        }

        return list;

    }

    public static void writeWords(String fileName, LinkedList<String> list) {

        File file = new File(fileName);

        try {
            if(!file.exists()){
                file.createNewFile();
            }


            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            Iterator<String> iterator = list.iterator();

            try {

                while (iterator.hasNext()) {
                    out.println(iterator.peekNext());
                    iterator.next();
                }
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
