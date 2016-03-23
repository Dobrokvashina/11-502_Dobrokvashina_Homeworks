package ru.itis.inform;

import java.security.PublicKey;

public class Point {

    private int x;
    private int y;
    boolean was;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        was = false;
    }

    public void wasHere() {
        was = true;
    }

    public boolean wasI() {
        return was;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "( " + x + ", " + y + ")";
    }

}
