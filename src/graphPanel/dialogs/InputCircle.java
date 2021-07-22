package graphPanel.dialogs;

import graphPanel.DataManager;
import objects.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputCircle extends JDialog implements ActionListener {
    private Point p1;
    private final JButton bOk;
    private boolean okData;
    private final JComboBox<String> box;
    private final JTextField r;
    DataManager dm;

    public InputCircle(JFrame owner, DataManager dm)  {
        super(owner, "NEW CIRCLE", true);
        this.dm=dm;
        setSize(new Dimension(215, 130));
        setLayout(null);

        //==============================================================================================================

        JLabel pLabel = new JLabel("Select point:");
        pLabel.setBounds(5,5,100,20);
        add(pLabel);

        box = new JComboBox<>();
        int i = 1;
        for (Point ignored : dm.points) {
            box.addItem("Point " + i);
            i++;
        }
        box.setBounds(90,5,100,20);
        add(box);

        //==============================================================================================================

        pLabel =new JLabel("Enter radius:");
        pLabel.setBounds(5,30,100,20);
        add(pLabel);

        r=new JTextField("0");
        r.setBounds(90,30,100,20);
        add(r);

        //==============================================================================================================

        bOk = new JButton("OK");
        bOk.setBounds(80, 60, 55, 20);
        bOk.addActionListener(this);
        add(bOk);
    }

    public Point getPoint1() {
        return p1;
    }

    public int getR() {
        return Integer.parseInt(r.getText());
    }

    public boolean isOk() {
        return okData;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bOk) {
            this.p1=dm.points.get(box.getSelectedIndex());

            this.okData = true;
        }
        setVisible(false);
    }
}
