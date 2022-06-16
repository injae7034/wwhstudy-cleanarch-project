package wwhstudycleanarchproject.smallShop.product.application.port.in;

import wwhstudycleanarchproject.smallShop.product.domain.Product;

public interface CreateProductUseCase {

    Product createProduct(Product product);

}
