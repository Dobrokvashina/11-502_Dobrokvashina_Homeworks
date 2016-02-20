package ru.itis.inform;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListIteratorImplTest {

    private Iterator<Integer> iterator;

    @Test
    public void testInsert() throws Exception {

        LinkedList<Integer> expectedList = new LinkedList();

        expectedList.add(5);
        expectedList.add(7);
        expectedList.add(10);

        LinkedList<Integer> actualList = new LinkedList();

        actualList.add(5);
        actualList.add(9);
        actualList.add(7);
        actualList.add(10);

        this.iterator = expectedList.iterator();

        iterator.next();
        iterator.next();

        iterator.insert(9);


        Iterator<Integer> iteratorForExeption = expectedList.iterator();
        Iterator<Integer> iteratorForActual = actualList.iterator();

        while(iteratorForActual.hasNext()) {
            assertEquals(iteratorForActual.peekNext(), iteratorForExeption.peekNext());
            iteratorForActual.next();
            iteratorForExeption.next();
        }

    }
}