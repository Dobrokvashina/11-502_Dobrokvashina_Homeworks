package ru.itis.inform;

import org.junit.Test;

import static org.junit.Assert.*;

public class WrapAlgorithmTest {

    @Test
    public void testWrap() throws Exception {

        Point[] points = new Point[4];

        points[0] = new Point(3, 3);
        points[1] = new Point(5, 1);
        points[2] = new Point(1, 1);
        points[3] = new Point(3, 5);

        Point[] actual = WrapAlgorithm.wrap(points);

        Point[] expected = new Point[4];

        expected[0] = new Point(1, 1);
        expected[1] = new Point(5, 1);
        expected[2] = new Point(3, 5);

        for (int i = 0; i < 3; i++) {
            assertEquals(expected[i].getX(), actual[i].getX());
            assertEquals(expected[i].getY(), actual[i].getY());
        }
    }
}