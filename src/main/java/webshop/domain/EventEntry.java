package webshop.domain;

import java.time.LocalDateTime;

public class EventEntry {
    private String cartId;
    private Product product;
    private int quantity;
    private Detail detail;
    private LocalDateTime dateTime;

    public EventEntry() {}

    public EventEntry(String cartId,Product product, int quantity, Detail detail) {
        this.cartId = cartId;
        this.product = product;
        this.quantity = quantity;
        this.detail = detail;
        dateTime = LocalDateTime.now();
    }

    public String getCartId() {
        return cartId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Detail getDetail() {
        return detail;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "EventEntry{" +
                "cartId='" + cartId + '\'' +
                ", product=" + product +
                ", quantity=" + quantity +
                ", detail=" + detail +
                ", dateTime=" + dateTime +
                '}';
    }
}
