package fitness.app.GUI;

import fitness.app.UsersDB;
import fitness.app.services.Service;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class InfoScreen extends JFrame {

    private Service services = Service.getServicesInstance();
    UsersDB udb = UsersDB.getDatabaseInstance();

    InfoScreen() {
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Form");
        setLayout(new BorderLayout());
        add(topPanel(), BorderLayout.NORTH);
        add(infoPanel(), BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    JPanel infoPanel() {

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);

        JLabel labelTitle = new JLabel("Please Fill This Form");
        labelTitle.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        labelTitle.setBounds(280, 30, 400, 30);
        infoPanel.add(labelTitle);

        JLabel labelAge = new JLabel("Age");
        labelAge.setBounds(180, 130, 200, 30);
        labelAge.setOpaque(true);
        labelAge.setBackground(Color.black);
        labelAge.setForeground(Color.white);
        labelAge.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelAge);

        JLabel labelHeight = new JLabel("Height");
        labelHeight.setBounds(180, 170, 200, 30);
        labelHeight.setOpaque(true);
        labelHeight.setBackground(Color.black);
        labelHeight.setForeground(Color.white);
        labelHeight.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelHeight);

        JLabel labelWeight = new JLabel("Weight");
        labelWeight.setBounds(180, 210, 200, 30);
        labelWeight.setOpaque(true);
        labelWeight.setBackground(Color.black);
        labelWeight.setForeground(Color.white);
        labelWeight.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelWeight);

        JLabel labelTargetWeight = new JLabel("Target Weight");
        labelTargetWeight.setBounds(180, 250, 200, 30);
        labelTargetWeight.setOpaque(true);
        labelTargetWeight.setBackground(Color.black);
        labelTargetWeight.setForeground(Color.white);
        labelTargetWeight.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelTargetWeight);


        JTextField ageTextField = new JTextField();
        ageTextField.setBounds(390, 130, 200, 30);
        infoPanel.add(ageTextField);

        JTextField heightTextField = new JTextField();
        heightTextField.setBounds(390, 170, 200, 30);
        infoPanel.add(heightTextField);

        JTextField weightTextField = new JTextField();
        weightTextField.setBounds(390, 210, 200, 30);
        infoPanel.add(weightTextField);

        JTextField targetWeightTextField = new JTextField();
        targetWeightTextField.setBounds(390, 250, 200, 30);
        infoPanel.add(targetWeightTextField);


        JButton jButton = new JButton("Submit");
        jButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        jButton.setBackground(Color.decode("#34b4eb"));
        jButton.setForeground(Color.WHITE);

        jButton.setBounds(330, 350, 110, 40);
        infoPanel.add(jButton);

        jButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String[] userData = new String[4];
                int countValidInput = 0;
                String field = ageTextField.getText();
                if (!(field.isEmpty() || field.isBlank() || field == null) && checkInput(field)) {
                    userData[0] = field;
                    countValidInput++;
                }
                else if(checkInput(field) == false){
                    showMessageDialog(null,"Please insert a number in the AGE field!");
                }
                field = heightTextField.getText();
                if (!(field.isEmpty() || field.isBlank() || field == null)&& checkInput(field)) {
                    userData[1] = field;
                    countValidInput++;
                }
                else if(checkInput(field) == false){
                    showMessageDialog(null,"Please insert a number in the HEIGHT field!");
                }
                field = weightTextField.getText();
                if (!(field.isEmpty() || field.isBlank() || field == null)&& checkInput(field)) {
                    userData[2] = field;
                    countValidInput++;
                }
                else if(checkInput(field) == false){
                    showMessageDialog(null,"Please insert a number in the WEIGHT field!");
                }
                field = targetWeightTextField.getText();
                if (!(field.isEmpty() || field.isBlank() || field == null)&& checkInput(field)) {
                    userData[3] = field;
                    countValidInput++;
                }
                else if(checkInput(field) == false){
                    showMessageDialog(null,"Please insert a number in the TARGET WEIGHT field!");
                }
                if(countValidInput != 4)
                    JOptionPane.showMessageDialog(getRootPane(), "All fields must be completed!", "Alert", JOptionPane.WARNING_MESSAGE);

                UsersDB.updateUser(services.getCurrentUser().getFname(), services.getCurrentUser().getLname(), "AGE", userData[0]);
                UsersDB.updateUser(services.getCurrentUser().getFname(), services.getCurrentUser().getLname(), "HEIGHT", userData[1]);
                UsersDB.updateUser(services.getCurrentUser().getFname(), services.getCurrentUser().getLname(), "WEIGHT", userData[2]);
                UsersDB.updateUser(services.getCurrentUser().getFname(), services.getCurrentUser().getLname(), "TARGETWEIGHT", userData[3]);

                LoadingScreen ls = new LoadingScreen();
                ls.setVisible(true);
                dispose();
            }

        });
        return infoPanel;
    }

    JPanel topPanel(){
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 20));
        JButton backToMainScreen = new JButton("<< Back to Main Page");
        backToMainScreen.addActionListener(e -> {
            dispose();
            VegetarianScreen vs = new VegetarianScreen();
            vs.setVisible(true);
        });
        topPanel.add(backToMainScreen);

        JLabel labelTitle = new JLabel("Let's Create Your Body Profile", JLabel.CENTER);

        labelTitle.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
        labelTitle.setBorder(new EmptyBorder(20, 70, 0, 0));
        topPanel.add(labelTitle);
        return topPanel;
    }

    private boolean checkInput(String input){
        if(input == null)
            return true;
        try{
            int nr = Integer.parseInt(input);
        } catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }
}


