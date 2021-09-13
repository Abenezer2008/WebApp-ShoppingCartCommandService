package webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.dto.ProductDTO;
import webshop.service.CartCommandService;

@RestController
public class CartController {
    @Autowired
    private CartCommandService cartCommandService;

    @Autowired
    ShoppingCartQueryFeignClient shoppingCartQueryFeignClient;

    @Autowired
    ProductFeignClient productFeignClient;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @PostMapping("/carts/command/{cartId}")
    public ResponseEntity<?> createCart(@PathVariable String cartId){
        cartCommandService.add(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT,HttpStatus.OK);
    }

    @PutMapping("/carts/command/add-product/{cartId}/quantity/{quantity}")
    public ResponseEntity<?> addToCart(@PathVariable String cartId, @RequestBody ProductDTO productDTO, @PathVariable int quantity){
        //Circuit breaker for feign clients.
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        int quantityInStock = circuitBreaker.run(() ->productFeignClient.getProductQuantityStock(productDTO.getProductNumber()), throwable -> getFallBackQuantity()) ;
        int quantityInCart = circuitBreaker.run(() -> shoppingCartQueryFeignClient.getProductQuantityInCart(cartId, productDTO.getProductNumber()), throwable -> getFallBackQuantity());

        if(quantityInStock >= quantityInCart + quantityInCart){
            cartCommandService.addToCart(cartId,productDTO,quantity);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PutMapping("/carts/command/remove-product/{cartId}/quantity/{quantity}")
    public ResponseEntity<?> removeFromCart(@PathVariable String cartId,@RequestBody ProductDTO productDTO, @PathVariable int quantity){
        cartCommandService.removeFromCart(cartId,productDTO,quantity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT,HttpStatus.OK);
    }
    @DeleteMapping("/carts/command/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable String cartId){
        cartCommandService.deleteCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT,HttpStatus.OK);
    }

    @FeignClient(name="ShoppingCartQueryService")
    interface ShoppingCartQueryFeignClient{
        @GetMapping("/carts/query/cart/{cartId}/product/{productId}")
        Integer getProductQuantityInCart(@PathVariable String cartId,@PathVariable String productId);
    }

    @FeignClient(name="ProductService")
    interface ProductFeignClient{
        @GetMapping("/products/{productNumber}/stock")
        Integer getProductQuantityStock(@PathVariable String productNumber);
    }

    private int getFallBackQuantity(){return 0;}
}
