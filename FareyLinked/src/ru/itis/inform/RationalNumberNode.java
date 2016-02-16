package ru.itis.inform;


public class RationalNumberNode {
    private RationalNumber value;
    private RationalNumberNode next;

    public RationalNumberNode(RationalNumber value) {
        this.value = value;
        this.next = null;
    }

    public void setNext(RationalNumberNode next) {
        this.next = next;
    }

    public RationalNumber getValue() {
        return value;
    }

    public int getValueA() {
        return value.getA();
    }

    public int getValueB() {
        return value.getB();
    }

    public RationalNumberNode getNext() {
        return next;
    }
}
