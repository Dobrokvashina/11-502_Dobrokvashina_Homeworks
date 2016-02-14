package ru.itis.inform;

public class Main {

    public static void main(String[] args) {
	    LinkedList list = new LinkedList();
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        list.show();
        list.remove(5);
        list.show();
        list.remove(1);
        list.show();
    }
}
