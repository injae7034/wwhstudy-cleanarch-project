package wwhstudycleanarchproject.smallShop.product.application.port.in.get;

import wwhstudycleanarchproject.smallShop.product.domain.Product;

public interface GetProductQuery {

    Product getProduct(GetProductCommand command);

}
