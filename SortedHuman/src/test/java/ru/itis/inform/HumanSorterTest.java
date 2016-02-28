package ru.itis.inform;

import org.junit.Test;
import ru.itis.inform.*;

import static org.junit.Assert.*;

public class HumanSorterTest {

    private HumanSorter sorter = new HumanSorter();

    @Test
    public void testSort() throws Exception {

        LinkedList<Human> actual = new LinkedList<Human>();

        actual.add(new Human("Clark", 32));
        actual.add(new Human("Barry", 25));
        actual.add(new Human("Bruce", 35));
        actual.add(new Human("Diana", 29));
        actual.add(new Human("Harold", 30));

        actual = sorter.sort(actual);

        LinkedList<Human> expected = new LinkedList<Human>();

        expected.add(new Human("Bruce", 35));
        expected.add(new Human("Clark", 32));
        expected.add(new Human("Harold", 30));
        expected.add(new Human("Diana", 29));
        expected.add(new Human("Barry", 25));

        Iterator<Human> actIterator = actual.iterator();
        Iterator<Human> expIterator = expected.iterator();

        for (int i = 0; i < 5; i++) {
            assertEquals(expIterator.peekNext().getName(), actIterator.peekNext().getName());
            assertEquals(expIterator.peekNext().getAge(), actIterator.peekNext().getAge());
            actIterator.next();
            expIterator.next();
        }
    }
}