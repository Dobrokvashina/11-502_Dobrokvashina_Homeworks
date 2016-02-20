package ru.itis.inform;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class FareyLinkedListImplTest {

    FareyLinkedListImpl generator;

    @Before
    public void setUp() throws Exception {
        this.generator = new FareyLinkedListImpl();
    }

    @Test
    public void testRunFarey() throws Exception {
        LinkedList<RationalNumber> expectedListOfNumbers = generator.runFarey(3);

        LinkedList<RationalNumber> actuallListOfNumbers = new LinkedList();

        actuallListOfNumbers.add(new RationalNumber(1, 1));
        actuallListOfNumbers.add(new RationalNumber(2, 3));
        actuallListOfNumbers.add(new RationalNumber(1, 2));
        actuallListOfNumbers.add(new RationalNumber(1, 3));
        actuallListOfNumbers.add(new RationalNumber(0, 1));

        Iterator<RationalNumber> exeptionIterator = expectedListOfNumbers.iterator();
        Iterator<RationalNumber> actualIterator = actuallListOfNumbers.iterator();

        while (actualIterator.hasNext()) {
            assertEquals(exeptionIterator.peekNext().getA(),actualIterator.peekNext().getA());
            assertEquals(exeptionIterator.peekNext().getB(),actualIterator.peekNext().getB());
            exeptionIterator.next();
            actualIterator.next();
        }


    }
}