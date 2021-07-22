package graphPanel;

import graphPanel.dialogs.*;
import objects.Circle;
import objects.Line;
import objects.Point;
import objects.Triangle;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MyPanel extends JPanel implements ActionListener {
    private final JButton drawPoint, drawLine, drawCircle, countCrossingPoint, checkLine, drawTriangle, countArea, checkTriangle, checkCircle, clean;
    private final CanvasPanel canvasPanel;

    DataManager dm;

    public MyPanel(DataManager dm) {
        this.dm = dm;
        setLayout(new BorderLayout());

        //buttons ======================================================================================================
        drawPoint = new JButton("Draw Point");
        drawLine = new JButton("Draw Line");
        drawTriangle=new JButton("Draw Triangle");
        drawCircle =new JButton("Draw Circle");
        countCrossingPoint = new JButton("Count Crossing Point");
        countArea=new JButton("Count triangle area");
        checkLine = new JButton("Check point position relative to line");
        checkTriangle=new JButton("Check point position relative to triangle");
        checkCircle=new JButton("Check if line is crossing circle");
        clean=new JButton("CLEAN");

        //layouts ======================================================================================================
        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(0);
        gridLayout.setRows(4);

        //tool panel ===================================================================================================
        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(gridLayout);

        add(BorderLayout.SOUTH, toolPanel);
        toolPanel.setBorder(new TitledBorder("Tool Panel"));

        drawPoint.addActionListener(this);
        drawLine.addActionListener(this);
        drawTriangle.addActionListener(this);
        drawCircle.addActionListener(this);
        countCrossingPoint.addActionListener(this);
        countArea.addActionListener(this);
        checkLine.addActionListener(this);
        checkTriangle.addActionListener(this);
        checkCircle.addActionListener(this);
        clean.addActionListener(this);

        toolPanel.add(drawPoint);
        toolPanel.add(drawLine);
        toolPanel.add(drawTriangle);
        toolPanel.add(drawCircle);
        toolPanel.add(countCrossingPoint);
        toolPanel.add(countArea);
        toolPanel.add(checkLine);
        toolPanel.add(checkTriangle);
        toolPanel.add(checkCircle);
        toolPanel.add(clean);

        //canvas Panel =================================================================================================
        canvasPanel = new CanvasPanel(this.dm);
        add(BorderLayout.CENTER, canvasPanel);

        canvasPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int x=e.getX()-395 - (dm.size /2);
                int y =305-e.getY() - (dm.size /2);

                dm.points.add(new Point(x,y));
                canvasPanel.repaint();
            }
        });
    }

    //Override =========================================================================================================
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        InputDialog dialog;
        if (source.equals(drawPoint)) {
            InputPoint point = new InputPoint(null);

            point.setLocationRelativeTo(null);
            point.setVisible(true);

            if (point.isOk()) {
                int x = point.getXx();
                int y = point.getYy();
                dm.points.add(new Point(x, y));
                canvasPanel.repaint();
            }
        }//=============================================================================================================
        else if(source.equals(drawLine)){
            if (dm.points.size() > 1) {
                dialog =new InputDialog(null, dm,dm.points,dm.points);

                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

                if(dialog.isOk()){
                    Point p1= (Point) dialog.getO1();
                    Point p2= (Point) dialog.getO2();

                    Line l=new Line(p1,p2);
                    dm.lines.add(l);
                    canvasPanel.repaint();
                    JOptionPane.showMessageDialog(null, "Equation of line:\n\t"+ l);
                }
            } else {
                JOptionPane.showMessageDialog(null, "You don't have any points or you have only one point.", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            }
        }//=============================================================================================================
        else if (source.equals(drawTriangle)) {
            if (dm.points.size() > 2) {
                InputTriangle triangle = new InputTriangle(null, dm);

                triangle.setLocationRelativeTo(null);
                triangle.setVisible(true);

                if (triangle.isOk()){
                    Point p1= triangle.getP1();
                    Point p2= triangle.getP2();
                    Point p3= triangle.getP3();
                    Triangle t=new Triangle(p1,p2,p3);
                    dm.triangles.add(t);
                    canvasPanel.repaint();
                }
            } else {
                JOptionPane.showMessageDialog(null, "You don't have any points or you don't have enough point.", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            }
        }//=============================================================================================================
        else if (source.equals(drawCircle)) {
            if (dm.points.size() > 0) {
                InputCircle circle = new InputCircle(null, dm);

                circle.setLocationRelativeTo(null);
                circle.setVisible(true);

                if (circle.isOk()) {
                    Point p = circle.getPoint1();
                    int r = circle.getR();
                    Circle c = new Circle(p, r);
                    dm.circles.add(c);
                    canvasPanel.repaint();
                }
            } else {
                JOptionPane.showMessageDialog(null, "You don't have any points.", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            }
        }//=============================================================================================================
        else if(source.equals(countCrossingPoint)) {
            if (dm.lines.size() > 1) {
                dialog =new InputDialog(null,dm,dm.lines,dm.lines);

                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

                if(dialog.isOk()) {
                    Line l1 = (Line) dialog.getO1();
                    Line l2 = (Line) dialog.getO2();

                    Point p = l1.crossingPointCramer(l1, l2);
                    dm.points.add(p);
                    canvasPanel.repaint();

                    JOptionPane.showMessageDialog(null, "The intersection point:\n\t" + p.toString());
                }
            } else {
                JOptionPane.showMessageDialog(null, "You don't have any lines or you have only one line.", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            }
        }//=============================================================================================================
        else if(source.equals(countArea)){
            if (dm.triangles.size() > 0) {
                JComboBox<String> jcb = new JComboBox<>();

                int i = 1;
                for (Triangle ignored : dm.triangles) {
                    jcb.addItem("Triangle " + i);
                    i++;
                }

                JOptionPane.showMessageDialog(null, jcb, "SELECT TRIANGLE", JOptionPane.QUESTION_MESSAGE);
                int index1 = jcb.getSelectedIndex();

                int size = dm.triangles.size();

                if (index1 < size) {
                    Triangle t = dm.triangles.get(index1);

                    double area=t.countArea(t.p1,t.p2,t.p3);

                    JOptionPane.showMessageDialog(null, "The triangle area="+area);
                }
            } else {
                JOptionPane.showMessageDialog(null, "You don't have any triangles.", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            }
        }//=============================================================================================================
        else if (source.equals(checkLine)) {
            if (dm.lines.size() > 0 && dm.points.size() > 0) {
                dialog =new InputDialog(null,dm,dm.points,dm.lines);

                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

                if(dialog.isOk()){
                    Line l= (Line) dialog.getO2();
                    Point p= (Point) dialog.getO1();

                    double check = l.checkPointLocation(p);

                    if (check == 0)
                        JOptionPane.showMessageDialog(null, "The point is on the line");
                    else if (check > 0)
                        JOptionPane.showMessageDialog(null, "The point is to right side of the line");
                    else
                        JOptionPane.showMessageDialog(null, "The point is to left side of the line");
                }
            } else {
                JOptionPane.showMessageDialog(null, "You don't have any lines or points.", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            }
        }//=============================================================================================================
        else if(source.equals(checkTriangle)){
            if (dm.triangles.size() > 0 && dm.points.size() > 0) {
                dialog =new InputDialog(null,dm,dm.points,dm.triangles);

                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

                if(dialog.isOk()) {
                    Triangle t = (Triangle) dialog.getO2();
                    Point p = (Point) dialog.getO1();

                    boolean check = t.checkPointLocation(p);

                    if (check)
                        JOptionPane.showMessageDialog(null, "The point is inside the triangle");
                    else
                        JOptionPane.showMessageDialog(null, "The point is outside the triangle");
                }
            } else {
                JOptionPane.showMessageDialog(null, "You don't have any triangles or points.", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            }
        }//=============================================================================================================
        else if (source.equals(checkCircle)){
            if (dm.circles.size() > 0 && dm.lines.size() > 0) {
                dialog =new InputDialog(null,dm,dm.lines,dm.circles);

                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

                if(dialog.isOk()){
                    Circle c = (Circle) dialog.getO2();
                    Line l = (Line) dialog.getO1();

                    ArrayList<Point> points=c.crossingPoints(l);

                    if (points.size()==2)
                        JOptionPane.showMessageDialog(null, "The line is crossing the circle in two points\np1=("+points.get(0).toString()+"),\n p2=("+points.get(1).toString()+")");
                    else if(points.size()==1)
                        JOptionPane.showMessageDialog(null, "The ine is crossing the circle in the one point\np=("+points.get(0).toString()+")");
                    else if(points.size()==0)
                        JOptionPane.showMessageDialog(null, "The line is not crossing the circle");

                    dm.points.addAll(points);
                    canvasPanel.repaint();
                }
            } else {
                JOptionPane.showMessageDialog(null, "You don't have any circles or lines.", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            }
        }//=============================================================================================================
        else if (source.equals(clean)){
            dm.points=new ArrayList<>();
            dm.lines=new ArrayList<>();
            dm.triangles=new ArrayList<>();
            dm.circles=new ArrayList<>();
            canvasPanel.repaint();
        }
    }
}
