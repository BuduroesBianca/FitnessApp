package fitness.app;

public class User {

    private int id;
    private String fname;
    private String lname;
    private int age;
    private String gender;
    private int height;
    private int weight;
    private String bodyType;
    private String goal;
    private boolean loggedIn = false;
    private String password;
    private boolean vegetarian;
    private int targetWeight;
    private String typicalDay;

    public User(String fname, String lname, String password) {
        this.fname = fname;
        this.lname = lname;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int id, String fname, String lname, int age, String gender, int height, int weight, String bodyType, String goal, String typicalDay,boolean vegetarian,int targetWeight) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bodyType = bodyType;
        this.goal = goal;
        this.vegetarian = vegetarian;
        this.targetWeight = targetWeight;
        this.typicalDay = typicalDay;
    }

    public User(String fname, String lname, int age, String gender, int height, int weight, String bodyType, String goal,String typicalDay,boolean vegetarian,int targetWeight) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bodyType = bodyType;
        this.goal = goal;
        this.vegetarian = vegetarian;
        this.targetWeight = targetWeight;
        this.typicalDay = typicalDay;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getTargetWeight() {
        return targetWeight;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getGoal() {
        return goal;
    }
}
