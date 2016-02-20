package ru.itis.inform;

public class Main {

    public static void main(String[] args) {

        FareyLinkedListImpl rationalNumbers = new FareyLinkedListImpl();

        LinkedList<RationalNumber> listOfNumbers = rationalNumbers.runFarey(10);

        Iterator<RationalNumber> current = listOfNumbers.iterator();

        while(current.hasNext()) {
            System.out.print(current.peekNext().getA() + "/" + current.peekNext().getB() + "  ");
            current.next();
        }
    }
}
