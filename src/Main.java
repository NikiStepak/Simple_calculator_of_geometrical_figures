import graphPanel.DataManager;
import graphPanel.MyPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    private JPanel panel;

    DataManager dm;

    Main(){
        this.dm=new DataManager();
    }

    public  static void main(String[] args){
        Main app=new Main();

        JFrame frame=new JFrame("App");

        app.panel=new MyPanel(app.dm); //main panel

        //frame ======================================================================
        frame.setContentPane(app.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800,750));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
