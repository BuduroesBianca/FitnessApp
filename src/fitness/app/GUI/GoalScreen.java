package fitness.app.GUI;

import fitness.app.UsersDB;
import fitness.app.services.Service;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class GoalScreen extends JFrame {

    private Service services = Service.getServicesInstance();
    UsersDB udb = UsersDB.getDatabaseInstance();
    private int loseSelected = 0;
    private int gainSelected = 0;
    private int maintainSelected = 0;

    public GoalScreen() {
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Goal");
        setLayout(new BorderLayout());
        add(topPanel(), BorderLayout.NORTH);
        add(infoPanel(), BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    JPanel infoPanel(){
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);

        JRadioButton lose = new JRadioButton("               Lose Weight");
        JRadioButton gain = new JRadioButton("               Gain Muscle");
        JRadioButton maintain = new JRadioButton("     Develop Healthy Habits");

        ImageIcon icon1 = new ImageIcon(new ImageIcon("Imagini/male.png").getImage().getScaledInstance(1,1,Image.SCALE_DEFAULT));
        lose.setIcon(icon1);
        gain.setIcon(icon1);
        maintain.setIcon(icon1);

        JButton jButton = new JButton("Submit");
        JLabel goal = new JLabel("What Is Your Primary Goal?",JLabel.CENTER);

        lose.setBackground(Color.decode("#34b4eb"));
        lose.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        lose.setForeground(Color.white);

        gain.setBackground(Color.decode("#34b4eb"));
        gain.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        gain.setForeground(Color.white);

        maintain.setBackground(Color.decode("#34b4eb"));
        maintain.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        maintain.setForeground(Color.white);

        ButtonGroup G = new ButtonGroup();

        goal.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));

        jButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        jButton.setBackground(Color.decode("#34b4eb"));

        jButton.setForeground(Color.WHITE);

        lose.setBounds(260,100,250,50);
        gain.setBounds(260,180,250,50);
        maintain.setBounds(260,260,250,50);
        jButton.setBounds(330, 380, 110, 40);
        goal.setBounds(210,15,350,40);
        jButton.setBorderPainted(false);

        infoPanel.add(goal);
        infoPanel.add(lose);
        infoPanel.add(gain);
        infoPanel.add(maintain);
        infoPanel.add(jButton);


        G.add(lose);
        G.add(gain);
        G.add(maintain);


        lose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(gainSelected == 1 || maintainSelected == 1) {
                    gain.setBorderPainted(false);
                    maintain.setBorderPainted(false);
                }
                loseSelected = 1;
                lose.setBorderPainted(true);
                lose.setBorder(BorderFactory.createLineBorder(Color.decode("#3493eb"), 3));

            }
        });

        gain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(loseSelected == 1 || maintainSelected == 1) {
                    lose.setBorderPainted(false);
                    maintain.setBorderPainted(false);
                }
                gainSelected = 1;
                gain.setBorderPainted(true);
                gain.setBorder(BorderFactory.createLineBorder(Color.decode("#3493eb"), 3));

            }
        });

        maintain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(loseSelected == 1 || gainSelected == 1) {
                    lose.setBorderPainted(false);
                    gain.setBorderPainted(false);
                }
                maintainSelected = 1;
                maintain.setBorderPainted(true);
                maintain.setBorder(BorderFactory.createLineBorder(Color.decode("#3493eb"), 3));

            }
        });

        jButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {

                if (lose.isSelected()) {
                    UsersDB.updateUser(services.getCurrentUser().getFname(),services.getCurrentUser().getLname(), "GOAL","Lose weight");
                    TypicalDayScreen bt = new TypicalDayScreen();
                    bt.setVisible(true);
                    dispose();
                }

                else if (gain.isSelected()) {
                    UsersDB.updateUser(services.getCurrentUser().getFname(),services.getCurrentUser().getLname(), "GOAL","Gain muscle");
                    TypicalDayScreen bt = new TypicalDayScreen();
                    bt.setVisible(true);
                    dispose();
                }
                else if (maintain.isSelected()) {
                    UsersDB.updateUser(services.getCurrentUser().getFname(),services.getCurrentUser().getLname(), "GOAL","Maintain weight");
                    TypicalDayScreen bt = new TypicalDayScreen();
                    bt.setVisible(true);
                    dispose();
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
        JButton backToMainScreen = new JButton("Back To Body Type");
        backToMainScreen.setOpaque(true);
        backToMainScreen.setBackground(Color.black);
        backToMainScreen.setForeground(Color.white);
        backToMainScreen.setBorderPainted(true);
        backToMainScreen.setBorder(BorderFactory.createLineBorder(Color.black, 8));
        backToMainScreen.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
        backToMainScreen.addActionListener(e -> {
            dispose();
            BodyTypeScreenFemale gs = new BodyTypeScreenFemale();
            gs.setVisible(true);
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

