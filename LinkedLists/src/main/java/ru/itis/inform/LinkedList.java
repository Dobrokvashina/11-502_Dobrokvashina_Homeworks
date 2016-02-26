package ru.itis.inform;

public class LinkedList<T> implements List<T> {
    private Node first;

    private int count;

    public LinkedList() {
        this.first = null;
        this.count = 0;
    }

    public boolean isEmpty() {
        if (this.first == null) {
            return true;
        } else return false;
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

    public void addEnd(T element) {
        Node newNode = new Node<T>(element);
        Node last = this.first;

        if (last == null) {
            this.first = newNode;
        } else {
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(newNode);
            newNode.setPrevious(last);
            newNode.setNext(null);
        }
        this.count++;
    }

    public void append(LinkedList<T> newList) {
        Node last = this.first;
        Iterator iterator = newList.iterator();

        if (!newList.isEmpty()) {

            if (last == null) {
                this.first = iterator.getNext();
            } else {
                while (last.getNext() != null) {
                    last = last.getNext();
                }
                last.setNext(iterator.getNext());
                iterator.getNext().setPrevious(last);
            }
        }
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
