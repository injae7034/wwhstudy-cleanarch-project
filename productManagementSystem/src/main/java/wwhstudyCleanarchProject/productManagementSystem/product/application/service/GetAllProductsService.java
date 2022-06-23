package wwhstudyCleanarchProject.productManagementSystem.product.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.in.GetAllProductsQuery;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.out.GetAllProductsRepository;
import wwhstudyCleanarchProject.productManagementSystem.product.domain.Product;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllProductsService implements GetAllProductsQuery {

    private final GetAllProductsRepository repository;

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
