package fitness.app.GUI;

import fitness.app.services.Service;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class MainScreen extends JFrame{

    private Service services = Service.getServicesInstance();

    MainScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(800, 600);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("FitnessApp");
        setLayout(new BorderLayout());
        setJMenuBar(mainMenuBar());
        add(infoPanel(), BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    JMenuBar mainMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu logOut = new JMenu("LOG OUT");
        JMenu quiz = new JMenu("QUIZ");
        JMenu profile = new JMenu("MY PROFILE");
        JMenu dashboard = new JMenu("DASHBOARD");
        menuBar.add(quiz);
        menuBar.add(profile);
        menuBar.add(dashboard);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(logOut);

        logOut.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                int logOut = JOptionPane.showConfirmDialog(getRootPane(), "Are you sure you want to log out?", "LOG OUT",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (logOut == JOptionPane.YES_OPTION) {
                    services.logOut();
                    new LoginScreen();
                    dispose();
                }
            }
            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        profile.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                dispose();
                ProfileScreen accountScreen = new ProfileScreen();
                accountScreen.setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        quiz.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                dispose();
                GenderScreen gs = new GenderScreen();
                gs.setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        return menuBar;
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

        JLabel homeIcon = new JLabel();

        JLabel text = new JLabel("Are you tired of this sedentary lifestyle?");
        text.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,22));

        JLabel text1 = new JLabel("Do you want to lose weight, gain weight, or just get a healthy lifestyle?");
        text.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,22));

        JLabel text2 = new JLabel("Do you want to have a personalised meal plan for every day of your diet?");
        text.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,22));

        JLabel text3 = new JLabel("Do you want a proffesional workout to get in form?");
        text.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,22));

        JLabel text4 = new JLabel("It's never to early or too late to work towards being the healthiest you!");
        text.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,22));


        ImageIcon img = new ImageIcon(new ImageIcon("Imagini/homepage.jpeg").getImage().getScaledInstance(300,225,Image.SCALE_DEFAULT));
        homeIcon.setIcon(img);

        JLabel text5 = new JLabel("Take our quiz and start your journey!");
        text.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,22));

        JButton quizButton = new JButton("QUIZ");
        quizButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD,15));
        quizButton.setOpaque(true);
        quizButton.setBackground(Color.decode("#d63a51"));
        quizButton.setForeground(Color.WHITE);
        quizButton.setBorder(BorderFactory.createLineBorder(Color.decode("#d63a51"), 5));
        quizButton.setBorderPainted(true);

        quizButton.addActionListener(e -> {
            GenderScreen g = new GenderScreen();
            g.setVisible(true);
            dispose();
        });

        center.setBounds(160,0,470,600);
        homeIcon.setBounds(90,180,300,225);
        text.setBounds(15,0,450,100);
        text1.setBounds(15,25,490,150);
        text2.setBounds(15,50,500,150);
        text3.setBounds(75,75,500,150);
        text4.setBounds(25,95, 500, 150);
        text5.setBounds(120, 320, 300, 200);
        quizButton.setBounds(180, 460, 100, 40);



        infoPanel.add(center);
        center.add(text);
        center.add(text1);
        center.add(text2);
        center.add(text3);
        center.add(text4);
        center.add(homeIcon);
        center.add(text5);
        center.add(quizButton);
       /* infoPanel.add(homeIcon);
        infoPanel.add(text);
        infoPanel.add(text1);
        infoPanel.add(text2);
        infoPanel.add(text3);*/

        return infoPanel;

    }
}