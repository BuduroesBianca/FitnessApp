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

public class BodyTypeScreenMale extends JFrame{
    private Service services = Service.getServicesInstance();
    UsersDB udb = UsersDB.getDatabaseInstance();
    private int rectnagleSelected = 0;
    private int pearSelected = 0;
    private int roundSelected = 0;


    public BodyTypeScreenMale() {
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Body type");
        setLayout(new BorderLayout());
        add(topPanel(), BorderLayout.NORTH);
        add(infoPanel(), BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    JPanel infoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);

        JRadioButton pear = new JRadioButton();
        JRadioButton rectangle = new JRadioButton();
        JRadioButton round = new JRadioButton();
        JButton jButton = new JButton("Submit");
        JLabel bodyType = new JLabel("Select your body type", JLabel.CENTER);


        ImageIcon icon = new ImageIcon(new ImageIcon("Imagini/pearm.png").getImage().getScaledInstance(90, 251, Image.SCALE_DEFAULT));
        pear.setIcon(icon);

        ImageIcon icon1 = new ImageIcon(new ImageIcon("Imagini/rectanglem.png").getImage().getScaledInstance(103, 257, Image.SCALE_DEFAULT));
        rectangle.setIcon(icon1);

        ImageIcon icon2 = new ImageIcon(new ImageIcon("Imagini/roundm.png").getImage().getScaledInstance(90, 256, Image.SCALE_DEFAULT));
        round.setIcon(icon2);


        ButtonGroup BT = new ButtonGroup();

        bodyType.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));

        jButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        jButton.setBackground(Color.decode("#34b4eb"));

        jButton.setForeground(Color.WHITE);

        pear.setBounds(150, 80, 90, 251);
        rectangle.setBounds(350, 80, 102, 251);
        round.setBounds(550, 80, 90, 251);

        jButton.setBounds(350, 400, 110, 40);
        bodyType.setBounds(240,10,300,40);
        jButton.setBorderPainted(false);

        infoPanel.add(bodyType);
        infoPanel.add(pear);
        infoPanel.add(rectangle);
        infoPanel.add(round);
        infoPanel.add(jButton);


        BT.add(pear);
        BT.add(rectangle);
        BT.add(round);


        pear.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {


                if (rectnagleSelected == 1)
                    rectangle.setBorderPainted(false);
                if (roundSelected==1)
                    round.setBorderPainted(false);

                pearSelected = 1;
                pear.setBorderPainted(true);
                pear.setBorder(BorderFactory.createLineBorder(Color.decode("#d149de"), 1));

            }
        });

        rectangle.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (pearSelected == 1)
                    pear.setBorderPainted(false);
                if (roundSelected==1)
                    round.setBorderPainted(false);

                rectnagleSelected= 1;
                rectangle.setBorderPainted(true);
                rectangle.setBorder(BorderFactory.createLineBorder(Color.decode("#d149de"), 1));

            }
        });

        round.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {


                if (pearSelected == 1)
                    pear.setBorderPainted(false);
                if (rectnagleSelected==1)
                    rectangle.setBorderPainted(false);


                roundSelected = 1;
                round.setBorderPainted(true);
                round.setBorder(BorderFactory.createLineBorder(Color.decode("#d149de"), 1));

            }
        });


        jButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (pear.isSelected()) {
                    UsersDB.updateUser(services.getCurrentUser().getFname(), services.getCurrentUser().getLname(), "BODYTYPE", "Pear");
                    GoalScreen gs = new GoalScreen();
                    gs.setVisible(true);
                    dispose();
                } else if (round.isSelected()) {
                    UsersDB.updateUser(services.getCurrentUser().getFname(), services.getCurrentUser().getLname(), "BODYTYPE", "Round");
                    GoalScreen gs = new GoalScreen();
                    gs.setVisible(true);
                    dispose();
                } else if (rectangle.isSelected()) {
                    UsersDB.updateUser(services.getCurrentUser().getFname(), services.getCurrentUser().getLname(), "BODYTYPE", "Rectangle");
                    GoalScreen gs = new GoalScreen();
                    gs.setVisible(true);
                    dispose();
                } else {
                    showMessageDialog(null, "None of the options are selected");
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
            GenderScreen mainScreen = new GenderScreen();
            mainScreen.setVisible(true);
        });
        topPanel.add(backToMainScreen);

        JLabel labelTitle = new JLabel("Let's Create Your Body Profile", JLabel.CENTER);
        JLabel labelQuestion = new JLabel("Select your Bodt type.", JLabel.CENTER);
        labelTitle.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
        labelTitle.setBorder(new EmptyBorder(20, 70, 0, 0));
        topPanel.add(labelTitle);
        return topPanel;
    }
}