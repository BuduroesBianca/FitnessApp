package fitness.app.GUI;

import fitness.app.services.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class MainScreen extends JFrame{

    private Service services = Service.getServicesInstance();

    MainScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("FitnessApp");
        setJMenuBar(mainMenuBar());
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
}
