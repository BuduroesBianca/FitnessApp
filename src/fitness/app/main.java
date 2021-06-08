package fitness.app;
import java.sql.SQLException;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class main {
    public static void main(String[] args) throws ParseException{

        UsersDB udb = UsersDB.getDatabaseInstance();
        //udb.dropTable();
        udb.addUser("Bianca","Buduroes","parola");
        udb.showUser();
    }
}
