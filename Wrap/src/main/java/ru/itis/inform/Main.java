package ru.itis.inform;

public class Main {

    public static void main(String[] args) {

        Point[] points = new Point[7];
        points[0] = new Point(-2, 4);
        points[1] = new Point(1, 3);
        points[2] = new Point(2, 2);
        points[3] = new Point(2, 5);
        points[4] = new Point(3, 1);
        points[5] = new Point(0, 0);
        points[6] = new Point(4, 3);

        Point[] result = WrapAlgorithm.wrap(points);

        int i = 0;

        while ((i < result.length) && (result[i] != null)) {
            System.out.println(result[i].toString());
            i++;
        }
    }
}
