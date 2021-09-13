package webshop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class ShoppingCart {
    @Id
    private String cartId;
    private List<EventEntry> eventEntries;

    public ShoppingCart(){}

    public ShoppingCart(String cartId) {
        this.cartId = cartId;
        eventEntries = new ArrayList<>();
    }

    public EventEntry addToCart(Product product,int quantity){
        EventEntry eventEntry = new EventEntry(this.cartId,product,quantity,Detail.PRODUCT_ADDED);
        this.eventEntries.add(eventEntry);
        return eventEntry;
    }

    public EventEntry removeFromCart(Product product,int quantity){
        EventEntry eventEntry = new EventEntry(this.cartId,product,quantity,Detail.PRODUCT_REMOVED);
        this.eventEntries.add(eventEntry);
        return eventEntry;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartId='" + cartId + '\'' +
                ", eventEntries=" + eventEntries +
                '}';
    }
}
