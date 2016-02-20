package ru.itis.inform;

import ru.itis.inform.*;

public class FareyLinkedListImpl {

    LinkedList<RationalNumber> runFarey(int n) {

        LinkedList<RationalNumber> listOfNumbers = new LinkedList<>();

        RationalNumber first = new RationalNumber(0, 1);
        RationalNumber last = new RationalNumber(1, 1);

        listOfNumbers.add(last);
        listOfNumbers.add(first);

        int i = 2;

        while (i <= n) {

            Iterator<RationalNumber> iterator = listOfNumbers.iterator();
            iterator.next();

            while (iterator.hasNext()) {

                int b = iterator.peekPrevious().getB() + iterator.peekNext().getB();

                if(b == i) {

                    RationalNumber newOne = new RationalNumber(iterator.peekPrevious().getA() + iterator.peekNext().getA(), b);

                    iterator.insert(newOne);

                }

                iterator.next();

            }

            i++;
        }

        return listOfNumbers;
    }

}
