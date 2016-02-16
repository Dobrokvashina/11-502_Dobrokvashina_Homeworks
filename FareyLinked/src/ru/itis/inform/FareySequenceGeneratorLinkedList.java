package ru.itis.inform;


public class FareySequenceGeneratorLinkedList {

    public FareySequenceGeneratorLinkedList() {
    }

    public RationalNumberLinkedList generate(int n) {

        RationalNumberLinkedList listOfNumbers = new RationalNumberLinkedList();

        RationalNumber first = new RationalNumber(0, 1);
        RationalNumber last = new RationalNumber(1, 1);

        listOfNumbers.add(last);
        listOfNumbers.add(first);


        int i = 2;

        while (i <= n) {

            RationalNumberNode current = listOfNumbers.getFirst();

            RationalNumberNode nextOne;

            while (current.getNext() != null) {

                nextOne = current.getNext();

                if (current.getValueB() + nextOne.getValueB() == i) {
                    RationalNumber newOne = new RationalNumber(current.getValueA() + nextOne.getValueA(), current.getValueB() + nextOne.getValueB());

                    listOfNumbers.addAfter(newOne, current.getValue());
                }

                current = nextOne;

            }

            i++;
        }

        return listOfNumbers;
    }
}
