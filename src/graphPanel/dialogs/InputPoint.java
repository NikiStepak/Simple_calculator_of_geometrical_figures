package graphPanel.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputPoint extends JDialog implements ActionListener {
    private final JTextField xField;
    private final JTextField yField;
    private final JButton bOk;
    private boolean okData;

    public InputPoint(JFrame owner) {
        super(owner,"NEW POINT",true);
        setSize(new Dimension(200, 130));
        setLayout(null);

        JLabel xLabel = new JLabel("Enter x", JLabel.RIGHT);
        xLabel.setBounds(0, 5, 50, 20);
        add(xLabel);

        xField = new JTextField("0");
        xField.setBounds(60, 5, 50, 20);
        add(xField);

        JLabel yLabel = new JLabel("Enter y", JLabel.RIGHT);
        yLabel.setBounds(0, 30, 50, 20);
        add(yLabel);

        yField = new JTextField("0");
        yField.setBounds(60, 30, 50, 20);
        add(yField);

        bOk = new JButton("OK");
        bOk.setBounds(15, 60, 55, 20);
        bOk.addActionListener(this);
        add(bOk);
    }

    public int getXx() {
        return Integer.parseInt(xField.getText());
    }

    public int getYy() {
        return Integer.parseInt(yField.getText());
    }

    public boolean isOk() {
        return okData;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if(source==bOk)
            this.okData=true;
        setVisible(false);
    }
}