package webshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import webshop.domain.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart,String > {}
