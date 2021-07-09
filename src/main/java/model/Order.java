package model;

public class Order {
    private int id;
    private String userName;
    private int idOrderDetail;
    private double totalPaid;
    private String time;

    public Order(int id, String userName, int idOrderDetail, double totalPaid, String time) {
        this.id = id;
        this.userName = userName;
        this.idOrderDetail = idOrderDetail;
        this.totalPaid = totalPaid;
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

    public int getIdOrderDetail() {
        return idOrderDetail;
    }

    public void setIdOrderDetail(int idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
