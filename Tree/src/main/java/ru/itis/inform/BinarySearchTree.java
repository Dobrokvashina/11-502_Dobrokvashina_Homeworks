package ru.itis.inform;

public interface BinarySearchTree {
    // добавляет элемент в дерево
    void insert(int element);

    // показывает дерево
    void show();

    // выводит элементы дерева в прямом порядке
    void inOrderPrint();

    boolean isSumOfEveryLeverBiggerThanSumOfPreviousOne();

    boolean isBST();

    void change(int nowElement, int newElement);
}