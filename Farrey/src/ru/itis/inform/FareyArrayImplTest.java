package ru.itis.inform;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class FareyArrayImplTest {

    private FareyArrayImpl generator;

    @Before
    public void setUp() throws Exception {
        this.generator = new FareyArrayImpl();
    }

    @Test
    public void testRunFarey() throws Exception {
        final int n = 2;
        RationalNumber[] expectedMatrix = new RationalNumber[n * n];

        RationalNumber actualMatrix[] = generator.runFarey(n);
        expectedMatrix[0] = new RationalNumber(0, 1);
        expectedMatrix[1] = new RationalNumber(1, 2);
        expectedMatrix[2] = new RationalNumber(1, 1);

        for (int i = 0; i < n; i++) {
            assertEquals(expectedMatrix[i].getA(), actualMatrix[i].getA());
            assertEquals(expectedMatrix[i].getB(), actualMatrix[i].getB());
        }
    }
}