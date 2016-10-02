package ru.itis.inform;

public class Observer {

    public void handleDigits(String Digits) {
        System.out.println("Digits: " + Digits);
    }

    public void handleLetters(String Letters) {
        System.out.println("Letters: " + Letters);
    }

    public void handleSigh(String Sigh) {
        System.out.println("Sigh: " + Sigh);
    }
}
