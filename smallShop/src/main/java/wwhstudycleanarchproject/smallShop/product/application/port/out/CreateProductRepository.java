package wwhstudycleanarchproject.smallShop.product.application.port.out;

import wwhstudycleanarchproject.smallShop.product.domain.Product;

public interface CreateProductRepository {

    Product save(Product product);

}
