package webshop.domain;

public class Product {
    private String productNumber;
    private String name;
    private double price;

    public Product() {}

    public Product(String productNumber, String name, double price) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productNumber='" + productNumber + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
