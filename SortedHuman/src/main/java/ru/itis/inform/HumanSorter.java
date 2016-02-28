package ru.itis.inform;


public class HumanSorter {

    LinkedList<Human> sort(LinkedList<Human> humans) {

        int maxAge = 100;

        ArrayList<LinkedList<Human>> list = new ArrayList<LinkedList<Human>>();

        for (int i = 0; i <= maxAge; i++) {
            list.add(new LinkedList<Human>());
        }

        Iterator<Human> iterator = humans.iterator();

        while (iterator.hasNext()) {
            int currentAge = iterator.peekNext().getAge();
            Human currentHuman = iterator.peekNext();

            if (!list.get(currentAge).isEmpty()) {
                LinkedList<Human> newOne = list.get(currentAge);
                newOne.add(currentHuman);
                list.set(currentAge, newOne);
            } else {
                LinkedList<Human> newOne = list.get(currentAge);
                newOne.addEnd(currentHuman);
                list.set(currentAge, newOne);
            }

            iterator.next();
        }

        LinkedList<Human> resultList = new LinkedList<Human>();

        for (int i = 0; i < maxAge; i++) {
            resultList.append(list.get(i));
        }

        return resultList;

    }
}
