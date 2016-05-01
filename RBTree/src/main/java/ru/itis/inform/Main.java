package ru.itis.inform;

public class Main {
    public static void main(String[] args) {
        RBTree tree = new RBTree();

        int array[] = {8, 10, 14, 1, 13, 6, 4, 7, 3, 15, 20, 19, 18};
        for (int i = 0; i < array.length; i++) {
            tree.add(array[i]);
        }

        tree.show();
    }
}
