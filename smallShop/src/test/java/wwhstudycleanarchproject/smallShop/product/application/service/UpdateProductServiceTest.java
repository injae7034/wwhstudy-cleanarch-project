package wwhstudycleanarchproject.smallShop.product.application.service;

import org.junit.jupiter.api.Test;
import wwhstudycleanarchproject.smallShop.product.application.port.in.update.UpdateProductCommand;
import wwhstudycleanarchproject.smallShop.product.application.port.in.update.UpdateProductUseCase;
import wwhstudycleanarchproject.smallShop.product.application.port.out.UpdateProductRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UpdateProductServiceTest {

    private final UpdateProductRepository repository =
            mock(UpdateProductRepository.class);

    private final UpdateProductUseCase useCase =
            new UpdateProductService(repository);

    @Test
    void updateProductTest() {

        useCase.updateProduct(new UpdateProductCommand(
                1L,
                "스프링",
                "한빛미디어",
                10000,
                100
        ));

        verify(repository).update(any(Product.class));

    }
}
