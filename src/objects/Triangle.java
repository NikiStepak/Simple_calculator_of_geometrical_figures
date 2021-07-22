package objects;

public class Triangle {

    public Point p1, p2, p3;
    double a, b, c;
    double area, perimeter;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        countSide();
        if (check()) {
            countPerimeter();
            countArea();
        }
    }

    public boolean check() {
        if (a < b + c)
            if (b < a + c)
                return c < a + b;
        return false;
    }

    void countSide() {
        double dx, dy;
        dx = p1.xd - p2.xd;
        dy = p1.yd - p2.yd;
        this.a = Math.sqrt(dx * dx + dy * dy);
        dx = p2.xd - p3.xd;
        dy = p2.yd - p3.yd;
        this.b = Math.sqrt(dx * dx + dy * dy);
        dx = p3.xd - p1.xd;
        dy = p3.yd - p1.yd;
        this.c = Math.sqrt(dx * dx + dy * dy);
    }

    double countSide(Point p1, Point p2) {
        double dx, dy;
        dx = p1.xd - p2.xd;
        dy = p1.yd - p2.yd;
        return Math.sqrt(dx * dx + dy * dy);
    }

    void countArea() {
        double p = perimeter / 2;
        this.area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public double countArea(Point p1, Point p2, Point p3) {
        double a = countSide(p1, p2);
        double b = countSide(p2, p3);
        double c = countSide(p3, p1);
        double p = countPerimeter(a, b, c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    void countPerimeter() {
        this.perimeter = (a + b + c);
    }

    double countPerimeter(double a, double b, double c) {
        return a + b + c;
    }

    public boolean checkPointLocation(Point p) {
        double area1, area2, area3;
        area1 = countArea(p1, p, p2);
        area2 = countArea(p2, p, p3);
        area3 = countArea(p3, p, p1);
        area1 += area2 + area3;
        return !(area < area1);
    }
}
