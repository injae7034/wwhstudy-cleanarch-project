package wwhstudycleanarchproject.smallShop.product.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.smallShop.product.application.port.in.DeleteProductUseCase;
import wwhstudycleanarchproject.smallShop.product.application.port.out.DeleteProductRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

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
