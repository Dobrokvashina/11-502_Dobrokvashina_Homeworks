package ru.itis.inform;

import java.util.NoSuchElementException;

class LinkedListIteratorImpl<T> implements Iterator<T> {

    Node<T> current;

    public LinkedListIteratorImpl(Node<T> first) {

        this.current = first;
    }

    @Override
    public boolean hasNext() {

        return current!= null;
    }


    @Override
    public void next() {

        this.current = current.getNext();
    }

    public T peekNext() {

        return current.getValue();
    }

    @Override
    public void previous() {

        if (current.getPrevious() != null) {
            current = current.getPrevious();
        } else {
            throw new NoSuchElementException();
        }
    }

    public T peekPrevious() {
        if (current.getPrevious() != null) {
            return current.getPrevious().getValue();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void insert(T element) {

        Node newOne = new Node(element);

            newOne.setNext(current);
            newOne.setPrevious(current.getPrevious());
            current.getPrevious().setNext(newOne);
            current.setPrevious(newOne);

    }
}
