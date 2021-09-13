package webshop.service;

import webshop.dto.ProductDTO;

public interface CartCommandService {
    void add(String cartId);
    void addToCart(String cartId, ProductDTO productDTO,int quantity);
    void removeFromCart(String cartId, ProductDTO productDTO, int quantity);
    void deleteCart(String cartId);
}
