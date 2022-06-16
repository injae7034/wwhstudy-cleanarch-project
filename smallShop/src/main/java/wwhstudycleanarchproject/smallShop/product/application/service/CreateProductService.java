package wwhstudycleanarchproject.smallShop.product.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.smallShop.product.application.port.in.CreateProductUseCase;
import wwhstudycleanarchproject.smallShop.product.application.port.out.CreateProductRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

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
