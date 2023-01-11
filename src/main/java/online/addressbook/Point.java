package online.addressbook;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Getter
@AllArgsConstructor
public class Point {
    private final double x;
    private final double y;

    public static Point getNewPoint(double x, double y) {
        return new Point(x, y);
    }

    public double getLengthTo(Point other) {
        double xa = x, xb = other.x, ya = y, yb = other.y;
        return sqrt(pow(xb - xa, 2) + pow(yb - ya, 2));
    }
}
