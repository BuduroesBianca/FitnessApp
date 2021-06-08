package fitness.app;
import java.sql.SQLException;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class main {
    public static void main(String[] args) throws ParseException{

        UsersDB udb = UsersDB.getDatabaseInstance();
        //udb.dropTable();
        //udb.addUser("Bianca","Buduroes","bianca@gmail.com","biancaabbe","Bianca02");
        udb.showUser();
        //new fitness.app.GUI.LoginScreen();
    }
}
