package model;

public class User {
    private String userID;
    private String userPassword;
    private String userAddress;
    private String userFullName;
    private String userPhone;
    private int role;

    public User(String userID, String userPassword,String userAddress ,String userFullName, String userPhone, int role) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userFullName = userFullName;
        this.userPhone = userPhone;
        this.role = role;
    }

    public User(String userID, String userPassword, String userAddress, String userFullName, String userPhone) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userFullName = userFullName;
        this.userPhone = userPhone;
    }

    public User(String userID, String userPassword, int role) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.role = role;
    }

    public User() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
