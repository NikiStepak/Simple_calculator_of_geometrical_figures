package graphPanel.dialogs;

import graphPanel.DataManager;
import objects.Circle;
import objects.Line;
import objects.Point;
import objects.Triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InputDialog extends JDialog implements ActionListener {
    private Object o1;
    private Object o2;
    private final JButton bOk;
    private boolean okData;
    private final JComboBox<String> box1;
    private final JComboBox<String> box2;
    DataManager dm;
    ArrayList list1, list2;

    public InputDialog(JFrame owner, DataManager dm, ArrayList list1, ArrayList list2) {
        super(owner,"SELECT",true);
        this.dm=dm;
        this.list1 =list1;
        this.list2=list2;
        setSize(new Dimension(215, 130));
        setLayout(null);

        //==============================================================================================================

        JLabel pLabel = new JLabel("Select:");
        pLabel.setBounds(5,5,100,20);
        add(pLabel);

        box1 = new JComboBox<>();
        int i = 1;
        for (Object l : list1) {
            if(l instanceof Point) 
                box1.addItem("Point " + i);
            else if(l instanceof Line)
                box1.addItem("Line "+i);
            else if(l instanceof Triangle)
                box1.addItem("Triangle "+i);
            else if(l instanceof Circle)
                box1.addItem("Circle "+i);
            i++;
        }
        box1.setBounds(90,5,100,20);
        add(box1);

        //==============================================================================================================

        pLabel =new JLabel("Select:");
        pLabel.setBounds(5,30,100,20);
        add(pLabel);

        box2 = new JComboBox<>();
        i = 1;
        for (Object c : list2) {
            if(c instanceof Point)
                box2.addItem("Point "+i);
            else if(c instanceof Line)
                box2.addItem("Line "+i);
            else if(c instanceof Triangle)
                box2.addItem("Triangle "+i);
            else if(c instanceof Circle)
                box2.addItem("Circle "+i);
            i++;
        }
        box2.setBounds(90,30,100,20);
        add(box2);

        //==============================================================================================================

        bOk = new JButton("OK");
        bOk.setBounds(80, 60, 55, 20);
        bOk.addActionListener(this);
        add(bOk);
    }

    public Object getO1() {
        return o1;
    }

    public Object getO2() {
        return o2;
    }

    public boolean isOk() {
        return okData;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bOk) {
            if(list1.get(0)instanceof Point)
                this.o1=dm.points.get(box1.getSelectedIndex());
            else if(list1.get(0) instanceof Line)
                this.o1 = dm.lines.get(box1.getSelectedIndex());
            else if(list1.get(0) instanceof Triangle)
                this.o1 = dm.triangles.get(box1.getSelectedIndex());
            else if(list1.get(0) instanceof Circle)
                this.o1=dm.circles.get(box1.getSelectedIndex());

            if(list2.get(0)instanceof Point)
                this.o2=dm.points.get(box2.getSelectedIndex());
            else if(list2.get(0) instanceof Line)
                this.o2 = dm.lines.get(box2.getSelectedIndex());
            else if(list2.get(0) instanceof Triangle)
                this.o2 = dm.triangles.get(box2.getSelectedIndex());
            else if(list2.get(0) instanceof Circle)
                this.o2=dm.circles.get(box2.getSelectedIndex());

            this.okData = true;
        }
        setVisible(false);
    }
}
