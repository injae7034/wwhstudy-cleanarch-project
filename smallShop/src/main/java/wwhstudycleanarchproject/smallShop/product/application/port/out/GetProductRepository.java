package wwhstudycleanarchproject.smallShop.product.application.port.out;

import wwhstudycleanarchproject.smallShop.product.domain.Product;

public interface GetProductRepository {

    Product findOne(Long id);

}
