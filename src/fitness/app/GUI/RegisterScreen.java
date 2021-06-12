package fitness.app.GUI;

import fitness.app.services.Service;

import javax.swing.*;
import java.awt.*;

public class RegisterScreen extends JFrame {
    private Service services = Service.getServicesInstance();

    RegisterScreen() {
        setSize(550, 550);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Registration Form");
        setLocationRelativeTo(null);


        JLabel labelTitle = new JLabel("New User Registration");
        labelTitle.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
        labelTitle.setBounds(160, 70, 400, 30);

        add(labelTitle);

        JLabel labelLastName = new JLabel("Last Name",JLabel.CENTER);
        labelLastName.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
        labelLastName.setBounds(65, 160, 200, 30);
        labelLastName.setOpaque(true);
        labelLastName.setBackground(Color.black);
        labelLastName.setForeground(Color.WHITE);
        add(labelLastName);

        JLabel labelFirstName = new JLabel("First Name",JLabel.CENTER);
        labelFirstName.setBounds(65, 200, 200, 30);
        labelFirstName.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
        labelFirstName.setOpaque(true);
        labelFirstName.setBackground(Color.black);
        labelFirstName.setForeground(Color.WHITE);
        add(labelFirstName);

        JLabel labelEmail = new JLabel("Email",JLabel.CENTER);
        labelEmail.setBounds(65, 240, 200, 30);
        labelEmail.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
        labelEmail.setOpaque(true);
        labelEmail.setBackground(Color.black);
        labelEmail.setForeground(Color.WHITE);
        add(labelEmail);

        JLabel labelUsername = new JLabel("Username",JLabel.CENTER);
        labelUsername.setBounds(65, 280, 200, 30);
        labelUsername.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
        labelUsername.setOpaque(true);
        labelUsername.setBackground(Color.black);
        labelUsername.setForeground(Color.WHITE);
        add(labelUsername);

        JLabel labelPassword = new JLabel("Password",JLabel.CENTER);
        labelPassword.setBounds(65, 320, 200, 30);
        labelPassword.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
        labelPassword.setOpaque(true);
        labelPassword.setBackground(Color.black);
        labelPassword.setForeground(Color.WHITE);
        add(labelPassword);

        JTextField lastNameTextField = new JTextField();
        lastNameTextField.setBounds(270, 160, 200, 30);
        lastNameTextField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
        add(lastNameTextField);

        JTextField firstNameTextField = new JTextField();
        firstNameTextField.setBounds(270, 200, 200, 30);
        firstNameTextField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
        add(firstNameTextField);


        JTextField emailTextField = new JTextField();
        emailTextField.setBounds(270, 240, 200, 30);
        emailTextField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
        add(emailTextField);

        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(270, 280, 200, 30);
        usernameTextField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
        add(usernameTextField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(270, 320, 200, 30);
        passwordField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
        add(passwordField);

        JButton submitButton = new JButton("ADD ACCOUNT");
        submitButton.setOpaque(true);
        submitButton.setBackground(Color.decode("#999999"));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));

        submitButton.setBounds(110, 390, 300, 30);
        add(submitButton);

        submitButton.addActionListener(e -> {
            String[] userData =  new String[5];
            int countValidInput = 0;
            String field = lastNameTextField.getText();
            if(!(field.isEmpty() || field.isBlank() || field==null)) {
                userData[0] = field;
                countValidInput++;
            }
            field = firstNameTextField.getText();
            if(!(field.isEmpty() || field.isBlank() || field==null)) {
                userData[1] = field;
                countValidInput++;
            }
            field = emailTextField.getText();
            if(!(field.isEmpty() || field.isBlank() || field==null)) {
                userData[2] = field;
                countValidInput++;
            }
            field = usernameTextField.getText();
            if(!(field.isEmpty() || field.isBlank() || field==null)) {
                userData[3] = field;
                countValidInput++;
            }
            field = String.valueOf(passwordField.getPassword());
            if(!(field.isEmpty() || field.isBlank() || field==null)) {
                userData[4] = field;
                countValidInput++;
            }

            if(countValidInput==5) {
                if (userData[3].contains(" ")) {
                    JOptionPane.showMessageDialog(getRootPane(), "Username must not contain spaces", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (userData[4].contains(" ")) {
                        JOptionPane.showMessageDialog(getRootPane(), "Password must not contain spaces", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        boolean checkRegistration = services.registerUser(userData);
                        if (checkRegistration) {
                            JOptionPane.showMessageDialog(getRootPane(), "Successful user registration!");
                            EventQueue.invokeLater(new Runnable()
                            {
                                public void run()
                                {
                                    try
                                    {
                                        fitness.app.GUI.LoginScreen ls = new fitness.app.GUI.LoginScreen();
                                        ls.setVisible(true);
                                    }
                                    catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            dispose();
                        } else
                            JOptionPane.showMessageDialog(getRootPane(), "Username already exists.", "Alert", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            else
                JOptionPane.showMessageDialog(getRootPane(), "All fields must be completed!", "Alert", JOptionPane.WARNING_MESSAGE);

        });



        JLabel text = new JLabel("Already Have an Account?",JLabel.CENTER);
        text.setFont(new Font("Microsoft YaHei UI", Font.BOLD,13));
        text.setBounds(0,480,280,30);
        text.setBorder(BorderFactory.createLineBorder(Color.white,3));
        add(text);

        JButton loginButton = new JButton("SIGN IN");

        loginButton.setOpaque(true);
        loginButton.setBackground(Color.white);
        loginButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD,15));
        loginButton.setBounds(280,480,255,30);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.white,3));
        add(loginButton);


        loginButton.addActionListener(e -> {
            EventQueue.invokeLater(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        fitness.app.GUI.LoginScreen ls = new fitness.app.GUI.LoginScreen();
                        ls.setVisible(true);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            dispose();
        });

    }
}