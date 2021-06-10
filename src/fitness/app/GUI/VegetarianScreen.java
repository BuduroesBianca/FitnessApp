package fitness.app.GUI;

import fitness.app.UsersDB;
import fitness.app.services.Service;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class VegetarianScreen extends JFrame {

    private Service services = Service.getServicesInstance();
    UsersDB udb = UsersDB.getDatabaseInstance();
    private int noSelected = 0;
    private int yesSelected = 0;
    private int maintainSelected = 0;

    public VegetarianScreen() {
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Vegetarian");
        setLayout(new BorderLayout());
        add(topPanel(), BorderLayout.NORTH);
        add(infoPanel(), BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    JPanel infoPanel(){
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);

        JRadioButton no = new JRadioButton("               YES");
        JRadioButton yes = new JRadioButton("               NO");

        ImageIcon icon1 = new ImageIcon(new ImageIcon("Imagini/male.png").getImage().getScaledInstance(1,1,Image.SCALE_DEFAULT));
        no.setIcon(icon1);
        yes.setIcon(icon1);

        JButton jButton = new JButton("Submit");
        JLabel goal = new JLabel("Are You a Vegetarian?",JLabel.CENTER);

        no.setBackground(Color.decode("#32a864"));
        no.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        no.setForeground(Color.white);

        yes.setBackground(Color.decode("#32a864"));
        yes.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
        yes.setForeground(Color.white);

        ButtonGroup G = new ButtonGroup();

        goal.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));

        jButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        jButton.setBackground(Color.decode("#34b4eb"));
        jButton.setForeground(Color.WHITE);

        no.setBounds(285,130,200,60);
        yes.setBounds(285,230,200,60);
        jButton.setBounds(330, 380, 110, 40);
        goal.setBounds(210,20,350,40);
        jButton.setBorderPainted(false);

        infoPanel.add(goal);
        infoPanel.add(yes);
        infoPanel.add(no);
        infoPanel.add(jButton);


        G.add(no);
        G.add(yes);


        no.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(yesSelected == 1) {
                    yes.setBorderPainted(false);
                }
                noSelected = 1;
                no.setBorderPainted(true);
                no.setBorder(BorderFactory.createLineBorder(Color.decode("#3493eb"), 3));

            }
        });

        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(noSelected == 1) {
                    no.setBorderPainted(false);
                }
                yesSelected = 1;
                yes.setBorderPainted(true);
                yes.setBorder(BorderFactory.createLineBorder(Color.decode("#3493eb"), 3));

            }
        });

        jButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {

                if (yes.isSelected()) {
                    UsersDB.updateUser(services.getCurrentUser().getFname(),services.getCurrentUser().getLname(), "VEGETARIAN","yes");
                    InfoScreen is = new InfoScreen();
                    is.setVisible(true);
                    dispose();
                }

                else if (no.isSelected()) {
                    UsersDB.updateUser(services.getCurrentUser().getFname(),services.getCurrentUser().getLname(), "VEGETARIAN","no");
                    InfoScreen is = new InfoScreen();
                    is.setVisible(true);
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
        JButton backToMainScreen = new JButton("<< Back to Main Page");
        backToMainScreen.addActionListener(e -> {
            dispose();
            TypicalDayScreen td = new TypicalDayScreen();
            td.setVisible(true);
        });
        topPanel.add(backToMainScreen);

        JLabel labelTitle = new JLabel("Let's Create Your Body Profile", JLabel.CENTER);

        labelTitle.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
        labelTitle.setBorder(new EmptyBorder(20, 70, 0, 0));
        topPanel.add(labelTitle);
        return topPanel;
    }

}

