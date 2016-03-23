package ru.itis.inform;

import java.util.ArrayList;

public class QuickWrap {

    public static ArrayList<Point> wrap(ArrayList<Point> input) {

        Point right = input.get(0);
        Point left = input.get(0);

        for (int i = 1; i < input.size(); i++) {
            if (input.get(i).getX() < left.getX())
                left = input.get(i);

            if (input.get(i).getX() > right.getX())
                right = input.get(i);
        }

        right.wasHere();
        left.wasHere();


        ArrayList<Point> up = new ArrayList<Point>();
        ArrayList<Point> down = new ArrayList<Point>();

        for (int i = 0; i < input.size(); i++) {
            if (isHigher(input.get(i), left, right))
                up.add(input.get(i));
            else
                down.add(input.get(i));
        }

        ArrayList<Point> result = new ArrayList<Point>();

        if (!up.isEmpty())
            result = Quickhull(up, left, right, true);

        if (!down.isEmpty())
            result.addAll(Quickhull(down, left, right, false));

        return result;
    }

    public static ArrayList<Point> Quickhull(ArrayList<Point> input, Point first, Point second, boolean isUp) {

        double maxDist = distance(input.get(0), first, second);
        Point maxPoint = input.get(0);

        for (int i = 1; i < input.size(); i++) {
            if ((distance(input.get(i), first, second) > maxDist) && (!input.get(i).wasI())){
                maxDist = distance(input.get(i), first, second);
                maxPoint = input.get(i);
            }

            if ((distance(input.get(i), first, second) == maxDist) && (!input.get(i).wasI())){
                Point oneLine = new Point(second.getX() - first.getX(), second.getY() - first.getY());
                Point secondLine = new Point(input.get(i).getX() - first.getX(), input.get(i).getY() - first.getY());
                Point mainLine = new Point(maxPoint.getX() - first.getX(), maxPoint.getY() - first.getY());

                if (WrapAlgorithm.angle(oneLine, secondLine) > WrapAlgorithm.angle(oneLine, mainLine)) {
                    maxDist = distance(input.get(i), first, second);
                    maxPoint = input.get(i);
                }
            }
        }

        maxPoint.wasHere();

        if (isUp) {
            for (int i = 0; i < input.size(); i++) {
                if ((isHigher(input.get(i), first, second)) && (!isHigher(input.get(i), first, maxPoint)) && (!isHigher(input.get(i), maxPoint, second))) {
                    input.remove(i);
                    i--;
                }
            }

            if (((isAnythingHigher(input, first, maxPoint)) || (isAnythingHigher(input, maxPoint, second))) && (!input.isEmpty())) {

                if (isAnythingHigher(input, first, maxPoint)) {
                    input = Quickhull(input, first, maxPoint, true);
                }

                if (isAnythingHigher(input, maxPoint, second)) {
                    input = Quickhull(input, maxPoint, second, true);
                }
            }

            return input;

        } else {
            for (int i = 0; i < input.size(); i++) {

                if ((!isHigher(input.get(i), first, second)) && (isHigher(input.get(i), first, maxPoint)) && (isHigher(input.get(i), maxPoint, second))) {

                    input.remove(i);
                    i--;
                }
            }

            if ((isAnythingLower(input, first, maxPoint)) || (isAnythingLower(input, maxPoint, second)) && (!input.isEmpty())) {
                if (isAnythingLower(input, first, maxPoint))
                    input = Quickhull(input, first, maxPoint, false);

                if (isAnythingLower(input, maxPoint, second))
                    input = Quickhull(input, maxPoint, second, false);

            }

            return input;
        }

    }


    public static double distance(Point point, Point lineA, Point lineB) {

        Point line = new Point(lineB.getX() - lineA.getX(), lineB.getY() - lineA.getY());
        Point secondLine = new Point(point.getX() - lineA.getX(), point.getY() - lineA.getY());
        double area = Math.abs(line.getX()*secondLine.getY() - line.getY()*secondLine.getX());
        double lineLength = Math.sqrt(line.getX()*line.getX() + line.getY()*line.getY());

        return area/lineLength;
    }

    public static boolean isHigher(Point point, Point A, Point B) {

        if (B.getX() != A.getX()) {

            double k = (A.getY() - B.getY());
            k /=  (A.getX() - B.getX());
            double b = A.getY() - k * A.getX();

            if ((!point.wasI()) && (point.getY() > ((point.getX() * k) + b))) {
                return true;
            }
        }

        return false;
    }

    public static boolean isAnythingHigher(ArrayList<Point> points, Point A, Point B) {

        if (B.getX() != A.getX()) {
            double k = (A.getY() - B.getY()) / (A.getX() - B.getX());
            double b = A.getY() - k * A.getX();

            for (int i = 0; i < points.size(); i++) {
                if ((!points.get(i).wasI()) && (points.get(i).getY() > (points.get(i).getX() * k + b))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAnythingLower(ArrayList<Point> points, Point A, Point B) {

        if (B.getX() != A.getX()) {
            double k = (A.getY() - B.getY()) / (A.getX() - B.getX());
            double b = A.getY() - k * A.getX();

            for (int i = 0; i < points.size(); i++) {
                if ((!points.get(i).wasI()) && (points.get(i).getY() < (points.get(i).getX() * k + b))) {
                    return true;
                }
            }
        }
        return false;
    }
}
