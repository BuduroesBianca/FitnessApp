package fitness.app.GUI;

import fitness.app.users.User;
import fitness.app.services.Service;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProfileScreen extends JFrame {

    private Service services = Service.getServicesInstance();
    private JTextField lastNameTextField,firstNameTextField,emailTextField,usernameTextField,passwordField;
    private boolean editMode = false;

    ProfileScreen() {
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("My Profile");
        setLayout(new BorderLayout());
        add(topPanel(), BorderLayout.NORTH);
        add(infoPanel(), BorderLayout.CENTER);
        add(bottomPanel(), BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    JPanel infoPanel(){
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setOpaque(true);
        infoPanel.setBackground(Color.decode("#f5ebd5"));

        JLabel labelLastName = new JLabel("Last Name:");
        labelLastName.setBounds(190, 185, 200, 30);
        labelLastName.setOpaque(true);
        labelLastName.setBackground(Color.black);
        labelLastName.setForeground(Color.white);
        labelLastName.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelLastName);

        JLabel labelFirstName = new JLabel("First Name:");
        labelFirstName.setBounds(190, 160, 200, 30);
        labelFirstName.setOpaque(true);
        labelFirstName.setBackground(Color.black);
        labelFirstName.setForeground(Color.white);
        labelFirstName.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelFirstName);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setBounds(190, 200, 200, 30);
        labelEmail.setOpaque(true);
        labelEmail.setBackground(Color.black);
        labelEmail.setForeground(Color.white);
        labelEmail.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelEmail);


        JLabel labelUsername = new JLabel("Username:");
        labelUsername.setBounds(190, 240, 200, 30);
        labelUsername.setOpaque(true);
        labelUsername.setBackground(Color.black);
        labelUsername.setForeground(Color.white);
        labelUsername.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelUsername);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(190, 280, 200, 30);
        labelPassword.setOpaque(true);
        labelPassword.setBackground(Color.black);
        labelPassword.setForeground(Color.white);
        labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelPassword);

        String[] data = ((User)services.getCurrentUser()).getObjectData();
        lastNameTextField = new JTextField();
        lastNameTextField.setBounds(400, 120, 200, 30);
        lastNameTextField.setText(data[1]);
        lastNameTextField.setEnabled(false);
        infoPanel.add(lastNameTextField);

        firstNameTextField = new JTextField();
        firstNameTextField.setBounds(400, 160, 200, 30);
        firstNameTextField.setText(data[2]);
        firstNameTextField.setEnabled(false);
        infoPanel.add(firstNameTextField);

        emailTextField = new JTextField();
        emailTextField.setBounds(400, 200, 200, 30);
        emailTextField.setText(data[3]);
        emailTextField.setEnabled(false);
        infoPanel.add(emailTextField);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(400, 240, 200, 30);
        usernameTextField.setText(data[4]);
        usernameTextField.setEnabled(false);
        infoPanel.add(usernameTextField);

        passwordField = new JTextField();
        passwordField.setBounds(400, 280, 200, 30);
        passwordField.setText(data[5]);
        passwordField.setEnabled(false);
        infoPanel.add(passwordField);

        JLabel labelTitle = new JLabel("MY PROFILE", JLabel.CENTER);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitle.setBounds(260,30,200,30);
        labelTitle.setBorder(new EmptyBorder(0, 60, 0, 0));
        infoPanel.add(labelTitle);

        return infoPanel;
    }

    JPanel topPanel(){
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 20));
        JButton backToMainScreen = new JButton("Back to Main Page");
        backToMainScreen.setOpaque(true);
        backToMainScreen.setBackground(Color.black);
        backToMainScreen.setForeground(Color.white);
        backToMainScreen.setBorderPainted(true);
        backToMainScreen.setBorder(BorderFactory.createLineBorder(Color.black, 8));
        backToMainScreen.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));

        backToMainScreen.addActionListener(e -> {
            dispose();
            MainScreen mainScreen = new MainScreen();
            mainScreen.setVisible(true);
        });
        topPanel.add(backToMainScreen);

        return topPanel;
    }

    JPanel bottomPanel(){
        JPanel bottomPanel = new JPanel();
        GridLayout layout = new GridLayout(1, 2);
        layout.setHgap(5);
        bottomPanel.setLayout(layout);

        JButton deleteButton = new JButton("DELETE ACCOUNT");
        deleteButton.setOpaque(true);
        deleteButton.setBackground(Color.decode("#999999"));
        deleteButton.setForeground(Color.white);

        deleteButton.addActionListener(e -> {
            int deleteAccount = JOptionPane.showConfirmDialog(getRootPane(),"Are you sure you want to delete your account?","DELETE ACCOUNT",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(deleteAccount == JOptionPane.YES_OPTION){
                try {
                    services.deleteAccount();
                    new LoginScreen();
                    dispose();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        bottomPanel.add(deleteButton);

        return bottomPanel;
    }
}
