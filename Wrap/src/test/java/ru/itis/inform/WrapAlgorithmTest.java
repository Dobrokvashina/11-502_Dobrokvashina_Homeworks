package ru.itis.inform;

import org.junit.Test;

import static org.junit.Assert.*;

public class WrapAlgorithmTest {

    @Test
    public void testWrap() throws Exception {

        Point[]  actual = new Point[7];

        actual[0] =new Point(-2, 4);
        actual[1] = new Point(1, 3);
        actual[2] = new Point(2, 2);
        actual[3] = new Point(2, 5);
        actual[4] = new Point(3, 1);
        actual[5] = new Point(0, 0);
        actual[6] = new Point(4, 3);

        actual = WrapAlgorithm.wrap(actual);


        Point[] expected = new Point[5];


        expected[0] = new Point(0, 0);
        expected[1] = new Point(-2, 4);
        expected[2] = new Point(2, 5);
        expected[3] = new Point(4, 3);
        expected[4] = new Point(3, 1);


        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].getX(), actual[i].getX());
            assertEquals(expected[i].getY(), actual[i].getY());
        }

    }
}