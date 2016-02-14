package ru.itis.inform;

public class Main {

    public static void main(String[] args) {

        FareySequenceGeneratorArrayImpl array = new FareySequenceGeneratorArrayImpl();
        RationalNumber numbers[] = array.generate(10);

        int j = 0;
        while (numbers[j + 1] != null) {
            System.out.print(numbers[j].getA() + "/" + numbers[j].getB() + ", ");
            j++;
        }

        System.out.print(numbers[j].getA() + "/" + numbers[j].getB() + ".");
    }
}

