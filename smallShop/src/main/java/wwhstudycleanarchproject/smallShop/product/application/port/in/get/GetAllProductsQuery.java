package wwhstudycleanarchproject.smallShop.product.application.port.in.get;

import wwhstudycleanarchproject.smallShop.product.domain.Product;

import java.util.List;

public interface GetAllProductsQuery {

    List<Product> getAllProducts();

}
