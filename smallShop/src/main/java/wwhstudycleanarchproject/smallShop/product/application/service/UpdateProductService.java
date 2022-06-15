package wwhstudycleanarchproject.smallShop.product.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.smallShop.product.application.port.in.update.UpdateProductCommand;
import wwhstudycleanarchproject.smallShop.product.application.port.in.update.UpdateProductUseCase;
import wwhstudycleanarchproject.smallShop.product.application.port.out.UpdateProductRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateProductService implements UpdateProductUseCase {

    private final UpdateProductRepository repository;

    @Override
    public Product updateProduct(UpdateProductCommand command) {
        Product product = new Product(
                command.getId(),
                command.getName(),
                command.getMaker(),
                command.getPrice(),
                command.getStockQuantity()
        );

        return repository.update(product);
    }
}
