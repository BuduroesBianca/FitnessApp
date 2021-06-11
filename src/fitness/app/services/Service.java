package fitness.app.services;

import fitness.app.User;
import fitness.app.UsersDB;

import java.util.List;


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
        System.out.println(usersList);
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

    public void logOut(){
        System.out.println("Logged out");
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void deleteAccount() throws Exception {
        usersList.remove(usersList.indexOf(currentUser));
        udb.deleteUser(currentUser.getId());
        System.out.println("Account deleted");
        currentUser = null;

    }

    public int calculateLoseWeightCalories(){
        double maintainCalories = calculateMaintainCalories();
        double loseCalories = 0.75 * maintainCalories;

        int result = (int) loseCalories;
        return result;

    }

    public int calculateGainMuscleCalories(){
        double maintainCalories = calculateMaintainCalories();
        double gainCalories = 1.15 * maintainCalories;
        int result = (int) gainCalories;
        return result;

    }

    public int calculateMaintainCalories(){
        System.out.println(getCurrentUser().getFname());
        int weight = udb.getWeight(getCurrentUser().getUsername());
        String bodyType = udb.getBodyType(getCurrentUser().getUsername());
        String typicalDay = udb.getTypicalDay(getCurrentUser().getUsername());
        String gender = udb.getGender(getCurrentUser().getUsername());

        int g = 0;
        double bt = 0;
        double l = 0;

        if(gender.equalsIgnoreCase("Female")){ g = 20; }
        if(gender.equalsIgnoreCase("Male")) { g = 22; }

        if(bodyType.equalsIgnoreCase( "Pear")) { bt = 1.5; }
        else if (bodyType.equalsIgnoreCase("Round")) { bt = 1.3; }
        else if (bodyType.equalsIgnoreCase("Rectangle")) { bt = 1.7; }
        else if (bodyType.equalsIgnoreCase("Hourglass")) { bt = 1.5; }

        if(typicalDay.equalsIgnoreCase("Mostly at home")) { l = 0; }
        else if(typicalDay.equalsIgnoreCase("At the office")) { l = 0.2; }
        else if(typicalDay.equalsIgnoreCase( "Daily long walks")) { l = 0.4; }
        else if(typicalDay.equalsIgnoreCase("Physical work")) { l = 0.6; }

        double maintainCalories = weight * g * (bt + l);
        int result = (int) maintainCalories;
        return result;
    }

    public int calculateBMR(){
        System.out.println(getCurrentUser().getFname());
        int weight = udb.getWeight(getCurrentUser().getUsername());
        int height = udb.getHeight(getCurrentUser().getUsername());
        int age = udb.getAge(getCurrentUser().getUsername());
        String gender = udb.getGender(getCurrentUser().getUsername());
        System.out.println(gender);
        double BMR = 0;

        if(gender.equalsIgnoreCase("Female")){
            BMR = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age); // formula pt BMR femei
        }
        if(gender.equalsIgnoreCase("Male")){
            BMR = 88.362 + (13.397*weight) + (4.799*height) - (5.677*age); // BMR barbati
        }
        System.out.println(BMR);

        int result = (int) BMR;
        return result;
    }

}