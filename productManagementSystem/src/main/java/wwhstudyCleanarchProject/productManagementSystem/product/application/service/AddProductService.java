package wwhstudyCleanarchProject.productManagementSystem.product.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.in.AddProductUseCase;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.out.AddProductRepository;
import wwhstudyCleanarchProject.productManagementSystem.product.domain.Product;

@RequiredArgsConstructor
@Transactional
@Service
public class AddProductService implements AddProductUseCase {

    private final AddProductRepository repository;

    @Override
    public Product addProduct(Product product) {
        return repository.save(product);
    }
}
