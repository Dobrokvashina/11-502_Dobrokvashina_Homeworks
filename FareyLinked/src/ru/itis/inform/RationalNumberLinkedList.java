package ru.itis.inform;


public class RationalNumberLinkedList implements List {

    private RationalNumberNode first;

    private int count;

    public RationalNumberLinkedList() {
        this.first = null;
        this.count = 0;
    }

    public RationalNumberNode getFirst() {
        return this.first;
    }

    public void add(RationalNumber element) {
        RationalNumberNode newNode = new RationalNumberNode(element);

        if (first == null) {
            this.first = newNode;
        } else {
            newNode.setNext(this.first);
            first = newNode;
        }
        this.count++;
    }

    public void addAfter(RationalNumber element, RationalNumber previous){

        RationalNumberNode newOne = new RationalNumberNode(element);

        RationalNumberNode node = this.first;

        RationalNumberNode nextOne = node.getNext();

        while (node.getValue() != previous) {
            node = nextOne;
            nextOne = node.getNext();
        }

        node.setNext(newOne);

        newOne.setNext(nextOne);

        this.count++;
    }


    public void show() {

        RationalNumberNode currentNode = this.first;


        for (int i = 0; i < this.count; i++) {
            if (i != this.count - 1) {
                System.out.print(currentNode.getValueA() + "/" + currentNode.getValueB() + ", ");
                currentNode = currentNode.getNext();
            } else {
                System.out.println(currentNode.getValueA() + "/" +currentNode.getValueB() + ".");
            }
        }
    }
}

