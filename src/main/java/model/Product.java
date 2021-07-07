package model;

public class Product {
    private int id;
    private String name;
    private int price;
    private String madeIn;
    private String image;
    private int quantity;
    private int idType;

    public Product() {
    }

    public Product(int id, String name, int price, String madeIn, String image, int quantity, int idType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.madeIn = madeIn;
        this.image = image;
        this.quantity = quantity;
        this.idType = idType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", madeIn='" + madeIn + '\'' +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", idType=" + idType +
                '}';
    }
}
