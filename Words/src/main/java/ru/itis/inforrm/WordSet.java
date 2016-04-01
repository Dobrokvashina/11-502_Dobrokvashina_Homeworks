package ru.itis.inforrm;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class WordSet {

    private Node first;

    private WordSet() {
        this.first = null;
    }

    private void addFirst(String element) {
        this.first = new Node(element);
    }

    public Node getFirst() {
        return first;
    }

    //создание списка по массиву строк arr
    public WordSet(String[] arr) {

        Node first = new Node(arr[0]);
        this.first = first;

        for (int i = 1; i < arr.length; i++) {
            insert(arr[i]);
        }
    }

    //формирование одного списка из двух существующих w1, w2 (метод слияния)
    public WordSet(WordSet w1, WordSet w2) {

        Node start1 = w1.getFirst();
        Node start2 = w2.getFirst();

        if (start1 != null && start2 != null) {
            if (start1.getValue().compareTo(start2.getValue()) < 0) {
                this.first = new Node(start1.getValue());
                start1 = start1.getNext();
            } else {
                this.first = new Node(start2.getValue());
                start2 = start2.getNext();
            }
        } else {
            if (start1 != null) {
                this.first = new Node(start1.getValue());
                start1 = start1.getNext();
            } else {
                this.first = new Node(start2.getValue());
                start2 = start2.getNext();
            }
        }


        while (start1 != null) {
            insert(start1.getValue());
            start1 = start1.getNext();
        }
        while (start2 != null) {
            insert(start2.getValue());
            start2 = start2.getNext();
        }
    }

    //вывод содержимого в текстовый файл с именем filename
    public void out(String filename) {

        File file = new File(filename);

        try {
            if(!file.exists()){
                file.createNewFile();
            }


            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            Node current = first;

            try {

                while (current != null) {
                    out.println(current.getValue());
                    current = current.getNext();
                }
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    //вставка слова в список без нарушения его упорядоченности.
    //Если такое слово в списке присутствует, то ничего не происходит
    public void insert(String word) {
        Node start = first;
        String current = start.getValue().toUpperCase();

        String wordUp = word.toUpperCase();

        Node newNode = new Node(word);

        if (first.getValue().toUpperCase().compareTo(wordUp) > 0) {
            newNode.setNext(first);
            first = newNode;
        } else {
            while ((start.getNext() != null) && (start.getNext().getValue().toUpperCase().compareTo(wordUp) <= 0)) {
                start = start.getNext();
                current = start.getValue().toUpperCase();
            }

            if (current.compareTo(wordUp) != 0) {
                Node next = start.getNext();
                start.setNext(newNode);
                newNode.setNext(next);
            }
        }
    }

    //удаление слова из списка. Если такого слова нет - ничего не происходит
    public void delete(String word) {

        Node start = first;
        Node previous = null;

        while ((start != null) && (start.getValue().compareTo(word) != 0)) {
            previous = start;
            start = start.getNext();
        }

        if (previous != null) {
            if ((start != null) && (start.getValue().compareTo(word) == 0)) {
                previous.setNext(start.getNext());
            }
        } else {
            first = start.getNext();
        }
    }

    // формирования нового списка слов, имеющих фиксированную длину l
    public WordSet newWordSetByWordLength(int l) {
        WordSet newSet = new WordSet();

        Node current = first;

        while (current != null) {
            if (current.getValue().length() == l) {
                if (newSet.getFirst() == null) {
                    newSet.addFirst(current.getValue());
                } else {
                    newSet.insert(current.getValue());
                }
            }
            current = current.getNext();
        }

        return newSet;
    }

    //разбиение списка слов на два, соответственно содержащих слова, начинающиеся с гласных и согласных букв. Метод
    //возвращает массив из двух ссылок WordSet, являющихся началами
    //соответствующих линейных списков.
    public WordSet [] vowelDivide() {
        WordSet[] array = new WordSet[2];
        array[0] = new WordSet();
        array[1] = new WordSet();

        Node current = first;

        while (current != null) {
            if (isGlasniy(current.getValue().charAt(0))) {
                if (array[0].getFirst() == null)
                    array[0].addFirst(current.getValue());
                else
                    array[0].insert(current.getValue());
            } else {
                if (array[1].getFirst() == null)
                    array[1].addFirst(current.getValue());
                else
                    array[1].insert(current.getValue());
            }
            current = current.getNext();
        }

        return  array;
    }

    public boolean isGlasniy(char bukva) {
        switch (bukva) {
            case 'a':
            case 'e':
            case 'y':
            case 'u':
            case 'i':
            case 'o':
            case 'A':
            case 'E':
            case 'Y':
            case 'U':
            case 'I':
            case 'O':
                return true;
            default:
                return false;
        }
    }

    public boolean isPalindorm(String word) {
        int size = word.length();

        boolean flag = true;
        int count = 0;
        word = word.toUpperCase();

        while ((count < size/2) && flag) {
            if (word.charAt(count) != word.charAt(size - count - 1)){
                flag = false;
            }
            count++;
        }

        return flag;
    }

    // удаления из списка слов, которые одинаково читаются как слева направо, так и справа налево
    public void removePalindrom() {
        Node current = first;
        while (current != null) {
            if (isPalindorm(current.getValue())) {
                delete(current.getValue());
            }
            current = current.getNext();
        }
    }

    public void show() {
        Node start = first;

        while (start != null) {
            System.out.println(start.getValue());
            start = start.getNext();
        }
    }
}
