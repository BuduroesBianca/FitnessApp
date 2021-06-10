package fitness.app.GUI;



import fitness.app.UsersDB;
import fitness.app.services.Service;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import static javax.swing.JOptionPane.showMessageDialog;

public class LoadingScreen extends JFrame{

    private Service services = Service.getServicesInstance();
    UsersDB udb = UsersDB.getDatabaseInstance();
    Timer timer;

    public LoadingScreen() {
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Loading...");
        setLayout(new BorderLayout());
        add(infoPanel(), BorderLayout.CENTER);
        setLocationRelativeTo(null);
        timer = new Timer(5000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        timer.start();
        timer.setRepeats(false);
    }

    JPanel infoPanel(){
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(true);
        infoPanel.setBackground(Color.white);
        infoPanel.setLayout(null);

        JLabel loadingIcon = new JLabel();

        ImageIcon img = new ImageIcon(new ImageIcon("Imagini/loading.gif").getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
        loadingIcon.setIcon(img);

        JLabel text = new JLabel("Calculating Your Results ...");
        text.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,22));

        loadingIcon.setBounds(0,40,550,400);
        text.setBounds(260,350,400,200);



        infoPanel.add(loadingIcon);
        infoPanel.add(text);

        return infoPanel;

    }

    public void shoutoff(){
        if (!timer.isRunning()) {
            timer.start();
        }
    }
}
