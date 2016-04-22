package ru.itis.inform;


public class Node {

    private int data;
    private Node left;
    private Node right;

    //конструктор одного "узла" дерева
    public Node(int data) {
        this.data = data;
    }

    //возвращение значения "узла"
    public int getData() {
        return data;
    }

    //установление значения "узла"
    public void setData(int data) {
        this.data = data;
    }

    //возвращение левого сына данного "узла"
    public Node getLeft() {
        return left;
    }

    //присвоение левого сына данного "узла"
    public void setLeft(Node left) {
        this.left = left;
    }

    //возвращение правого сына данного "узла"
    public Node getRight() {
        return right;
    }

    //присвоение правого сына данного "узла"
    public void setRight(Node right) {
        this.right = right;
    }
}
