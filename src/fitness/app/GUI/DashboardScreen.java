package fitness.app.GUI;


import fitness.app.services.Service;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class DashboardScreen extends  JFrame{

    private Service services = Service.getServicesInstance();

    DashboardScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(800, 600);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("FitnessApp");
        setLayout(new BorderLayout());
        add(infoPanel(), BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    JPanel infoPanel(){

        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(true);
        infoPanel.setBackground(Color.decode("#1f7e8f"));
        infoPanel.setLayout(null);

        JPanel center = new JPanel();
        center.setLayout(null);
        center.setOpaque(true);
        center.setBackground(Color.WHITE);


        //---------------- RECIPE AND WORKOUT-------------------------------------------
        JPanel recipePanel = new JPanel();
        recipePanel.setOpaque(true);
        recipePanel.setBackground(Color.decode("#1f7e8f"));
        recipePanel.setLayout(null);
        recipePanel.setVisible(false);

        JLabel recipe = new JLabel();

        JButton back = new JButton("Back");
        back.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,15));
        back.setBackground(Color.decode("#00529e"));
        back.setForeground(Color.WHITE);

        ImageIcon img = new ImageIcon(new ImageIcon("imagini/recipe.jpeg").getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
        recipe.setIcon(img);

        recipe.setBounds(0,0,800,600);
        back.setBounds(600,20,120,40);
        recipePanel.add(back);
        recipePanel.add(recipe);

        back.addActionListener(e -> {
            recipePanel.setVisible(false);
            center.setVisible(true);
        });

        JLabel foodTitle = new JLabel("Meal Plan",JLabel.CENTER);
        foodTitle.setFont(new Font("Microsoft YaHei UI", Font.BOLD,25));

        JButton rec = new JButton("Click for recipe!");
        rec.setFont(new Font("Microsoft YaHei UI", Font.BOLD,15));

        JButton b = new JButton("Workout");
        b.setFont(new Font("Microsoft YaHei UI", Font.BOLD,15));


         b.addActionListener(e ->{
            try {

            // create a URI
            URI u = new URI("www.geeksforgeeks.org");

            Desktop d = Desktop.getDesktop();
            d.browse(u);
            }
            catch (Exception evt) {
            }
        });

         //-----------------------------------------------------------------------------


        center.setBounds(160,0,470,600);
        rec.setBounds(50,100,200,50);
        foodTitle.setBounds(30,20,400,50);
        recipePanel.setBounds(0,0,800,600);
        b.setBounds(50,200,200,50);


        infoPanel.add(center);
        infoPanel.add(recipePanel);
        center.add(foodTitle);
        center.add(rec);
        center.add(b);


        rec.addActionListener(e -> {
            recipePanel.setVisible(true);
            center.setVisible(false);
        });

        return infoPanel;
    }

    JPanel day1() {

    }

}
