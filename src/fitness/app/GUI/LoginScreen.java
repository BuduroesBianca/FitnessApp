package fitness.app.GUI;


import fitness.app.services.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame{
    static private GridBagLayout gridBag;
    static private GridBagConstraints gridCons;
    static private JPanel panel;
    private Service services = Service.getServicesInstance();

    static void addComponentInGrid(Component c, int x, int y, int w, int h){
        gridCons.gridx = x;
        gridCons.gridy = y;
        gridCons.gridwidth = w;
        gridCons.gridheight = h;
        gridBag.setConstraints(c, gridCons);
        panel.add(c);
    }

    public LoginScreen(){
        setTitle("LOG IN PAGE");
        panel = (JPanel) getContentPane();
        gridBag = new GridBagLayout();
        panel.setLayout(gridBag);
        panel.setOpaque(true);
        panel.setBackground(Color.decode("#f5ebd5"));
        JLabel loginIcon = new JLabel();

        ImageIcon img = new ImageIcon(new ImageIcon("Imagini/login.gif").getImage().getScaledInstance(180,250,Image.SCALE_DEFAULT));
        loginIcon.setIcon(img);


        gridCons = new GridBagConstraints();
        gridCons.weightx = 1;
        gridCons.weighty = 1;
        gridCons.insets = new Insets(6,6,6,6);
        JLabel labelLogin = new JLabel("LOGIN", JLabel.CENTER);
        labelLogin.setFont(new Font("Arial",Font.BOLD,22));
        gridCons.fill = GridBagConstraints.BOTH;

        JLabel labelIcon = new JLabel();
        labelIcon.setIcon(img);
        labelIcon.setHorizontalAlignment(JLabel.CENTER);

        addComponentInGrid(labelIcon,0,0,3,1);
        //addComponentInGrid(loginIcon,1,1,3,1);

        JLabel labelUsername = new JLabel("Username:");
        gridCons.fill = GridBagConstraints.NONE;
        gridCons.anchor = GridBagConstraints.CENTER;
        addComponentInGrid(labelUsername,0,1,1,3);
        labelUsername.setBounds(0,300,100,20);

        JLabel labelPassword = new JLabel("Password:");

        addComponentInGrid(labelPassword,0,2,1,3);

        gridCons.fill = GridBagConstraints.HORIZONTAL;
        gridCons.anchor = GridBagConstraints.CENTER;
        JTextField textUsername = new JTextField("",30);
        JPasswordField textPassword = new JPasswordField("",30);
        textPassword.setBorder(BorderFactory.createLineBorder(Color.white));
        addComponentInGrid(textUsername,1,1,2,3);
        addComponentInGrid(textPassword,1,2,2,3);
        textUsername.setBorder(BorderFactory.createLineBorder(Color.white));

        JButton logInButton = new JButton("LOG IN");

       /*logInButton.addActionListener(e -> {
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
        });*/
      logInButton.addActionListener(e -> {
            DashboardScreen g = new DashboardScreen();
            g.setVisible(true);
            dispose();
        });

        gridCons.fill = GridBagConstraints.HORIZONTAL;
        addComponentInGrid(logInButton,1,4,2,1);

        JButton signUpButton = new JButton("SIGN UP");
        signUpButton.addActionListener(e -> {
            fitness.app.GUI.RegisterScreen registerScreen = new fitness.app.GUI.RegisterScreen();
            dispose();
            registerScreen.setVisible(true);
        });
        addComponentInGrid(signUpButton,3,4,2,1);

        setSize(new Dimension(500,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);

    }

}
