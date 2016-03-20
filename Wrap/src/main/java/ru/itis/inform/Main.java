package ru.itis.inform;

public class Main {

    public static void main(String[] args) {

        Point[] points = new Point[4];

        points[0] = new Point(3, 3);
        points[1] = new Point(5, 1);
        points[2] = new Point(1, 1);
        points[3] = new Point(3, 5);

        Point[] result = WrapAlgorithm.wrap(points);

        int i = 0;

        while ((i < result.length) && (result[i + 1] != null)) {
            System.out.println("( " + result[i].getX() + "; " + result[i].getY() + ")");
            i++;
        }
    }
}
