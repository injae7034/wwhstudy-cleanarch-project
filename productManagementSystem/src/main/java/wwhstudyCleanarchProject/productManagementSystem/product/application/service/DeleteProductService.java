package wwhstudyCleanarchProject.productManagementSystem.product.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.in.DeleteProductUseCase;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.out.DeleteProductRepository;
import wwhstudyCleanarchProject.productManagementSystem.product.domain.Product;

@RequiredArgsConstructor
@Transactional
@Service
public class DeleteProductService implements DeleteProductUseCase {

    private final DeleteProductRepository repository;

    @Override
    public Product deleteProduct(Product product) {
        return repository.delete(product);
    }
}
