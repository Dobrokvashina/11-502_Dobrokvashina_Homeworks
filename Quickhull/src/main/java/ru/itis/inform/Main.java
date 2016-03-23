package ru.itis.inform;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<Point>();

        points.add(new Point(2, 2));
        points.add(new Point(-1, -2));
        points.add(new Point(-2, 3));
        points.add(new Point(-4, 0));
        points.add(new Point(-1, 1));
        points.add(new Point(2, -1));
        points.add(new Point(0, 0));

        ArrayList<Point> result = QuickWrap.wrap(points);

        for (Point i: result) {
            System.out.println(i.toString());
        }
    }
}
