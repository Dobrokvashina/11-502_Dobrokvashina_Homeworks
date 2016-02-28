package ru.itis.inform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HumanReaderWriter {

    public static LinkedList<Human> readHumans(File fileName) {

        LinkedList<Human> list = new LinkedList<Human>();

        try {

            Scanner scanner = new Scanner(fileName);

            while (scanner.hasNext()) {

                String name = scanner.next();

                int age = Integer.parseInt(scanner.next());

                Human human = new Human(name, age);

                list.add(human);
            }

            scanner.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        }

        return list;

    }

    public static void writeHumans(String fileName, LinkedList<Human> list) {

        File file = new File(fileName);

        try {
            if(!file.exists()){
                file.createNewFile();
            }


            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            Iterator<Human> iterator = list.iterator();

            try {

                while (iterator.hasNext()) {
                    out.println(iterator.peekNext().toString());
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
