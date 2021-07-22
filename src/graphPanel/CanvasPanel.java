package graphPanel;

import objects.Circle;
import objects.Line;
import objects.Point;
import objects.Triangle;

import javax.swing.*;
import java.awt.*;

public class CanvasPanel extends JPanel {
    DataManager dm;

    public CanvasPanel(DataManager dm){
        this.dm=dm;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(0, 300, 1000, 300);
        g.drawLine(400, 0, 400, 1000);

        int i = 1;
        for (Point p : dm.points) {
            g.fillOval(395 + p.x, 295 - p.y, dm.size, dm.size);
            g.drawString("P" + i, 395 + p.x, 295 - p.y);
            i++;
        }
        i = 1;
        for (Line l : dm.lines) {
            g.setColor(Color.BLUE);
            g.drawLine(400 + l.p1.x, 300 - l.p1.y, 400 + l.p2.x, 300 - l.p2.y);
            g.drawString("L" + i, 415 + (l.p1.x+l.p2.x)/2, 315 - (l.p1.y+l.p2.y)/2);
            i++;
        }

        i = 1;
        for (Triangle t : dm.triangles) {
            g.setColor(new Color(72,162,84));
            g.drawPolygon(new int[]{400 + t.p1.x, 400 + t.p2.x, 400 + t.p3.x}, new int[]{300 - t.p1.y, 300 - t.p2.y, 300 - t.p3.y}, 3);
            g.drawString("T" + i, 410 + t.p3.x, 310 - t.p3.y);
            i++;
        }

        i = 1;
        for (Circle c : dm.circles) {
            g.setColor(Color.RED);
            g.drawOval(400 + c.p.x - c.r , 300 - c.p.y - c.r, c.r*2, c.r*2);
            g.drawString("C" + i, 410 + c.p.x, 310 - c.p.y);
            i++;
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }
}
