package Model;

/**
 * Represents a Product
 */

public class Product {
    private String name;
    private Integer stock;
    private Double price;

    public Product(String name, Integer stock, Double price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    @Override
    public String toString() {
        String temp;

        temp = "Product " + name + ": Stock: " + stock + " Price" + price;

        return temp;
    }

    public String getName() {
        return name;
    }

    public Integer getStock() {
        return stock;
    }

    public Double getPrice() {
        return price;
    }
}
