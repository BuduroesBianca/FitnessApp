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
        //-------

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
                break;
            }
        }

        System.out.println("Select your goal:");
        System.out.println("1. Lose weight");
        System.out.println("2. Gain weight");
        System.out.println("3. Maintain");

        switch (scan.nextInt()){
            case 1: {
                UsersDB.updateUser(fName,lname,"GOAL","Lose Weight");
                break;
            }
            case 2:{
                UsersDB.updateUser(fName,lname,"GOAL","Gain Weight");
                break;
            }
            case 3:{
                UsersDB.updateUser(fName,lname,"GOAL","Maintain");
                break;
            }
        }


        System.out.println("Select your body type");
        System.out.println("1. Rectangle");
        System.out.println("2. Pear");
        System.out.println("3. Round");
        System.out.println("4. Hourglass");
        switch (scan.nextInt()){
            case 1: {
                UsersDB.updateUser(fName,lname,"BODYTYPE","Rectangle");
                break;
            }
            case 2: {
                UsersDB.updateUser(fName,lname,"BODYTYPE","Pear");
                break;
            }
            case 3: {
                UsersDB.updateUser(fName,lname,"BODYTYPE","Round");
                break;
            }
            case 4: {
                UsersDB.updateUser(fName,lname,"BODYTYPE","Hourglass");
                break;
            }
        }


        System.out.println("Select your typical day:");
        System.out.println("1. At the office");
        System.out.println("2. Daily long walks");
        System.out.println("3. Physical work");
        System.out.println("4. Mostly at home");


        switch (scan.nextInt()){
            case 1: {
                UsersDB.updateUser(fName,lname,"TYPICALDAY","At the office");
                break;
            }
            case 2:{
                UsersDB.updateUser(fName,lname,"TYPICALDAY","Daily long walks");
                break;
            }
            case 3:{
                UsersDB.updateUser(fName,lname,"TYPICALDAY","Physical work");
                break;
            }
            case 4:{
                UsersDB.updateUser(fName,lname,"TYPICALDAY","Mostly at home");
                break;
            }
        }

        System.out.println("Fill the form: ");
        System.out.println("Your age:");
        String age = scan.next();
        System.out.println("Your height: ");
        String height = scan.next();
        System.out.println("Your weight: ");
        String weight = scan.next();
        System.out.println("Your target weight: ");
        String targetWeight = scan.next();

        UsersDB.updateUser(fName,lname,"AGE",age);
        UsersDB.updateUser(fName,lname,"HEIGHT",height);
        UsersDB.updateUser(fName,lname,"WEIGHT",weight);
        UsersDB.updateUser(fName,lname,"TARGETWEIGHT",targetWeight);



        udb.showUser();
    }
}
