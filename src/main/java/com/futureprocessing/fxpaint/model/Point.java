package com.futureprocessing.fxpaint.model;

import javafx.scene.input.MouseEvent;

import static java.lang.Double.compare;
import static java.lang.Math.abs;
import static java.util.Objects.requireNonNull;

public final class Point {

    public static Point ONE = Point.from(1, 1);
    public static Point ONE_X = Point.from(1, 0);
    public static Point ONE_Y = Point.from(0, 1);
    public static Point ZERO = Point.from(0, 0);
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point from(MouseEvent event) {
        requireNonNull(event);
        return new Point(event.getX(), event.getY());
    }

    public static Point from(double x, double y) {
        return new Point(x, y);
    }

    public Point relativelyTo(Point origin) {
        requireNonNull(origin);
        return relativelyTo(origin.getX(), origin.getY());
    }

    public Point relativelyTo(double originX, double originY) {
        return new Point(x - originX, y - originY);
    }

    public Point minus(Point point) {
        requireNonNull(point);
        return new Point(x - point.getX(), y - point.getY());
    }

    public Point plus(Point point) {
        requireNonNull(point);
        return new Point(x + point.getX(), y + point.getY());
    }

    public boolean isInFirstQuadrant() {
        return x > 0 && y > 0;
    }

    public boolean isInSecondQuadrant() {
        return x < 0 && y > 0;
    }

    public boolean isInThirdQuadrant() {
        return x < 0 && y < 0;
    }

    public boolean isInFourthQuadrant() {
        return x > 0 && y < 0;
    }

    public boolean isIn(Point upperLeft, double width, double height) {
        requireNonNull(upperLeft);
        return x > upperLeft.getX() && x < width && y > upperLeft.getY() && y < height;
    }

    public double getX() {
        return x;
    }

    public int getIntX() {
        return (int) x;
    }

    public double getAbsoluteX() {
        return abs(x);
    }

    public double getY() {
        return y;
    }

    public int getIntY() {
        return (int) y;
    }

    public double getAbsoluteY() {
        return abs(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Point point = (Point) o;
        return compare(point.x, x) == 0 && compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s[x=%s,y=%s]", getClass().getSimpleName(), x, y);
    }
}
