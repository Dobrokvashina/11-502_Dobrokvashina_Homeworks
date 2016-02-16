package ru.itis.inform;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphMatrixImplTest {

    private GraphMatrixImpl generator;

    @Before
    public void setUp() throws Exception {
        this.generator = new GraphMatrixImpl();
    }

    @Test
    public void testRunFloid() throws Exception {

        generator.addVertex();
        generator.addVertex();
        generator.addVertex();


        generator.addEdge(0, 1, 11);
        generator.addEdge(0, 2, 20);
        generator.addEdge(1, 2, 8);


        int actual[][] = generator.RunFloid();

        int expected[][] = {{0, 11, 19}, {11, 0, 8}, {19, 8, 0}};

        assertArrayEquals(actual, expected);
    }
}