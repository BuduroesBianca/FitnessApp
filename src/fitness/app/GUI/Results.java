package fitness.app.GUI;

import fitness.app.services.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class Results extends  JFrame{

    private Service services = Service.getServicesInstance();

    Results() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(800, 600);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("FitnessApp");
        setLayout(new BorderLayout());
        add(infoPanel(), BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    JPanel infoPanel(){

        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(true);
        infoPanel.setBackground(Color.decode("#1f7e8f"));
        infoPanel.setLayout(null);

        JPanel center = new JPanel();
        center.setLayout(null);
        center.setOpaque(true);
        center.setBackground(Color.WHITE);


        double bmr = services.calculateBMR();
        JLabel BMR = new JLabel(String.valueOf(bmr));
        BMR.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,22));


        center.setBounds(160,0,470,600);
        BMR.setBounds(25,10,450,100);


        infoPanel.add(center);
        center.add(BMR);


        return infoPanel;
    }
}
