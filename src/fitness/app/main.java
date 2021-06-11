package fitness.app;

import java.awt.*;
import java.text.ParseException;


public class main {

    public static void main(String[] args) throws ParseException{

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

    }
}
