package ru.itis.inform;

public class Main {

    public static void main(String[] args) {
	    LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(5);
        list.add(7);
        list.add(10);
        list.addEnd(11);

        Iterator<Integer> iterator = list.iterator();

        while(iterator.hasNext()) {
            System.out.print(iterator.peekNext() + "  ");
            iterator.next();
        }

        System.out.println();

        LinkedList<Integer> list1 = new LinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        list.append(list1);

        Iterator<Integer> iterator1 = list.iterator();

        while(iterator1.hasNext()) {
            System.out.print(iterator1.peekNext() + "  ");
            iterator1.next();
        }
    }
}
