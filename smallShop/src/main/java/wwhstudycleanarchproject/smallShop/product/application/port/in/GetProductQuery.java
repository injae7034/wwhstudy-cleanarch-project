package wwhstudycleanarchproject.smallShop.product.application.port.in;

import wwhstudycleanarchproject.smallShop.product.domain.Product;

public interface GetProductQuery {

    Product getProduct(Long id);

}
