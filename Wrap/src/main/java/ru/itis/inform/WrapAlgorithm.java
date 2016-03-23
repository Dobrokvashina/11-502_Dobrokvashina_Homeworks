package ru.itis.inform;

public class WrapAlgorithm {

    public static double angle(Point first, Point second) {
        double scal = (first.getX()*second.getX()) + (first.getY()*second.getY());
        double a = Math.sqrt((first.getX()*first.getX()) + (first.getY()*first.getY()));
        double b = Math.sqrt((second.getX()*second.getX()) + (second.getY()*second.getY()));
        return scal /(a*b) + 1;
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

        double min = -1;
        Point current = null;
        Point line = null;

        for (int i = 0; i < input.length; i++) {
            if (input[i] != start) {
                Point currentLine = new Point(input[i].getX() - start.getX(), input[i].getY() - start.getY());

                if (angle(one, currentLine) > min) {
                    current = input[i];
                    min = angle(one, currentLine);
                    line = currentLine;
                }
            }
        }

        result[1] = current;
        int count = 2;

        boolean end = false;

        while (!end) {

            double max = 3;
            Point mayCurrent = null;
            Point newLine = null;
            line = new Point(-line.getX(), -line.getY());

            for (int i = 0; i < input.length; i++) {
                    Point currentLine = new Point(input[i].getX() - current.getX(), input[i].getY() - current.getY());

                    if (angle(line, currentLine) < max) {
                        mayCurrent = input[i];
                        max = angle(line, currentLine);
                        newLine = currentLine;
                    }
            }

            line = newLine;

            current = mayCurrent;

            if ((current.getX() == start.getX())&& (current.getY() == start.getY()) || (count == input.length)) {
                end = true;
            } else {
                result[count] = current;
                count++;
            }

        }

        return result;
    }
}
