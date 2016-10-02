package ru.itis.inform;


public class Takeniter {

    Observer obeserver;

    public void process(String text) {

        obeserver = new Observer();

        char[] symbols = text.toCharArray();

        int i = 0;

        String current;
        while(i < symbols.length) {

            if (symbols[i] != ' ') {

                if (Character.getNumericValue(symbols[i]) == -1) {
                        current = "";

                    while (i < symbols.length && Character.getNumericValue(symbols[i]) == -1 && symbols[i] != ' ') {
                        current += symbols[i];
                        i++;

                        if (i+1 < symbols.length && symbols[i] == ' ' && Character.getNumericValue(symbols[i+1]) == -1) {
                            i++;
                            current+= ' ';
                        }
                    }
                    obeserver.handleSigh(current);
                } else {

                    if (i < symbols.length && Character.getNumericValue(symbols[i]) < 10 && Character.getNumericValue(symbols[i]) >= 0) {
                        current = "";
                        while (Character.getNumericValue(symbols[i]) < 10 && Character.getNumericValue(symbols[i]) >= 0  && symbols[i] != ' ') {
                            current += symbols[i];
                            i++;

                            if (i+1 < symbols.length && symbols[i] == ' ' && Character.getNumericValue(symbols[i+1]) < 10 && Character.getNumericValue(symbols[i+1]) >= 0) {
                                i++;
                                current+= ' ';
                            }
                        }
                        obeserver.handleDigits(current);
                    } else {

                        current = "";
                        while (i < symbols.length && Character.getNumericValue(symbols[i]) > 9  && symbols[i] != ' ') {
                            current += symbols[i];
                            i++;
                            if (i+1 < symbols.length && symbols[i] == ' ' && Character.getNumericValue(symbols[i + 1]) > 9) {
                                current+= ' ';
                                i++;
                            }
                        }
                        obeserver.handleLetters(current);
                    }
                }

            } else {
                i++;
            }
        }
    }

}
