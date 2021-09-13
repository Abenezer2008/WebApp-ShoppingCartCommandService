package webshop.dto;

import webshop.domain.Detail;

public class EventDTO {
    private String cartId;
    private ProductDTO productDTO;
    private int quantity;
    private Detail detail;

    public EventDTO(){}

    public EventDTO(String cartId, ProductDTO productDTO, int quantity, Detail detail) {
        this.cartId = cartId;
        this.productDTO = productDTO;
        this.quantity = quantity;
        this.detail = detail;
    }

    public String getCartId() {
        return cartId;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public int getQuantity() {
        return quantity;
    }

    public Detail getDetail() {
        return detail;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "cartId='" + cartId + '\'' +
                ", productDTO=" + productDTO +
                ", quantity=" + quantity +
                ", detail=" + detail +
                '}';
    }
}
