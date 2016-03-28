package ru.itis.inform;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeImplTest {

    @org.junit.Test
    public void isSumOfEveryLeverBiggerThanSumOfPreviousOne() throws Exception {
        BinarySearchTreeImpl tree = new BinarySearchTreeImpl();

        int array[] = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        for (int i = 0; i < array.length; i++) {
            tree.insert(array[i]);
        }

        boolean expected = true;

        boolean actual = tree.isSumOfEveryLeverBiggerThanSumOfPreviousOne();

        assertEquals(expected, actual);

    }

    @Test
    public void isBST() throws Exception {
        BinarySearchTreeImpl tree = new BinarySearchTreeImpl();

        int array[] = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        for (int i = 0; i < array.length; i++) {
            tree.insert(array[i]);
        }

        tree.change(3,99);
        boolean actualResult = tree.isBST();
        boolean expectedResilt = false;
        assertEquals(expectedResilt, actualResult);

    }
}
