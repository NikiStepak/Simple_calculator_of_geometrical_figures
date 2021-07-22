package objects;

import java.util.ArrayList;

public class Circle {
    public Point p;
    public int r;

    public Circle(Point p, int r) {
        this.r = r;
        this.p = p;
    }

    public ArrayList<Point> crossingPoints(Line l) {
        ArrayList<Point>points=new ArrayList<>();
        double a = 1 + l.a * l.a;
        double b = 2 * l.a * l.b - 2 * p.xd - 2 * l.a * p.yd;
        double c = p.xd * p.xd + l.b * l.b + p.yd * p.yd - 2 * l.b * p.yd - r * r;
        double delta = b * b - 4 * a * c;
        if (delta > 0) {
            delta = Math.sqrt(delta);
            double x1 = (-b + delta) / (2 * a);
            double x2 = (-b - delta) / (2 * a);
            points.add(new Point(x1,l.countY(x1)));
            points.add(new Point(x2,l.countY(x2)));

        } else if (delta == 0) {
            double x = -b / (2 * a);
            points.add(new Point(x,l.countY(x)));
        }
        return points;
    }
}
