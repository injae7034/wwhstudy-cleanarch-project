package wwhstudycleanarchproject.smallShop.product.application.port.in.update;

import wwhstudycleanarchproject.smallShop.product.domain.Product;

public interface UpdateProductUseCase {

    Product updateProduct(UpdateProductCommand command);

}
