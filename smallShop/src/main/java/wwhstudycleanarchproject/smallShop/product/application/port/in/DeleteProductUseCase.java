package wwhstudycleanarchproject.smallShop.product.application.port.in;

import wwhstudycleanarchproject.smallShop.product.domain.Product;

public interface DeleteProductUseCase {

    Product deleteProduct(Product product);

}
