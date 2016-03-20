package ru.itis.inform;

public class WrapAlgorithm {

    public static double angle(Point first, Point second) {
        double scal = first.getX()*second.getX() + first.getY()*second.getY();
        double a = Math.sqrt(first.getX()*first.getX() + first.getY()*first.getY());
        double b = Math.sqrt(second.getX()*second.getX() + second.getY()*second.getY());
        return Math.toDegrees(Math.acos(scal /(a*b)));
    }

    public static Point[] wrap(Point[] input) {

        Point[] result = new Point[input.length];

        Point start = input[0];

        for (int i = 1; i < input.length; i++) {
            if (input[i].getY() < start.getY()) {
                start = input[i];
            }
            if ((input[i].getY() == start.getY()) && (input[i].getX() < start.getX())) {
                start = input[i];
            }
        }

        result[0] = start;

        Point one = new Point(1, 0);

        double min = 190;
        Point current = null;
        Point line = null;

        for (int i = 0; i < input.length; i++) {
            if (input[i] != start) {
                line = new Point(input[i].getX() - start.getX(), input[i].getY() - start.getY());
                if (angle(one, line) < min) {
                    current = input[i];
                    min = angle(one, line);
                }
            }
        }

        result[1] = current;
        int count = 2;

        boolean end = false;

        while (!end) {

            double max = -1;
            Point mayCurrent = null;
            Point newLine = null;
            line = new Point(-line.getX(), -line.getY());

            for (int i = 0; i < input.length; i++) {
                if (input[i] != current) {
                    newLine = new Point(input[i].getX() - current.getX(), input[i].getY() - current.getY());
                    if (angle(line, newLine) > max) {
                        mayCurrent = input[i];
                        max = angle(line, newLine);
                    }
                }
            }

            line = newLine;

            if (current == start) {
                end = true;
            } else {
                current = mayCurrent;
                result[count] = current;
                count++;
            }

        }

        return result;
    }
}
