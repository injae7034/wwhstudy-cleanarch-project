package wwhstudyCleanarchProject.productManagementSystem.product.application.port.out;


import wwhstudyCleanarchProject.productManagementSystem.product.domain.Product;

public interface UpdateProductRepository {

    Product update(Product product);

}
