package wwhstudycleanarchproject.smallShop.product.application.port.out;

import wwhstudycleanarchproject.smallShop.product.domain.Product;

import java.util.List;

public interface GetAllProductsRepository {

    List<Product> findAll();
    
}
