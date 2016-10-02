package ru.itis.inform;


import java.io.File;

public class Main {

    static MyThread first;
    static MyThread second;
    static MyThread third;
    static MyThread fourth;


    public static void main(String[] args) {

        first = new MyThread("C:/Users/Саоша/IdeaProjects/Threads/src/main/input/first.txt");
        second = new MyThread("C:/Users/Саоша/IdeaProjects/Threads/src/main/input/second.txt");
        third = new MyThread("C:/Users/Саоша/IdeaProjects/Threads/src/main/input/third.txt");
        fourth = new MyThread("C:/Users/Саоша/IdeaProjects/Threads/src/main/input/fourth.txt");
        first.start();
        second.start();
        third.start();
        fourth.start();
        try {
            first.join();
            second.join();
            third.join();
            fourth.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Collector collector = new Collector();

        collector.collectFromTo("C:/Users/Саоша/IdeaProjects/Threads/src/main/input/first.txt", "C:/Users/Саоша/IdeaProjects/Threads/src/main/input/second.txt", "C:/Users/Саоша/IdeaProjects/Threads/src/main/input/third.txt",
                "C:/Users/Саоша/IdeaProjects/Threads/src/main/input/fourth.txt", "C:/Users/Саоша/IdeaProjects/Threads/src/main/finalOutput.txt");
    }


}
    class MyThread extends Thread
    {
        private String fileName;

        public MyThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run()
        {
            HumanReaderWriter readerWriter = new HumanReaderWriter();

            File file = new File(fileName);

            LinkedList<Human> humans = readerWriter.readHumans(file);


            HumanSorter sorter = new HumanSorter();

            humans = sorter.sort(humans);

            readerWriter.writeHumans(fileName, humans);
        }
    }


