package fitness.app.GUI;


import fitness.app.UsersDB;
import fitness.app.services.Service;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardScreen extends  JFrame{

    private Service services = Service.getServicesInstance();
    private UsersDB udb = UsersDB.getDatabaseInstance();
    List<String> lwm = new ArrayList<>();
    List<String> lwf = new ArrayList<>();
    List<String> gwm = new ArrayList<>();
    List<String> gwf = new ArrayList<>();
    List<String> lwi = new ArrayList<>();
    List<String> gwi = new ArrayList<>();

    private String gender;
    private String goal;
    private List<String> workoutPlan;
    private List<String> mealPlan;
    private URI urlYT;


    DashboardScreen()  {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(800, 600);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("FitnessApp");
        setLayout(new BorderLayout());
        setJMenuBar(mainMenuBar());
        add(infoPanel(), BorderLayout.CENTER);
        gender = udb.getGender(services.getCurrentUser().getUsername());
        goal = udb.getGoal(services.getCurrentUser().getUsername());

        lwm.addAll(Arrays.asList("https://www.youtube.com/watch?v=OJF1veAhTdE&ab_channel=Men%27sHealth",
                "https://www.youtube.com/watch?v=UcX9OcJDQy4&ab_channel=Men%27sHealth",
                "https://www.youtube.com/watch?v=nOQpD8aDJeI&ab_channel=THENX",
                "https://www.youtube.com/watch?v=xngUfoWLkFw&ab_channel=BullyJuice",
                "https://www.youtube.com/watch?v=AdSLMdQfmqw&ab_channel=Men%27sHealth"));

        lwf.addAll(Arrays.asList("https://www.youtube.com/watch?v=It-B8xhkZy4&ab_channel=Bodybuilding.com",
                "https://www.youtube.com/watch?v=Og12jfLMrJw&ab_channel=Bodybuilding.com",
                "https://www.youtube.com/watch?v=e9LD7HoJONg&ab_channel=Bodybuilding.com",
                "https://www.youtube.com/watch?v=d_VX1gtm078&ab_channel=Bodybuilding.com",
                "https://www.youtube.com/watch?v=460nSW7xEok&ab_channel=Bodybuilding.com"));

        gwm.addAll(Arrays.asList("https://www.youtube.com/watch?v=mcCg_ycMhlA",
                "https://www.youtube.com/watch?v=DVlIYIwRTLA&ab_channel=Bodybuilding.com",
                "https://www.youtube.com/watch?v=lA7dbOmxs5I&ab_channel=Bodybuilding.com",
                "https://www.youtube.com/watch?v=ubJxrA02MLY&ab_channel=Bodybuilding.comBodybuilding.comVerificat",
                "https://www.youtube.com/watch?v=JXYqd6ugxZw&ab_channel=Bodybuilding.comBodybuilding.comVerificat"));

        gwf.addAll(Arrays.asList("https://www.youtube.com/watch?v=Un7s7VwSdV0&ab_channel=Bodybuilding.com",
                "https://www.youtube.com/watch?v=AulGwjIv3m8&ab_channel=Bodybuilding.com",
                "https://www.youtube.com/watch?v=VQhs1R1VPtM&ab_channel=Bodybuilding.com",
                "https://www.youtube.com/watch?v=YHCZcgFzhPQ&ab_channel=Bodybuilding.com",
                "https://www.youtube.com/watch?v=3RcDkEOJV8c&ab_channel=Bodybuilding.com"));

        lwi.addAll(Arrays.asList("imagini/1l.jpeg","imagini/2l.jpeg","imagini/3l.jpeg","imagini/4l.jpeg","imagini/5l.jpeg",
                "imagini/6l.jpeg","imagini/7l.jpeg","imagini/8l.jpeg","imagini/9l.jpeg","imagini/10l.jpeg"));

        gwi.addAll(Arrays.asList("imagini/1g.jpeg","imagini/2g.jpeg","imagini/3g.jpeg","imagini/4g.jpeg","imagini/5g.jpeg",
                "imagini/6g.jpeg","imagini/7g.jpeg","imagini/8g.jpeg","imagini/9g.jpeg","imagini/10g.jpeg"));

        System.out.println(goal);

        if(gender.equalsIgnoreCase("Female")){
            if(goal.equalsIgnoreCase("Lose Weight")) {
                workoutPlan = lwf;
                mealPlan = lwi;
            }
            else if(goal.equalsIgnoreCase("Gain muscle")) {
                workoutPlan = gwf;
                mealPlan = gwi;
            }
            else if(goal.equalsIgnoreCase("Maintain weight")) {
                workoutPlan = lwf;
                workoutPlan.addAll(gwf);
                mealPlan = lwi;
                mealPlan.addAll(gwi);
            }
        }
        else if(gender.equalsIgnoreCase("Male")){
            if(goal.equalsIgnoreCase("Lose Weight")) {
                workoutPlan = lwm;
                mealPlan = lwi;
            }
            else if(goal.equalsIgnoreCase("Gain muscle")) {
                workoutPlan = gwm;
                mealPlan = gwi;
            }
            else if(goal.equalsIgnoreCase("Maintain weight")) {
                workoutPlan = lwm;
                workoutPlan.addAll(gwm);
                mealPlan = lwi;
                mealPlan.addAll(gwi);
            }
        }

        setLocationRelativeTo(null);
    }

    JMenuBar mainMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu logOut = new JMenu("LOG OUT");
        JMenu quiz = new JMenu("QUIZ");
        JMenu profile = new JMenu("MY PROFILE");
        JMenu dashboard = new JMenu("DASHBOARD");
        menuBar.add(quiz);
        menuBar.add(profile);
        menuBar.add(dashboard);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(logOut);

        logOut.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                int logOut = JOptionPane.showConfirmDialog(getRootPane(), "Are you sure you want to log out?", "LOG OUT",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (logOut == JOptionPane.YES_OPTION) {
                    services.logOut();
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
                }
            }
            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        profile.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                dispose();
                ProfileScreen accountScreen = new ProfileScreen();
                accountScreen.setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        quiz.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                dispose();
                GenderScreen gs = new GenderScreen();
                gs.setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        dashboard.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                dispose();
                DashboardScreen gs = null;
                gs = new DashboardScreen();
                gs.setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        return menuBar;
    }

    JPanel infoPanel(){

        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(true);
        infoPanel.setBackground(Color.decode("#f5ebd5"));
        infoPanel.setLayout(null);

        JPanel center = new JPanel();
        center.setLayout(null);
        center.setOpaque(true);
        center.setBackground(Color.WHITE);

        JLabel gif = new JLabel();
        ImageIcon img1 = new ImageIcon(new ImageIcon("imagini/dash.gif").getImage().getScaledInstance(300,170,Image.SCALE_DEFAULT));
        gif.setIcon(img1);

        JButton day1 = new JButton("DAY 1");
        day1.setBackground(Color.black);
        day1.setForeground(Color.WHITE);

        JButton day2 = new JButton("DAY 2");
        day2.setBackground(Color.black);
        day2.setForeground(Color.WHITE);

        JButton day3 = new JButton("DAY 3");
        day3.setBackground(Color.black);
        day3.setForeground(Color.WHITE);

        JButton day4 = new JButton("DAY 4");
        day4.setBackground(Color.black);
        day4.setForeground(Color.WHITE);

        JButton day5 = new JButton("DAY 5");
        day5.setBackground(Color.black);
        day5.setForeground(Color.WHITE);

        JButton day6 = new JButton("DAY 6");
        day6.setBackground(Color.black);
        day6.setForeground(Color.WHITE);

        JButton day7 = new JButton("DAY 7");
        day7.setBackground(Color.black);
        day7.setForeground(Color.WHITE);

        JButton day8 = new JButton("DAY 8");
        day8.setBackground(Color.black);
        day8.setForeground(Color.WHITE);

        JButton day9 = new JButton("DAY 9");
        day9.setBackground(Color.black);
        day9.setForeground(Color.WHITE);

        JButton day10 = new JButton("DAY 10");
        day10.setBackground(Color.black);
        day10.setForeground(Color.WHITE);




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





        JLabel foodTitle = new JLabel("Meal Plan",JLabel.CENTER);
        foodTitle.setFont(new Font("Microsoft YaHei UI", Font.BOLD,20));
        foodTitle.setOpaque(false);
        foodTitle.setBorder(BorderFactory.createLineBorder(Color.decode("#5fdae3"),2));


        foodTitle.setVisible(false);

        JButton rec = new JButton("Click for Meal Plan!");
        rec.setFont(new Font("Microsoft YaHei UI", Font.BOLD,15));
        rec.setOpaque(true);
        rec.setBackground(Color.black);
        rec.setForeground(Color.white);
        rec.setFocusPainted(false);
        rec.setVisible(false);


        JLabel workoutTitle = new JLabel("Your Workout",JLabel.CENTER);
        workoutTitle.setFont(new Font("Microsoft YaHei UI", Font.BOLD,20));
        workoutTitle.setBorder(BorderFactory.createLineBorder(Color.decode("#5fdae3"),1));
        workoutTitle.setVisible(false);


        JButton b = new JButton("Click for Workout!");
        b.setFont(new Font("Microsoft YaHei UI", Font.BOLD,15));
        b.setForeground(Color.WHITE);
        b.setBackground(Color.black);
        b.setFocusPainted(false);
        b.setVisible(false);

        gif.setVisible(false);

        JLabel begin = new JLabel("Let's Start This Journey!");
        begin.setFont(new Font("Microsoft YaHei UI", Font.BOLD,25));

        JLabel begin1 = new JLabel("Pick a Day");
        begin1.setFont(new Font("Microsoft YaHei UI", Font.BOLD,20));

        JLabel day = new JLabel();
        day.setFont(new Font("Microsoft YaHei UI", Font.BOLD,20));

        day1.addActionListener(e -> {
            ImageIcon img = new ImageIcon(new ImageIcon(mealPlan.get(0)).getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
            recipe.setIcon(img);

            recipe.setBounds(0,0,800,600);
            back.setBounds(600,20,120,40);
            recipePanel.add(back);
            recipePanel.add(recipe);

            day.setText("DAY 1");
            day.setVisible(true);
            urlYT = URI.create(workoutPlan.get(0));
            begin.setVisible(false);
            begin1.setVisible(false);
            begin.setVisible(false);
            foodTitle.setVisible(true);
            workoutTitle.setVisible(true);
            rec.setVisible(true);
            b.setVisible(true);
            gif.setVisible(true);
        });

        day2.addActionListener(e -> {
            ImageIcon img = new ImageIcon(new ImageIcon(mealPlan.get(1)).getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
            recipe.setIcon(img);

            recipe.setBounds(0,0,800,600);
            back.setBounds(600,20,120,40);
            recipePanel.add(back);
            recipePanel.add(recipe);

            day.setText("DAY 2");
            day.setVisible(true);
            begin1.setVisible(false);
            urlYT = URI.create(workoutPlan.get(1));
            begin.setVisible(false);
            begin.setVisible(false);
            foodTitle.setVisible(true);
            workoutTitle.setVisible(true);
            rec.setVisible(true);
            b.setVisible(true);
            gif.setVisible(true);
        });

        day3.addActionListener(e -> {
            ImageIcon img = new ImageIcon(new ImageIcon(mealPlan.get(2)).getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
            recipe.setIcon(img);

            recipe.setBounds(0,0,800,600);
            back.setBounds(600,20,120,40);
            recipePanel.add(back);
            recipePanel.add(recipe);

            day.setText("DAY 3");
            day.setVisible(true);
            begin1.setVisible(false);
            urlYT = URI.create(workoutPlan.get(3));
            begin.setVisible(false);
            begin.setVisible(false);
            foodTitle.setVisible(true);
            workoutTitle.setVisible(true);
            rec.setVisible(true);
            b.setVisible(true);
            gif.setVisible(true);
        });

        day4.addActionListener(e -> {
            ImageIcon img = new ImageIcon(new ImageIcon(mealPlan.get(3)).getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
            recipe.setIcon(img);

            recipe.setBounds(0,0,800,600);
            back.setBounds(600,20,120,40);
            recipePanel.add(back);
            recipePanel.add(recipe);

            day.setText("DAY 4");
            day.setVisible(true);
            begin1.setVisible(false);
            urlYT = URI.create(workoutPlan.get(4));
            begin.setVisible(false);
            begin.setVisible(false);
            foodTitle.setVisible(true);
            workoutTitle.setVisible(true);
            rec.setVisible(true);
            b.setVisible(true);
            gif.setVisible(true);
        });

        day5.addActionListener(e -> {
            ImageIcon img = new ImageIcon(new ImageIcon(mealPlan.get(4)).getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
            recipe.setIcon(img);

            recipe.setBounds(0,0,800,600);
            back.setBounds(600,20,120,40);
            recipePanel.add(back);
            recipePanel.add(recipe);

            day.setText("DAY 5");
            day.setVisible(true);
            begin1.setVisible(false);
            urlYT = URI.create(workoutPlan.get(0));
            begin.setVisible(false);
            begin.setVisible(false);
            foodTitle.setVisible(true);
            workoutTitle.setVisible(true);
            rec.setVisible(true);
            b.setVisible(true);
            gif.setVisible(true);
        });

        day6.addActionListener(e -> {
            ImageIcon img = new ImageIcon(new ImageIcon(mealPlan.get(5)).getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
            recipe.setIcon(img);

            recipe.setBounds(0,0,800,600);
            back.setBounds(600,20,120,40);
            recipePanel.add(back);
            recipePanel.add(recipe);

            day.setText("DAY 6");
            day.setVisible(true);
            begin1.setVisible(false);
            urlYT = URI.create(workoutPlan.get(1));
            begin.setVisible(false);
            begin.setVisible(false);
            foodTitle.setVisible(true);
            workoutTitle.setVisible(true);
            rec.setVisible(true);
            b.setVisible(true);
            gif.setVisible(true);
        });

        day7.addActionListener(e -> {
            ImageIcon img = new ImageIcon(new ImageIcon(mealPlan.get(6)).getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
            recipe.setIcon(img);

            recipe.setBounds(0,0,800,600);
            back.setBounds(600,20,120,40);
            recipePanel.add(back);
            recipePanel.add(recipe);

            day.setText("DAY 7");
            day.setVisible(true);
            begin1.setVisible(false);
            urlYT = URI.create(workoutPlan.get(2));
            begin.setVisible(false);
            begin.setVisible(false);
            foodTitle.setVisible(true);
            workoutTitle.setVisible(true);
            rec.setVisible(true);
            b.setVisible(true);
            gif.setVisible(true);
        });

        day8.addActionListener(e -> {
            ImageIcon img = new ImageIcon(new ImageIcon(mealPlan.get(7)).getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
            recipe.setIcon(img);

            recipe.setBounds(0,0,800,600);
            back.setBounds(600,20,120,40);
            recipePanel.add(back);
            recipePanel.add(recipe);

            day.setText("DAY 8");
            day.setVisible(true);
            begin1.setVisible(false);
            urlYT = URI.create(workoutPlan.get(3));
            begin.setVisible(false);
            begin.setVisible(false);
            foodTitle.setVisible(true);
            workoutTitle.setVisible(true);
            rec.setVisible(true);
            b.setVisible(true);
            gif.setVisible(true);
        });

        day9.addActionListener(e -> {
            ImageIcon img = new ImageIcon(new ImageIcon(mealPlan.get(8)).getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
            recipe.setIcon(img);

            recipe.setBounds(0,0,800,600);
            back.setBounds(600,20,120,40);
            recipePanel.add(back);
            recipePanel.add(recipe);

            day.setText("DAY 9");
            day.setVisible(true);
            begin1.setVisible(false);
            urlYT = URI.create(workoutPlan.get(4));
            begin.setVisible(false);
            begin.setVisible(false);
            foodTitle.setVisible(true);
            workoutTitle.setVisible(true);
            rec.setVisible(true);
            b.setVisible(true);
            gif.setVisible(true);
        });

        day10.addActionListener(e -> {
            ImageIcon img = new ImageIcon(new ImageIcon(mealPlan.get(9)).getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
            recipe.setIcon(img);

            recipe.setBounds(0,0,800,600);
            back.setBounds(600,20,120,40);
            recipePanel.add(back);
            recipePanel.add(recipe);

            day.setText("DAY 10");
            day.setVisible(true);
            begin1.setVisible(false);
            urlYT = URI.create(workoutPlan.get(0));
            begin.setVisible(false);
            begin.setVisible(false);
            foodTitle.setVisible(true);
            workoutTitle.setVisible(true);
            rec.setVisible(true);
            b.setVisible(true);
            gif.setVisible(true);
        });


        back.addActionListener(e -> {
            recipePanel.setVisible(false);
            center.setVisible(true);
            day1.setVisible(true);
            day2.setVisible(true);
            day3.setVisible(true);
            day4.setVisible(true);
            day5.setVisible(true);
            day6.setVisible(true);
            day7.setVisible(true);
            day8.setVisible(true);
            day9.setVisible(true);
            day10.setVisible(true);

        });

        b.addActionListener(f ->{
            try {

                Desktop d = Desktop.getDesktop();
                d.browse(urlYT);
            }
            catch (Exception evt) {
            }
        });




        //-----------------------------------------------------------------------------


        day.setBounds(50,30,100,40);
        center.setBounds(160,0,470,600);
        begin.setBounds(80,200,300,50);
        begin1.setBounds(180,250,300,50);
        gif.setBounds(80,370,300,170);
        rec.setBounds(130,120,200,50);
        foodTitle.setBounds(-10,50,490,30);
        workoutTitle.setBounds(-10,220,490,30);
        recipePanel.setBounds(0,0,800,600);
        b.setBounds(130,300,200,50);
        day1.setBounds(30,150,100,40);
        day2.setBounds(30,210,100,40);
        day3.setBounds(30,270,100,40);
        day4.setBounds(30,330,100,40);
        day5.setBounds(30,390,100,40);
        day6.setBounds(660,150,100,40);
        day7.setBounds(660,210,100,40);
        day8.setBounds(660,270,100,40);
        day9.setBounds(660,330,100,40);
        day10.setBounds(660,390,100,40);



        infoPanel.add(center);
        infoPanel.add(day);
        infoPanel.add(day1);
        infoPanel.add(day2);
        infoPanel.add(day3);
        infoPanel.add(day4);
        infoPanel.add(day5);
        infoPanel.add(day6);
        infoPanel.add(day7);
        infoPanel.add(day8);
        infoPanel.add(day9);
        infoPanel.add(day10);
        infoPanel.add(recipePanel);
        center.add(foodTitle);
        center.add(rec);
        center.add(b);
        center.add(workoutTitle);
        center.add(gif);
        center.add(begin);
        center.add(begin1);

        rec.addActionListener(e -> {
            day.setVisible(false);
            recipePanel.setVisible(true);
            center.setVisible(false);
            day1.setVisible(false);
            day2.setVisible(false);
            day3.setVisible(false);
            day4.setVisible(false);
            day5.setVisible(false);
            day6.setVisible(false);
            day7.setVisible(false);
            day8.setVisible(false);
            day9.setVisible(false);
            day10.setVisible(false);
        });

        return infoPanel;
    }

}
