package wwhstudyCleanarchProject.productManagementSystem.product.application.port.in;

import wwhstudyCleanarchProject.productManagementSystem.product.domain.Product;

public interface CreateProductUseCase {

    Product createProduct(Product product);

}
