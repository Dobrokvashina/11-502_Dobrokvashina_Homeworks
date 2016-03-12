package ru.itis.inform;

import org.junit.Before;
import org.junit.Test;
import ru.itis.inform.Iterator;
import ru.itis.inform.LinkedList;

import static org.junit.Assert.assertEquals;

public class LinkedListTest {

    private LinkedList<Integer> expectedList;

    @Test
    public void testSort() throws Exception {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(6);
        list.addEnd(2);
        list.addEnd(4);
        list.addEnd(1);
        list.addEnd(3);
        list.addEnd(5);

        expectedList = LinkedList.sort(list);

        LinkedList<Integer> actualList = new LinkedList<Integer>();

        actualList.add(1);
        actualList.addEnd(2);
        actualList.addEnd(3);
        actualList.addEnd(4);
        actualList.addEnd(5);
        actualList.addEnd(6);

        Iterator<Integer> iteratorForExeption = expectedList.iterator();
        Iterator<Integer> iteratorForActual = actualList.iterator();

        while(iteratorForActual.hasNext()) {
            assertEquals(iteratorForActual.peekNext(), iteratorForExeption.peekNext());
            iteratorForActual.next();
            iteratorForExeption.next();
        }
    }

    @Test
    public void testMerge() throws Exception {

        LinkedList<Integer> firstOne = new LinkedList<Integer>();
        firstOne.add(1);
        firstOne.addEnd(3);
        firstOne.addEnd(4);

        LinkedList<Integer> secondOne = new LinkedList<Integer>();
        secondOne.add(1);
        secondOne.addEnd(2);
        secondOne.addEnd(5);

        expectedList = LinkedList.merge(firstOne, secondOne);

        LinkedList<Integer> actualList = new LinkedList<Integer>();
        actualList.add(1);
        actualList.addEnd(1);
        actualList.addEnd(2);
        actualList.addEnd(3);
        actualList.addEnd(4);
        actualList.addEnd(5);

        Iterator<Integer> iteratorForExeption = expectedList.iterator();
        Iterator<Integer> iteratorForActual = actualList.iterator();

        while(iteratorForActual.hasNext()) {
            assertEquals(iteratorForActual.peekNext(), iteratorForExeption.peekNext());
            iteratorForActual.next();
            iteratorForExeption.next();
        }
    }

    @Test
    public void testRemove() throws Exception {

        expectedList = new LinkedList<Integer>();

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