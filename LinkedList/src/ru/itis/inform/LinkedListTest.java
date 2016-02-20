package ru.itis.inform;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {

    private LinkedList<Integer> expectedList;

    @Before
    public void setUp() throws Exception {

        this.expectedList = new LinkedList();

    }

    @Test
    public void testRemove() throws Exception {

        this.expectedList.add(5);
        this.expectedList.add(7);
        this.expectedList.add(10);
        this.expectedList.remove(7);

        LinkedList<Integer> actualList = new LinkedList();

        actualList.add(5);
        actualList.add(10);

        Iterator<Integer> iteratorForExeption = expectedList.iterator();
        Iterator<Integer> iteratorForActual = actualList.iterator();

        while(iteratorForActual.hasNext()) {
            assertEquals(iteratorForActual.peekNext(), iteratorForExeption.peekNext());
            iteratorForActual.next();
            iteratorForExeption.next();
        }

    }
}