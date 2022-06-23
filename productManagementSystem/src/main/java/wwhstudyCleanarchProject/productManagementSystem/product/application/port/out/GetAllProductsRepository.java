package wwhstudyCleanarchProject.productManagementSystem.product.application.port.out;


import wwhstudyCleanarchProject.productManagementSystem.product.domain.Product;

import java.util.List;

public interface GetAllProductsRepository {

    List<Product> findAll();
    
}
