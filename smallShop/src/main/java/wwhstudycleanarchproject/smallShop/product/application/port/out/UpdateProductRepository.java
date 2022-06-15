package wwhstudycleanarchproject.smallShop.product.application.port.out;

import wwhstudycleanarchproject.smallShop.product.domain.Product;

public interface UpdateProductRepository {

    Product update(Product product);

}
