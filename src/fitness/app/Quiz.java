package fitness.app;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Quiz {

    Map<String, ArrayList<String>> recipes;
    Map<String, ArrayList<String>> wWorkouts;
    Map<String, ArrayList<String>> mWorkouts;


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        UsersDB udb = UsersDB.getDatabaseInstance();

        // DE FACUT CU LOGAREAAA
        System.out.println("FIRSTNAME");
        String fName = scan.next();
        System.out.println("LASTNAME");
        String lname  = scan.next();

        System.out.println("Select your gender");
        System.out.println("1. Female");
        System.out.println("2. Male");

        switch (scan.nextInt()){
            case 1: {
                UsersDB.updateUser(fName,lname,"GENDER","Female");
                break;
            }
            case 2:{
                UsersDB.updateUser(fName,lname,"GENDER","Male");
            }
        }
        udb.showUser();
    }
}
