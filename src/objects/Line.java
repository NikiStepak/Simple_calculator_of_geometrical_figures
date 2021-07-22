package objects;

public class Line {
    public Point p1, p2;
    public double a, b;

    public Line(Point p1, Point p2){
        this.p1=p1;
        this.p2=p2;
        this.a=countA(p1,p2);
        this.b=countB(this.a,p1);
    }

    double countA(Point p1, Point p2){
        return (p2.yd-p1.yd)/(p2.xd-p1.xd);
    }

    double countB(double a, Point p){
        return (p.yd-a*p.xd);
    }

    public Point crossingPointCramer(Line l1, Line l2){
        double w=(l1.a*(-1))-(l2.a*(-1));
        double wx=l1.b-l2.b;
        double wy=(-l2.b*l1.a)-(-l1.b*l2.a);
        double x= (wx/w);
        double y= (wy/w);
        return new Point(x,y);
    }

    public double checkPointLocation(Point p) {
        return (a * p.xd - p.yd + b);
    }

    public double countY(double x){
        return (a*x+b);
    }

    @Override
    public String toString() {
        String text="y=";
        if(a<0)
            text+="-";
        text+=Math.abs(a)+"x";
        if(b<0)
            text+="-";
        else
            text+="+";
        text+=Math.abs(b);
        return text;
    }
}
