package model;

public class Order {
    private int id;
    private String userName;
    private String time;

    public Order(int id, String userName, String time) {
        this.id = id;
        this.userName = userName;
        this.time = time;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
