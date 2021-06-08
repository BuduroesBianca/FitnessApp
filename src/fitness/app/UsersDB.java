package fitness.app;

import javax.xml.crypto.Data;
import java.util.*;
import java.sql.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDB {

    private static Connection connection;
    private static UsersDB instance;

    private UsersDB() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:derby:C:/Users/Bianca/source/FitnessApp/FitnessAppDB;create=true");
        boolean notFoundUsers = true;

        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        while(results.next()){
            if("users".equalsIgnoreCase(results.getString("TABLE_NAME"))){
                notFoundUsers = false;
                break;
            }
        }

        if(notFoundUsers){
            connection.createStatement().execute("CREATE TABLE users (ID int not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),FIRSTNAME varchar(50),LASTNAME varchar(50),AGE int,GENDER varchar(50),HEIGHT int,WEIGHT int,BODYTYPE varchar(50),GOAL varchar(50), PASSWORD varchar(50))");
        }

    }

    // DESIGN PATTERN - SINGLETON
    public static UsersDB getDatabaseInstance() {
        if (instance == null)
            try {
                instance = new UsersDB();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        return instance;
    }

    public void dropTable(){

        try {
            PreparedStatement stmt = connection.prepareStatement("DROP TABLE users");
            stmt.executeUpdate();
            System.out.println("table dropped");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void addUser(String firstName,String lastName, String pass ) {
        User user = new User(firstName,lastName,pass);

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (FIRSTNAME,LASTNAME,PASSWORD) VALUES (?,?,?)");

            statement.setString(1, user.getFname());
            statement.setString(2, user.getLname());
            statement.setString(3, user.getPassword());


            statement.executeUpdate();
            System.out.println("User has been added.");
        }catch (SQLException e){
            System.out.println("Error. The user hasn't been added.");
            e.printStackTrace();
        }
    }

    public void showUser(){
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM users");
            while (resultSet.next())
            {
                System.out.println("Id:" + resultSet.getInt(1));
                System.out.println("First Name:" + resultSet.getString(2));
                System.out.println("Last Name:" + resultSet.getString(3));
                System.out.println("Age:" + resultSet.getInt(4));
                System.out.println("Gender:" + resultSet.getString(5));
                System.out.println("Height:" + resultSet.getInt(6));
                System.out.println("Weight:" + resultSet.getInt(7));
                System.out.println("Body Type:" + resultSet.getString(8));
                System.out.println("Goal:" + resultSet.getString(9));
                System.out.println("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(String firstName,String lastName,String fieldToUpdate, String updatedValue){
        PreparedStatement statement;
        switch (fieldToUpdate){
            case "AGE" : {
                try {
                    statement = connection.prepareStatement("UPDATE users SET AGE = ? WHERE FIRSTNAME = ? and LASTNAME = ?");
                    statement.setInt(1,  Integer.parseInt(updatedValue));
                    statement.setString(2, firstName);
                    statement.setString(3,lastName);
                    statement.executeUpdate();
                    System.out.println("Campul Varsta a fost modificat cu succes.");
                    break;
                } catch (SQLException e) {
                    System.out.println("Campul Varsta nu a fost modificat. Eroare.");
                    e.printStackTrace();
                }
            }
            case "GENDER" : {
                try {
                    statement = connection.prepareStatement("UPDATE users SET GENDER = ? WHERE  FIRSTNAME = ? and LASTNAME = ?");
                    statement.setString(1, updatedValue);
                    statement.setString(2, firstName);
                    statement.setString(3,lastName);
                    statement.executeUpdate();
                    System.out.println("Campul gender a fost modificat cu succes.");
                    break;
                } catch (SQLException e) {
                    System.out.println("Campul gender nu a fost modificat. Eroare.");
                    e.printStackTrace();
                }
            }
            case "HEIGHT" : {
                try {
                    statement = connection.prepareStatement("UPDATE users SET HEIGHT = ? WHERE FIRSTNAME = ? and LASTNAME = ?");
                    statement.setString(1, updatedValue);
                    statement.setString(2, firstName);
                    statement.setString(3,lastName);
                    statement.executeUpdate();
                    System.out.println("Campul height a fost modificat cu succes.");
                    break;
                } catch (SQLException e) {
                    System.out.println("Campul height nu a fost modificat. Eroare.");
                    e.printStackTrace();
                }
            }
            case "WEIGHT" : {
                try {
                    statement = connection.prepareStatement("UPDATE users SET WEIGHT = ? WHERE FIRSTNAME = ? and LASTNAME = ?");
                    statement.setString(1, updatedValue);
                    statement.setString(2, firstName);
                    statement.setString(3,lastName);
                    statement.executeUpdate();
                    System.out.println("Campul WIGHT a fost modificat cu succes.");
                    break;
                } catch (SQLException e) {
                    System.out.println("Campul WEIGHT nu a fost modificat. Eroare.");
                    e.printStackTrace();
                }
            }
            case "BODYTYPE" : {
                try {
                    statement = connection.prepareStatement("UPDATE users SET BODYTYPE = ? WHERE FIRSTNAME = ? and LASTNAME = ?");
                    statement.setBoolean(1, Boolean.parseBoolean(updatedValue));
                    statement.setString(2, firstName);
                    statement.setString(3,lastName);
                    statement.executeUpdate();
                    System.out.println("Campul BT a fost modificat cu succes.");
                    break;
                } catch (SQLException e) {
                    System.out.println("Campul BT nu a fost modificat. Eroare.");
                    e.printStackTrace();
                }
            }
            case "GOAL" : {
                try {
                    statement = connection.prepareStatement("UPDATE users SET GOAL = ? WHERE FIRSTNAME = ? and LASTNAME = ?");
                    statement.setBoolean(1, Boolean.parseBoolean(updatedValue));
                    statement.setString(2, firstName);
                    statement.setString(3,lastName);
                    statement.executeUpdate();
                    System.out.println("Campul GOAL a fost modificat cu succes.");
                    break;
                } catch (SQLException e) {
                    System.out.println("Campul GOAL nu a fost modificat. Eroare.");
                    e.printStackTrace();
                }
            }
            default:
                System.out.println("Nu exista aceasta coloana in tabela pacienti.");
        }
    }


    public static List<User> Read() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet results = connection.createStatement().executeQuery("SELECT ID,FIRSTNAME,LASTNAME,AGE,GENDER,HEIGHT,WEIGHT,BODY_TYPE,GOAL FROM users");
            while (results.next()) {
                users.add(new User(results.getInt(1),results.getString(2), results.getString(3), results.getInt(4), results.getString(5), results.getInt(6), results.getInt(7),results.getString(8),results.getString(9)));
            }
            return users;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }



    public void deleteUser(int id){
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            stmt.setInt(1,id);


            stmt.executeUpdate();
            System.out.println("User has been deleted.");

        }catch (SQLException e){
            System.out.println("Error. The user hasn't been deleted.");
            e.printStackTrace();
        }
    }

}
