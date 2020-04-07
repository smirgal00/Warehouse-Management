package Model;

public class Order {
    private Integer ID;
    private String Name;
    private Integer quantity;
    private String product;
    private Double price;

    public Order(Integer ID, String name, Integer quantity, String product, Double price) {
        this.ID = ID;
        Name = name;
        this.quantity = quantity;
        this.product = product;
        this.price = price;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getProduct() {
        return product;
    }

    public Double getPrice() {
        return price;
    }

}
