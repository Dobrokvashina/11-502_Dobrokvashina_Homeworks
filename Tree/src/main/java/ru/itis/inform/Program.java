package ru.itis.inform;


public class Program {


    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTreeImpl();

        int array[] = {8, 10, 14, 1, 13, 6, 4, 7, 3, 15, 20, 19, 18};
        for (int i = 0; i < array.length; i++) {
            tree.insert(array[i]);
        }

        tree.show();

        tree.inOrderPrint();

        System.out.println();

        if (tree.isSumOfEveryLeverBiggerThanSumOfPreviousOne()) {
            System.out.println("Sum Of Every Level IS Bigger Than Sum Of Previous One");
        } else {
            System.out.println("Sum Of Every Level Is NOT Bigger Than Sum Of Previous One");
        }

        tree.change(10, 2);

        System.out.println();

        tree.show();

        if (tree.isBST()) {
            System.out.println("It IS Binary Search tree");
        } else {
            System.out.println("It is NOT Binary Search tree");
        }



        // бинарная кучка

        BinaryHeap heap = new BinaryHeap();

        heap.add(9);
        heap.add(11);
        heap.add(8);
        heap.add(10);
        heap.add(20);
        heap.add(12);
        heap.add(4);

        heap.show();

        System.out.println("Max=  " + heap.getMax());

        heap.show();
    }

}
