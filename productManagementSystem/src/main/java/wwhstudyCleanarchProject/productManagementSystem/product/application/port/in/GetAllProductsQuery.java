package wwhstudyCleanarchProject.productManagementSystem.product.application.port.in;


import wwhstudyCleanarchProject.productManagementSystem.product.domain.Product;

import java.util.List;

public interface GetAllProductsQuery {

    List<Product> getAllProducts();

}
