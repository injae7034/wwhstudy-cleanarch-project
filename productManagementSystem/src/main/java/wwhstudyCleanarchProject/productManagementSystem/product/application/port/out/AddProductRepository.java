package wwhstudyCleanarchProject.productManagementSystem.product.application.port.out;


import wwhstudyCleanarchProject.productManagementSystem.product.domain.Product;

public interface AddProductRepository {

    Product save(Product product);

}
