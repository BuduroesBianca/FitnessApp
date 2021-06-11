package fitness.app.GUI;

import fitness.app.UsersDB;
import fitness.app.services.Service;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class Results extends  JFrame{

    private Service services = Service.getServicesInstance();

    private UsersDB udb=UsersDB.getDatabaseInstance();
    Results() {
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
        JMenu home = new JMenu("HOME");
        menuBar.add(home);
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

        dashboard.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                dispose();
                DashboardScreen gs = new DashboardScreen();
                gs.setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        home.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                dispose();
                MainScreen gs = new MainScreen();
                gs.setVisible(true);
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

        JLabel resultsIcon = new JLabel();
        ImageIcon img = new ImageIcon(new ImageIcon("Imagini/confet.gif").getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
        resultsIcon.setIcon(img);

        JLabel title=new JLabel("BMR");
        title.setFont(new Font("Microsoft YaHei UI", Font.BOLD,20));

        JLabel text = new JLabel("Basal metabolic rate is the number of calories your body");
        text.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,16));

        JLabel text1=new JLabel("needs to accomplish its most basic life-sustaining functions.");
        text1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,16));

        JLabel text2=new JLabel("Your BMR is:");
        text2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,16));

        double bmr = services.calculateBMR();
        JLabel BMR = new JLabel(String.valueOf(bmr));
        BMR.setFont(new Font("Microsoft YaHei UI", Font.BOLD,16));
//--------------------------
        JLabel title2=new JLabel("AMR");
        title2.setFont(new Font("Microsoft YaHei UI", Font.BOLD,20));

        JLabel text3 = new JLabel("Your AMR represents the number of calories you need");
        text3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,16));

        JLabel text4=new JLabel("to consume each day to stay at your current weight.");
        text4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,16));

        JLabel text5=new JLabel("Your AMR is:");
        text5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,16));

        double amr = services.calculateMaintainCalories();
        JLabel AMR = new JLabel(String.valueOf(amr));
        AMR.setFont(new Font("Microsoft YaHei UI", Font.BOLD,16));
//-----------------------------
        JLabel maintain=new JLabel("To maintain your weight you need to consume");
        maintain.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,16));

        JLabel maintain2=new JLabel(String.valueOf(amr));
        maintain2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,16));
        maintain2.setForeground(Color.red);

        JLabel maintain3=new JLabel("Calories per day:");
        maintain3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,16));
//---------------------------------------------
        JLabel lw=new JLabel("To lose weight you need to consume");
        lw.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,16));

        double lose=services.calculateLoseWeightCalories();
        System.out.println(lose);
        JLabel lw2=new JLabel(String.valueOf(lose));
        lw2.setFont(new Font("Microsoft YaHei UI", Font.BOLD,16));
        lw2.setForeground(Color.red);

        JLabel lw3=new JLabel("Calories per day:");
        lw3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,16));
        //----------------------------
        JLabel gw=new JLabel("To gain weight you need to consume");
        gw.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,16));

        double gain=services.calculateGainMuscleCalories();
        JLabel gw2=new JLabel(String.valueOf(gain));
        gw2.setFont(new Font("Microsoft YaHei UI", Font.BOLD,16));
        gw2.setForeground(Color.red);

        JLabel gw3=new JLabel("Calories per day:");
        gw3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,16));

        center.setBounds(160,100,470,350);

        title.setBounds(190, 10, 500, 40);
        text.setBounds(20, 35, 500, 50);
        text1.setBounds(10, 50,500, 50);
        text2.setBounds(120, 90,500, 50);
        BMR.setBounds(220,90,200,50);

        resultsIcon.setBounds(0,0,800,600);

        title2.setBounds(190, 140, 500, 40);
        text3.setBounds(20, 155, 500, 50);
        text4.setBounds(10, 170,500, 50);
        text5.setBounds(120, 210,500, 50);
        AMR.setBounds(220,210,200,50);

        BMR.setForeground(Color.decode("#001478"));
        AMR.setForeground(Color.decode("#001478"));
//------------------------------------------------
        maintain.setBounds(80, 260,500,40);
        maintain2.setBounds(255, 300,500,40);
        maintain3.setBounds(125, 300,500,40);
//-------------------------------------------------------------
        lw.setBounds(80, 260,500,40);
        lw2.setBounds(255, 300,500,40);
        lw3.setBounds(125, 300,500,40);
//-------------------------------------------------------------
        gw.setBounds(80, 260,500,40);
        gw2.setBounds(255, 300,500,40);
        gw3.setBounds(125, 300,500,40);

        lw.setVisible(false);
        lw2.setVisible(false);
        lw3.setVisible(false);
        gw.setVisible(false);
        gw2.setVisible(false);
        gw3.setVisible(false);
        maintain.setVisible(false);
        maintain2.setVisible(false);
        maintain3.setVisible(false);

        if(udb.getGoal(services.getCurrentUser().getUsername()).equalsIgnoreCase("Lose Weight"))
        {
            lw.setVisible(true);
            lw2.setVisible(true);
            lw3.setVisible(true);
        }
        else {
            if (udb.getGoal(services.getCurrentUser().getUsername()).equalsIgnoreCase("Gain Weight")) {
                gw.setVisible(true);
                gw2.setVisible(true);
                gw3.setVisible(true);
            } else {
                maintain.setVisible(true);
                maintain2.setVisible(true);
                maintain3.setVisible(true);
            }
        }

        infoPanel.add(center);
        infoPanel.add(resultsIcon);

        center.add(title);
        center.add(text);
        center.add(text1);
        center.add(text2);
        center.add(BMR);

        center.add(title2);
        center.add(text3);
        center.add(text4);
        center.add(text5);
        center.add(AMR);

        center.add(maintain);
        center.add(maintain2);
        center.add(maintain3);

        center.add(lw);
        center.add(lw2);
        center.add(lw3);

        center.add(gw);
        center.add(gw2);
        center.add(gw3);


        return infoPanel;
    }
}