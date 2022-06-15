package wwhstudycleanarchproject.smallShop.product.application.port.in.create;

import wwhstudycleanarchproject.smallShop.product.domain.Product;

public interface CreateProductUseCase {

    Product createProduct(CreateProductCommand command);

}
