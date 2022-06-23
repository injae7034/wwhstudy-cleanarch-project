package wwhstudyCleanarchProject.productManagementSystem.product.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.in.UpdateProductUseCase;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.out.UpdateProductRepository;
import wwhstudyCleanarchProject.productManagementSystem.product.domain.Product;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateProductService implements UpdateProductUseCase {

    private final UpdateProductRepository repository;

    @Override
    public Product updateProduct(Product product)  {
        return repository.update(product);
    }
}
