package webshop.dto;

public class ProductDTO {
    private String productNumber;
    private String name;
    private double price;
    private String description;
    private int numberInStock;

    public ProductDTO(){}

    public ProductDTO(String productNumber, String name, double price, String description, int numberInStock) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.description = description;
        this.numberInStock = numberInStock;
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

    public String getDescription() {
        return description;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productNumber='" + productNumber + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", numberInStock=" + numberInStock +
                '}';
    }
}