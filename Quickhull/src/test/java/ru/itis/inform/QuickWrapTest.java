package ru.itis.inform;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class QuickWrapTest {

    @Test
    public void testWrap() throws Exception {

        ArrayList<Point>  actual = new ArrayList<Point>();

        actual.add(new Point(-2, 4));
        actual.add(new Point(1, 3));
        actual.add(new Point(2, 2));
        actual.add(new Point(2, 5));
        actual.add(new Point(3, 1));
        actual.add(new Point(0, 0));
        actual.add(new Point(4, 3));

        actual = QuickWrap.wrap(actual);

        ArrayList<Point> expected = new ArrayList<Point>();


        expected.add(new Point(2, 5));
        expected.add(new Point(-2, 4));
        expected.add(new Point(3, 1));
        expected.add(new Point(0, 0));
        expected.add(new Point(4, 3));


        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getX(), actual.get(i).getX());
            assertEquals(expected.get(i).getY(), actual.get(i).getY());
        }
    }
}