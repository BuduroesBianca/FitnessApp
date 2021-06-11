package fitness.app.GUI;


import fitness.app.services.Service;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame{
    static private GridBagLayout gridBag;
    static private GridBagConstraints gridCons;
    private Service services = Service.getServicesInstance();


    public LoginScreen() {
        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("LOG IN PAGE");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(true);
        infoPanel.setBackground(Color.decode("#f5ebd5"));
        infoPanel.setLayout(null);
        setLayout(new BorderLayout());

        JLabel loginIcon = new JLabel();

        ImageIcon img = new ImageIcon(new ImageIcon("Imagini/login.gif").getImage().getScaledInstance(180,250,Image.SCALE_DEFAULT));
        loginIcon.setIcon(img);

        loginIcon.setBounds(150,40,180,200);
        infoPanel.add(loginIcon);


        JLabel labelIcon = new JLabel();
        labelIcon.setIcon(img);
        labelIcon.setHorizontalAlignment(JLabel.CENTER);


        JLabel labelUsername = new JLabel("Username",JLabel.CENTER);
        labelUsername.setBounds(30,250,200,30);
        labelUsername.setForeground(Color.white);
        labelUsername.setOpaque(true);
        labelUsername.setFont(new Font("Microsoft YaHei UI", Font.BOLD,15));
        labelUsername.setBackground(Color.black);
        infoPanel.add(labelUsername);

        JLabel labelPassword = new JLabel("Password",JLabel.CENTER);
        labelPassword.setForeground(Color.white);
        labelPassword.setOpaque(true);
        labelPassword.setFont(new Font("Microsoft YaHei UI", Font.BOLD,15));
        labelPassword.setBackground(Color.black);
        labelPassword.setBounds(30,290,200,30);
        infoPanel.add(labelPassword);


        JTextField textUsername = new JTextField("",30);
        JPasswordField textPassword = new JPasswordField("",30);
        textUsername.setBounds(240,250,200,30);
        textPassword.setBounds(240,290,200,30);
        textPassword.setFont(new Font("Microsoft YaHei UI", Font.BOLD,15));
        textUsername.setFont(new Font("Microsoft YaHei UI", Font.BOLD,15));
        textPassword.setBorder(BorderFactory.createLineBorder(Color.white));
        textUsername.setBorder(BorderFactory.createLineBorder(Color.white));

        infoPanel.add(textUsername);
        infoPanel.add(textPassword);

        JButton logInButton = new JButton("LOG IN");
        logInButton.setBounds(160,360,150,30);
        logInButton.setOpaque(true);
        logInButton.setBackground(Color.decode("#999999"));
        logInButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD,15));
        logInButton.setForeground(Color.WHITE);

        infoPanel.add(logInButton);

        logInButton.addActionListener(e -> {
            String username = textUsername.getText();
            String password = String.valueOf(textPassword.getPassword());
            boolean invalidUsernameInput = username.isEmpty() || username.isBlank() || username == null;
            boolean invalidPasswordInput = password.isEmpty() || password.isBlank() || password == null;
            if (invalidUsernameInput || invalidPasswordInput) {
                JOptionPane.showMessageDialog(getRootPane(), "All fields must be completed!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                if (username.contains(" ")) {
                    JOptionPane.showMessageDialog(getRootPane(), "Username must not contain spaces", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (password.contains(" ")) {
                        JOptionPane.showMessageDialog(getRootPane(), "Password must not contain spaces", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int checkUser = services.logInUser(username, password);
                        if (checkUser == 0) {
                            JOptionPane.showMessageDialog(getRootPane(), String.format("Welcome back to FitnessApp,\n %s ", username));
                            //MainScreen mainScreen = new MainScreen();
                            MainScreen mainScreen = new MainScreen();
                            mainScreen.setVisible(true);
                            dispose();
                        } else {
                            if (checkUser == 1)
                                JOptionPane.showMessageDialog(getRootPane(), "This username does not exist.", "Alert", JOptionPane.WARNING_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(getRootPane(), "Invalid password.", "Alert", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        });
      /*logInButton.addActionListener(e -> {
            DashboardScreen g = new DashboardScreen();
            g.setVisible(true);
            dispose();
        });*/

        JButton signUpButton = new JButton("SIGN UP");

        JLabel text = new JLabel("You Don't Have an Account?",JLabel.CENTER);
        text.setFont(new Font("Microsoft YaHei UI", Font.BOLD,13));
        text.setBounds(0,430,250,30);
        text.setBorder(BorderFactory.createLineBorder(Color.white,3));

        signUpButton.setOpaque(true);
        signUpButton.setBackground(Color.white);
        signUpButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD,15));
        signUpButton.setBounds(250,430,235,30);
        signUpButton.setBorder(BorderFactory.createLineBorder(Color.white,3));

        infoPanel.add(signUpButton);
        infoPanel.add(text);
        signUpButton.addActionListener(e -> {
            fitness.app.GUI.RegisterScreen registerScreen = new fitness.app.GUI.RegisterScreen();
            dispose();
            registerScreen.setVisible(true);
        });
        setContentPane(infoPanel);

    }


}
