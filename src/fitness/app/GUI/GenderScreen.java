package fitness.app.GUI;

import fitness.app.User;
import fitness.app.UsersDB;
import fitness.app.services.Service;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JOptionPane.showMessageDialog;

public class GenderScreen extends JFrame {

    private Service services = Service.getServicesInstance();
    UsersDB udb = UsersDB.getDatabaseInstance();
    private int femaleSelected = 0;
    private int maleSelected = 0;

    public GenderScreen() {
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Gender");
        setLayout(new BorderLayout());
        add(topPanel(), BorderLayout.NORTH);
        add(infoPanel(), BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    JPanel infoPanel(){
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);

        JRadioButton female = new JRadioButton();
        JRadioButton male = new JRadioButton();
        JButton jButton = new JButton("Submit");
        JLabel gender = new JLabel("Select your gender",JLabel.CENTER);


        ImageIcon icon = new ImageIcon(new ImageIcon("C:/Users/Bianca/Desktop/Imagini/female.png").getImage().getScaledInstance(164,340,Image.SCALE_DEFAULT));
        female.setIcon(icon);

        ImageIcon icon1 = new ImageIcon(new ImageIcon("C:/Users/Bianca/Desktop/Imagini/male.png").getImage().getScaledInstance(164,327,Image.SCALE_DEFAULT));
        male.setIcon(icon1);


        ButtonGroup G = new ButtonGroup();

        gender.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));

        jButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        jButton.setBackground(Color.decode("#34b4eb"));

        jButton.setForeground(Color.WHITE);

        female.setBounds(200,60,164,340);
        male.setBounds(420,60,164,327);
        jButton.setBounds(350, 410, 110, 40);
        gender.setBounds(280,10,200,40);
        jButton.setBorderPainted(false);

        infoPanel.add(gender);
        infoPanel.add(female);
        infoPanel.add(male);
        infoPanel.add(jButton);


        G.add(female);
        G.add(male);


        female.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {


                if(maleSelected == 1)
                        male.setBorderPainted(false);
                femaleSelected = 1;
                female.setBorderPainted(true);
                female.setBorder(BorderFactory.createLineBorder(Color.decode("#d149de"), 1));

            }
        });

       male.addActionListener(new ActionListener() {
            int count = 0;
            @Override
            public void actionPerformed(ActionEvent e) {

                if(femaleSelected == 1)
                    female.setBorderPainted(false);
                maleSelected = 1;
                male.setBorderPainted(true);
                male.setBorder(BorderFactory.createLineBorder(Color.decode("#3493eb"), 1));

            }
        });

        jButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {

                if (female.isSelected()) {
                    UsersDB.updateUser(services.getCurrentUser().getFname(),services.getCurrentUser().getLname(), "GENDER","Female");
                }

                else if (male.isSelected()) {
                    UsersDB.updateUser(services.getCurrentUser().getFname(),services.getCurrentUser().getLname(), "GENDER","Male");
                }
                else{
                    showMessageDialog(null,"None of the options are selected");
                }
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
            MainScreen mainScreen = new MainScreen();
            mainScreen.setVisible(true);
        });
        topPanel.add(backToMainScreen);

        JLabel labelTitle = new JLabel("Let's Create Your Body Profile", JLabel.CENTER);
        JLabel labelQuestion = new JLabel("Select your gender.", JLabel.CENTER);
        labelTitle.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
        labelTitle.setBorder(new EmptyBorder(20, 70, 0, 0));
        topPanel.add(labelTitle);
        return topPanel;
    }

}
