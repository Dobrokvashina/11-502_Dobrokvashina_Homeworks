package ru.itis.inform;

public class Main {

    public static void main(String[] args) {

        FareySequenceGeneratorLinkedList numbers = new FareySequenceGeneratorLinkedList();

        RationalNumberLinkedList rationalNumbers = numbers.generate(10);

        rationalNumbers.show();
    }
}
