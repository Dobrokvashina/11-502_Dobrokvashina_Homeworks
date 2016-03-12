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

    public int length() {
        return this.count;
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

    public static <T extends Comparable<T>> LinkedList<T> merge(LinkedList<T> firstOne, LinkedList<T> secondOne) {
        LinkedList<T> list = new LinkedList<T>();

        Iterator<T> firstItr = firstOne.iterator();
        Iterator<T> secondItr = secondOne.iterator();

        int compare = firstItr.peekNext().compareTo(secondItr.peekNext());
        if (compare == 0) {
            list.add(firstItr.peekNext());
            list.add(firstItr.peekNext());
            firstItr.next();
            secondItr.next();
        } else {
            if (compare < 0) {
                list.add(firstItr.peekNext());
                firstItr.next();
            } else {
                list.add(secondItr.peekNext());
                secondItr.next();
            }
        }

        while (firstItr.hasNext() && secondItr.hasNext()) {

            compare = firstItr.peekNext().compareTo(secondItr.peekNext());
            if (compare == 0) {
                list.addEnd(firstItr.peekNext());
                list.addEnd(firstItr.peekNext());
                firstItr.next();
                secondItr.next();
            } else {
                if (compare < 0) {
                    list.addEnd(firstItr.peekNext());
                    firstItr.next();
                } else {
                    list.addEnd(secondItr.peekNext());
                    secondItr.next();
                }
            }

        }

        if (firstItr.hasNext())
            while (firstItr.hasNext()) {
                list.addEnd(firstItr.peekNext());
                firstItr.next();
            }

        if (secondItr.hasNext())
            while (secondItr.hasNext()) {
                list.addEnd(secondItr.peekNext());
                secondItr.next();
            }

        return list;
    }

    public static <T extends Comparable<T>> LinkedList<T> sort(LinkedList<T> list) {
        ArrayList<LinkedList<T>> stack = new ArrayList<LinkedList<T>>();
        double c = Math.log(list.length())/Math.log(2.0);

        for (int i = 0; i < (int)c + 1; i++) {
            stack.add(new LinkedList<T>());
        }

        int stackPos = 0;
        Iterator<T> itr = list.iterator();

        while (itr.hasNext()) {
            LinkedList<T> newList = new LinkedList<T>();
            newList.add(itr.peekNext());
            stack.set(stackPos, newList);
            itr.next();
            stackPos++;
            while ((stackPos > 1) && (stack.get(stackPos - 1).length() == stack.get(stackPos - 2).length()) ){
                stack.set(stackPos - 2, merge(stack.get(stackPos - 2), stack.get(stackPos - 1)));
                stackPos--;
            }
        }

        while (stackPos > 1) {
            stack.set(stackPos - 2, merge(stack.get(stackPos - 2), stack.get(stackPos - 1)));
            stackPos--;
        }

        list = stack.get(0);

        return list;
    }

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
