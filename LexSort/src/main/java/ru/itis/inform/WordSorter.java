package ru.itis.inform;

public class WordSorter {

    public LinkedList<String> sort(LinkedList<String> words) {


        Iterator<String> identMax = words.iterator();
        int maxSize = identMax.peekNext().length();
        int max = 26;


        for (int j = maxSize; j > 0; j--) {

            ArrayList<LinkedList<String>> list = new ArrayList<LinkedList<String>>();

            for (int i = 0; i <= max; i++) {
                list.add(new LinkedList<String>());
            }

            Iterator<String> iterator = words.iterator();

            while (iterator.hasNext()) {
                char currentChar = iterator.peekNext().charAt(j-1);
                int currentNumber =  toInt(currentChar) - 1;
                if (currentNumber == -1) {
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

    public int toInt (char bukva) {
        switch (bukva) {
            case 'a' :
            case 'A' :
                return 1;
            case 'b' :
            case 'B' :
                return 2;
            case 'c' :
            case 'C' :
                return 3;
            case 'd' :
            case 'D' :
                return 4;
            case 'e' :
            case 'E' :
                return 5;
            case 'f' :
            case 'F' :
                return 6;
            case 'g' :
            case 'G' :
                return 7;
            case 'h' :
            case 'H' :
                return 8;
            case 'i' :
            case 'I' :
                return 9;
            case 'j' :
            case 'J' :
                return 10;
            case 'k' :
            case 'K' :
                return 11;
            case 'l' :
            case 'L' :
                return 12;
            case 'm' :
            case 'M' :
                return 13;
            case 'n' :
            case 'N' :
                return 14;
            case 'o' :
            case 'O' :
                return 15;
            case 'p' :
            case 'P' :
                return 16;
            case 'q' :
            case 'Q' :
                return 17;
            case 'r' :
            case 'R' :
                return 18;
            case 's' :
            case 'S' :
                return 19;
            case 't' :
            case 'T' :
                return 20;
            case 'u' :
            case 'U' :
                return 21;
            case 'v' :
            case 'V' :
                return 22;
            case 'w' :
            case 'W' :
                return 23;
            case 'x' :
            case 'X' :
                return 24;
            case 'y' :
            case 'Y' :
                return 25;
            case 'z' :
            case 'Z' :
                return 26;
            default:
                return -1;
        }
    }
}
