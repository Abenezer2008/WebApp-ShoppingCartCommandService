package webshop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webshop.domain.EventEntry;
import webshop.domain.Product;
import webshop.domain.ShoppingCart;
import webshop.dto.EventAdapter;
import webshop.dto.EventDTO;
import webshop.dto.ProductAdapter;
import webshop.dto.ProductDTO;
import webshop.integration.CreateEventSender;
import webshop.integration.DeleteEventSender;
import webshop.integration.Sender;
import webshop.repository.ShoppingCartRepository;

import java.util.Optional;

@Service
public class CartCommandServiceImpl implements CartCommandService{

    @Autowired
    private ShoppingCartRepository cartRepository;
    @Autowired
    private Sender sender;
    @Autowired
    private CreateEventSender createEventSender;
    @Autowired
    private DeleteEventSender deleteEventSender;

    private static final Logger logger = LoggerFactory.getLogger(CartCommandServiceImpl.class.getName());


    @Override
    public void add(String cartId) {
        logger.info("Calling add");
        ShoppingCart cart = new ShoppingCart(cartId);
        cartRepository.save(cart);
        logger.info("Creating shopping cart");
        createEventSender.send(cartId);
    }

    @Override
    public void addToCart(String cartId, ProductDTO productDTO, int quantity) {
        logger.info("Calling add to cart");
        Optional<ShoppingCart> optionalShoppingCart = cartRepository.findById(cartId);
        if(optionalShoppingCart.isPresent()){
            ShoppingCart cart = optionalShoppingCart.get();
            Product product = ProductAdapter.fromDTO(productDTO);
            EventEntry eventEntry = cart.addToCart(product,quantity);
            EventDTO eventDTO = EventAdapter.toDTO(eventEntry);
            cartRepository.save(cart);
            logger.info("Adding item to cart");
            sender.send(eventDTO);
        }else logger.error("Add to cart with invalid id");
    }

    @Override
    public void removeFromCart(String cartId, ProductDTO productDTO, int quantity) {
        logger.info("Calling remove from cart");
        Optional<ShoppingCart> optionalShoppingCart = cartRepository.findById(cartId);
        if(optionalShoppingCart.isPresent()){
            ShoppingCart cart = optionalShoppingCart.get();
            Product product = ProductAdapter.fromDTO(productDTO);
            EventEntry eventEntry = cart.removeFromCart(product,quantity);
            EventDTO eventDTO = EventAdapter.toDTO(eventEntry);
            cartRepository.save(cart);
            logger.info("Removing item from cart");
            sender.send(eventDTO);
        } else logger.error("Remove from cart with invalid id");
    }

    @Override
    public void deleteCart(String cartId) {
        logger.info("Calling remove");
        cartRepository.deleteById(cartId);
        deleteEventSender.send(cartId);
    }
}
