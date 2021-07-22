package graphPanel.dialogs;

import graphPanel.DataManager;
import objects.Point;
import objects.Triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputTriangle extends JDialog implements ActionListener {
    private Point p1, p2, p3;
    private final JButton bOk;
    private boolean okData;
    private final JComboBox<String> box1, box2, box3;
    DataManager dm;

    public InputTriangle(JFrame owner, DataManager dm) {
        super(owner, "NEW TRIANGLE", true);
        this.dm = dm;
        setSize(new Dimension(215, 150));
        setLayout(null);

        //==============================================================================================================

        JLabel pLabel = new JLabel("Select point:");
        pLabel.setBounds(5, 5, 100, 20);
        add(pLabel);

        box1 = new JComboBox<>();
        int i = 1;
        for (Point ignored : dm.points) {
            box1.addItem("Point " + i);
            i++;
        }
        box1.setBounds(90, 5, 100, 20);
        add(box1);

        //==============================================================================================================

        pLabel = new JLabel("Select point:");
        pLabel.setBounds(5, 30, 100, 20);
        add(pLabel);

        box2 = new JComboBox<>();
        i = 1;
        for (Point ignored : dm.points) {
            box2.addItem("Point " + i);
            i++;
        }
        box2.setBounds(90, 30, 100, 20);
        add(box2);

        //==============================================================================================================

        pLabel = new JLabel("Select point:");
        pLabel.setBounds(5, 55, 100, 20);
        add(pLabel);

        box3 = new JComboBox<>();
        i = 1;
        for (Point ignored : dm.points) {
            box3.addItem("Point " + i);
            i++;
        }
        box3.setBounds(90, 55, 100, 20);
        add(box3);

        //==============================================================================================================

        bOk = new JButton("OK");
        bOk.setBounds(80, 85, 55, 20);
        bOk.addActionListener(this);
        add(bOk);
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Point getP3() {
        return p3;
    }

    public boolean isOk() {
        return okData;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bOk) {
            this.p1 = dm.points.get(box1.getSelectedIndex());
            this.p2 = dm.points.get(box2.getSelectedIndex());
            this.p3 = dm.points.get(box3.getSelectedIndex());

            Triangle t = new Triangle(p1, p2, p3);
            if (t.check())
                this.okData = true;
        }
        setVisible(false);
    }
}
