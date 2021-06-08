package fitness.app.services;

import fitness.app.User;
import fitness.app.UsersDB;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


final public class Service {

    private static Service servicesInstance = null;
    private UsersDB udb = UsersDB.getDatabaseInstance();

    private List<User> usersList = UsersDB.Read();


    private User currentUser = null;

    public static Service getServicesInstance(){
        if (servicesInstance == null)
            servicesInstance = new Service();
        return servicesInstance;
    }

    public int logInUser(String username, String password){
        int check = 0;
        boolean existCustomer = searchUser(username);
        boolean correctPassword = checkCustomerPassword(username,password);
        if (existCustomer && correctPassword) {
            currentUser = getUser(username);
        } else {
            if (!existCustomer)
                check = 1;
            else
                check = 2; // Valid username, but incorrect password
        }
        return check;
    }

    private boolean searchUser(String username) {
        return getUser(username) != null;
    }

    private User getUser(String username){
        User searchedUser = null;
        for(User customer: usersList)
            if (customer.getUsername().equals(username))
                searchedUser = customer;
        return searchedUser;
    }

    private boolean checkCustomerPassword(String username,String password){
        for(User user: usersList)
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
                return true;
        return false;
    }

    public boolean registerUser(String [] user){
        boolean check = true;
        if(getUser(user[3])==null) {
            String[] userData = {user[0], user[1], user[2], user[3], user[4]};
            try {
                udb.addUser(user[0], user[1], user[2], user[3], user[4]);
                usersList = UsersDB.Read();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            check = false;
        return check;
    }

}
