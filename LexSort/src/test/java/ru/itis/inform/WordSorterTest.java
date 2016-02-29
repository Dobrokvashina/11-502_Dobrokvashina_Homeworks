package ru.itis.inform;

import org.junit.Test;

import static org.junit.Assert.*;


public class WordSorterTest {

    @Test
    public void testSort() throws Exception {
        WordSorter sorter = new WordSorter();

        LinkedList<String> actual = new LinkedList<String>();

        actual.add("Paint");
        actual.addEnd("Apple");
        actual.addEnd("Table");
        actual.addEnd("SLEEP");
        actual.addEnd("zooly");
        actual.addEnd("after");
        actual.addEnd("mercy");

        actual = sorter.sort(actual);

        LinkedList<String> expected = new LinkedList<String>();

        expected.add("after");
        expected.addEnd("Apple");
        expected.addEnd("mercy");
        expected.addEnd("Paint");
        expected.addEnd("SLEEP");
        expected.addEnd("Table");
        expected.addEnd("zooly");

        Iterator<String> actIterator = actual.iterator();
        Iterator<String> expIterator = expected.iterator();

        while (actIterator.hasNext()) {
            assertEquals(expIterator.peekNext(), actIterator.peekNext());
            actIterator.next();
            expIterator.next();
        }

    }
}