package fitness.app.GUI;
import fitness.app.users.UsersDB;
import fitness.app.services.Service;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JOptionPane.showMessageDialog;

public class TypicalDayScreen extends JFrame{
    private Service services = Service.getServicesInstance();
    UsersDB udb = UsersDB.getDatabaseInstance();
    private int officeSelected = 0;
    private int homeSelected = 0;
    private int physicalSelected = 0;
    private int walksSelected = 0;

    public TypicalDayScreen() {
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Typical Day");
        setLayout(new BorderLayout());
        add(topPanel(), BorderLayout.NORTH);
        add(infoPanel(), BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    JPanel infoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);

        JRadioButton office = new JRadioButton();
        JRadioButton walks = new JRadioButton();
        JRadioButton home = new JRadioButton();
        JRadioButton physical = new JRadioButton();
        JButton jButton = new JButton("Submit");
        JLabel typicalDay = new JLabel("Select your typical day", JLabel.CENTER);


        ImageIcon icon = new ImageIcon(new ImageIcon("Imagini/office.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        office.setIcon(icon);

        ImageIcon icon1 = new ImageIcon(new ImageIcon("Imagini/healthy.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        walks.setIcon(icon1);

        ImageIcon icon2 = new ImageIcon(new ImageIcon("Imagini/home.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        home.setIcon(icon2);

        ImageIcon icon3 = new ImageIcon(new ImageIcon("Imagini/physical.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        physical.setIcon(icon3);


        ButtonGroup TD = new ButtonGroup();

        typicalDay.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));

        jButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        jButton.setBackground(Color.decode("#34b4eb"));

        jButton.setForeground(Color.WHITE);

        office.setBounds(220, 60, 150, 150);
        walks.setBounds(380, 60, 150, 150);
        home.setBounds(220, 220, 150, 150);
        physical.setBounds(380, 220, 150, 150);

        jButton.setBounds(310, 400, 110, 40);
        typicalDay.setBounds(220,10,300,40);
        jButton.setBorderPainted(false);

        infoPanel.add(typicalDay);
        infoPanel.add(office);
        infoPanel.add(walks);
        infoPanel.add(home);
        infoPanel.add(physical);
        infoPanel.add(jButton);


        TD.add(office);
        TD.add(walks);
        TD.add(home);
        TD.add(physical);


        office.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {


                if (walksSelected == 1)
                    walks.setBorderPainted(false);
                if (homeSelected==1)
                    home.setBorderPainted(false);
                if(physicalSelected==1)
                    physical.setBorderPainted(false);

                officeSelected = 1;
                office.setBorderPainted(true);
                office.setBorder(BorderFactory.createLineBorder(Color.decode("#d149de"), 1));

            }
        });

        home.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {


                if (walksSelected == 1)
                    walks.setBorderPainted(false);
                if (officeSelected==1)
                    office.setBorderPainted(false);
                if(physicalSelected==1)
                    physical.setBorderPainted(false);

                homeSelected = 1;
                home.setBorderPainted(true);
                home.setBorder(BorderFactory.createLineBorder(Color.decode("#d149de"), 1));

            }
        });

        walks.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {


                if (officeSelected == 1)
                    office.setBorderPainted(false);
                if (homeSelected==1)
                    home.setBorderPainted(false);
                if(physicalSelected==1)
                    physical.setBorderPainted(false);

                walksSelected = 1;
                walks.setBorderPainted(true);
                walks.setBorder(BorderFactory.createLineBorder(Color.decode("#d149de"), 1));

            }
        });

        physical.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {


                if (walksSelected == 1)
                    walks.setBorderPainted(false);
                if (homeSelected==1)
                    home.setBorderPainted(false);
                if(officeSelected==1)
                    office.setBorderPainted(false);

                physicalSelected = 1;
                physical.setBorderPainted(true);
                physical.setBorder(BorderFactory.createLineBorder(Color.decode("#d149de"), 1));

            }
        });
        jButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (office.isSelected()) {
                    UsersDB.updateUser(services.getCurrentUser().getFname(), services.getCurrentUser().getLname(), "TYPICALDAY", "At the office");
                    VegetarianScreen vs = new VegetarianScreen();
                    vs.setVisible(true);
                    dispose();
                } else if (walks.isSelected()) {
                    UsersDB.updateUser(services.getCurrentUser().getFname(), services.getCurrentUser().getLname(), "TYPICALDAY", "Daily long walks");
                    VegetarianScreen vs = new VegetarianScreen();
                    vs.setVisible(true);
                    dispose();
                } else if (home.isSelected()) {
                    UsersDB.updateUser(services.getCurrentUser().getFname(), services.getCurrentUser().getLname(), "TYPICALDAY", "Mostly at home");
                    VegetarianScreen vs = new VegetarianScreen();
                    vs.setVisible(true);
                    dispose();
                } else if (walks.isSelected()) {
                    UsersDB.updateUser(services.getCurrentUser().getFname(), services.getCurrentUser().getLname(), "TYPICALDAY", "Physical work");
                    VegetarianScreen vs = new VegetarianScreen();
                    vs.setVisible(true);
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
        JButton backToMainScreen = new JButton("Back To Goal");
        backToMainScreen.setOpaque(true);
        backToMainScreen.setBackground(Color.black);
        backToMainScreen.setForeground(Color.white);
        backToMainScreen.setBorderPainted(true);
        backToMainScreen.setBorder(BorderFactory.createLineBorder(Color.black, 8));
        backToMainScreen.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
        backToMainScreen.addActionListener(e -> {
            dispose();
            GoalScreen bt = new GoalScreen();
            bt.setVisible(true);
        });
        topPanel.add(backToMainScreen);

        JLabel labelTitle = new JLabel("Let's Create Your Body Profile", JLabel.CENTER);
        JLabel labelQuestion = new JLabel("Select your typical day.", JLabel.CENTER);
        labelTitle.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
        labelTitle.setBorder(new EmptyBorder(20, 70, 0, 0));
        topPanel.add(labelTitle);
        return topPanel;
    }
}