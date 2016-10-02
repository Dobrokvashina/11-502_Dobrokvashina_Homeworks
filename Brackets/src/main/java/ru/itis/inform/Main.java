package ru.itis.inform;

import java.util.Stack;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        BracketsCounter bracketsInput = new BracketsCounter("(())()(()())");

        System.out.println("This is a string".valueOf(5872));
        for (int i = 0; i < 4; i++) {
            CounterThread counter = new CounterThread(bracketsInput, i);
            counter.start();
        }

        while (!bracketsInput.isEnd()) {
            Thread.sleep(50);
        }

        if (bracketsInput.isEmpty()) {
            System.out.println("All right, every bracket has a pair =)");
        } else{
            System.out.println("Oops... It's look like you've missed something =/");
        }
    }

}

class BracketsCounter {
    private String input;
    private Stack<Character> brackets;
    private int pos;

    public BracketsCounter(String input) {
        this.input = input;
        brackets = new Stack<Character>();
        pos = 0;
    }

    public synchronized int nextChar() {
        if (pos < input.length() && (input.charAt(pos) == '[' || input.charAt(pos) == ']' || input.charAt(pos) == '(' || input.charAt(pos) == ')'
                || input.charAt(pos) == '{' || input.charAt(pos) == '}')) {
            if (brackets.size() != 0 && ((input.charAt(pos) == ']' && brackets.peek() == '[') || (input.charAt(pos) == '}' && brackets.peek() == '{') || (input.charAt(pos) == ')' && brackets.peek() == '('))) {
                brackets.pop();
            } else {
                brackets.push(input.charAt(pos));
            }
        }
        pos++;
        return pos;
    }

    public Stack<Character> getBrackets() {
        return brackets;
    }

    public boolean isEnd() {
        return pos >= input.length();
    }

    public boolean isEmpty() {
        return brackets.isEmpty();
    }
}

class CounterThread extends Thread {
    private BracketsCounter counter;
    private int number;


    public CounterThread(BracketsCounter counter, int number) {
        this.number = number;
        this.counter = counter;
    }

    @Override
    public void run() {
        while (!counter.isEnd()) {
            System.out.println("I'm thread № " + number + " check char at " + counter.nextChar());
        }

    }
}
