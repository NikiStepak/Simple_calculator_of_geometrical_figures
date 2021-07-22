package objects;

public class Point {
    double xd,yd;
    public int x, y;

    public Point(double x, double y) {
        this.xd = x;
        this.yd = y;
        this.x= (int) xd;
        this.y= (int) yd;
    }

    @Override
    public String toString() {
        return ("x="+xd+", y="+yd);
    }
}
