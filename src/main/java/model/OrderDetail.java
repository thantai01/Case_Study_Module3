package model;

public class OrderDetail {
    private int idProduct;
    private int idOrder;
    private int quantity;

    public OrderDetail(int idProduct, int idOrder, int quantity) {
        this.idProduct = idProduct;
        this.idOrder = idOrder;
        this.quantity = quantity;
    }

    public OrderDetail() {
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "idProduct=" + idProduct +
                ", idOrder=" + idOrder +
                ", quantity=" + quantity +
                '}';
    }
}
