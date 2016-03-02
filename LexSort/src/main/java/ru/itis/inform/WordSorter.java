package ru.itis.inform;

public class WordSorter {

    public LinkedList<String> sort(LinkedList<String> words) {


        Iterator<String> identMax = words.iterator();
        int maxSize = identMax.peekNext().length();
        int max = 26;


        for (int j = maxSize; j > 0; j--) {

            ArrayList<LinkedList<String>> list = new ArrayList<LinkedList<String>>();

            for (int i = 0; i < max; i++) {
                list.add(new LinkedList<String>());
            }

            Iterator<String> iterator = words.iterator();

            while (iterator.hasNext()) {
                char currentChar = iterator.peekNext().charAt(j-1);
                int currentNumber =  Character.getNumericValue(currentChar) - 10;
                if ((currentNumber > 26) && (currentNumber < 1)) {
                    throw new IllegalArgumentException("There are some symbols I can't understand");
                }
                String currentWord = iterator.peekNext();

                if (list.get(currentNumber).isEmpty()) {
                    LinkedList<String> newOne = list.get(currentNumber);
                    newOne.add(currentWord);
                    list.set(currentNumber, newOne);
                } else {
                    LinkedList<String> newOne = list.get(currentNumber);
                    newOne.addEnd(currentWord);
                    list.set(currentNumber, newOne);
                }

                iterator.next();
            }

            LinkedList<String> words1 = new LinkedList<String>();
            for (int i = 0; i < max; i++) {
                words1.append(list.get(i));
            }

            words = words1;
        }

        return words;
    }

}
