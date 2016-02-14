package ru.itis.inform;

public class LinkedList implements List {
    private Node first;

    private int count;

    public LinkedList() {
        this.first = null;
        this.count = 0;
    }

    public void add(int element) {
        Node newNode = new Node(element);

        if (first == null) {
            this.first = newNode;
        } else {
            newNode.setNext(this.first);
            first = newNode;
        }
        this.count++;
    }

    @Override
    public void remove(int element) {

        Node node = this.first;

        Node previous = null;

        while (node.getValue() != element) {
            previous = node;
            node = node.getNext();
        }

        if (node.getNext() != null) {
            previous.setNext(node.getNext());
        } else {
            previous.setNext(null);
        }

        this.count--;
    }

    public void show() {

        Node currentNode = first;


        for (int i = 0; i < this.count; i++){
            if (i != this.count - 1) {
                System.out.print(currentNode.getValue() + ", ");
                currentNode = currentNode.getNext();
            } else{
                System.out.println(currentNode.getValue() + ".");
            }
        }
    }
}
