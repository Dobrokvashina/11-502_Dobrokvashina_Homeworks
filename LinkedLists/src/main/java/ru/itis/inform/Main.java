package ru.itis.inform;

public class Main {

    public static void main(String[] args) {
	    LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.addEnd(4);
        list.addEnd(15);
        list.addEnd(20);

        Iterator<Integer> iterator = list.iterator();

        while(iterator.hasNext()) {
            System.out.print(iterator.peekNext() + "  ");
            iterator.next();
        }


        System.out.println();

        System.out.println("merge: ");

        LinkedList<Integer> list1 = new LinkedList<Integer>();
        list1.add(2);
        list1.addEnd(5);
        list1.addEnd(13);


        LinkedList<Integer> anotherList = LinkedList.merge(list, list1);

        iterator = anotherList.iterator();

        while(iterator.hasNext()) {
            System.out.print(iterator.peekNext() + "  ");
            iterator.next();
        }


        System.out.println();
        System.out.println("appEnd:  ");

        list.append(list1);

        iterator = list.iterator();

        while(iterator.hasNext()) {
            System.out.print(iterator.peekNext() + "  ");
            iterator.next();
        }

        System.out.println();
        System.out.println("sort:  ");

        LinkedList<Integer> list2 = new LinkedList<Integer>();

        list2.add(8);
        list2.addEnd(4);
        list2.addEnd(3);
        list2.addEnd(20);
        list2.addEnd(13);
        list2.addEnd(1);

        System.out.println("  before:");
        iterator = list2.iterator();

        while(iterator.hasNext()) {
            System.out.print(iterator.peekNext() + "  ");
            iterator.next();
        }

        System.out.println();
        System.out.println("  after:");

        list2 = LinkedList.sort(list2);

        iterator = list2.iterator();

        while(iterator.hasNext()) {
            System.out.print(iterator.peekNext() + "  ");
            iterator.next();
        }
    }
}
