package wwhstudyCleanarchProject.productManagementSystem.product.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.in.CreateProductUseCase;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.out.CreateProductRepository;
import wwhstudyCleanarchProject.productManagementSystem.product.domain.Product;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateProductService implements CreateProductUseCase {

    private final CreateProductRepository repository;

    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }
}
