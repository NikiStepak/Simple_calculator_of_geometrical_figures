package graphPanel;

import objects.Circle;
import objects.Line;
import objects.Point;
import objects.Triangle;

import java.util.ArrayList;

public class DataManager {
    public ArrayList<Point> points;
    public ArrayList<Line> lines;
    public ArrayList<Triangle> triangles;
    public ArrayList<Circle> circles;
    public int size =10;

    public DataManager(){
        points=new ArrayList<>();
        lines=new ArrayList<>();
        triangles=new ArrayList<>();
        circles=new ArrayList<>();
    }
}
