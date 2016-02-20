package ru.itis.inform;

public class LinkedList<T> implements List<T> {
    private Node first;

    private int count;

    public LinkedList() {
        this.first = null;
        this.count = 0;
    }

    public void add(T element) {
        Node newNode = new Node<T>(element);
        newNode.setPrevious(null);

        if (first == null) {
            this.first = newNode;
        } else {
            newNode.setNext(this.first);
            this.first.setPrevious(newNode);
            this.first = newNode;
        }
        this.count++;
    }

    @Override
    public void remove(T element) {

        Node node = this.first;

        Node previous = null;

        while (node.getValue() != element) {
            previous = node;
            node = node.getNext();
        }

        if (previous == null) {
            this.first = node.getNext();
        } else {

            if (node.getNext() != null) {
                previous.setNext(node.getNext());
            } else {
                previous.setNext(null);
            }
        }

        this.count--;
    }


    public Iterator<T> iterator() {
        return new LinkedListIteratorImpl<T>(this.first);
    }
}
