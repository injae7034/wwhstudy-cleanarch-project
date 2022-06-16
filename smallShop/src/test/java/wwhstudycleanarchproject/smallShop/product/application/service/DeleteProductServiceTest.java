package wwhstudycleanarchproject.smallShop.product.application.service;

import org.junit.jupiter.api.Test;
import wwhstudycleanarchproject.smallShop.product.application.port.in.DeleteProductUseCase;
import wwhstudycleanarchproject.smallShop.product.application.port.out.DeleteProductRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteProductServiceTest {

    private final DeleteProductRepository repository =
            mock(DeleteProductRepository.class);

    private final DeleteProductUseCase useCase =
            new DeleteProductService(repository);

    @Test
    void deleteProductTest() {
        //given & when
        useCase.deleteProduct(new Product("스프링",
                "한빛미디어",
                10000,
                100));

        //then
        verify(repository).delete(any(Product.class));
    }
}
