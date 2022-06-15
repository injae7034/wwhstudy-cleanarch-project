package wwhstudycleanarchproject.smallShop.product.application.service;

import org.junit.jupiter.api.Test;
import wwhstudycleanarchproject.smallShop.product.application.port.in.create.CreateProductCommand;
import wwhstudycleanarchproject.smallShop.product.application.port.in.create.CreateProductUseCase;
import wwhstudycleanarchproject.smallShop.product.application.port.out.CreateProductRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class CreateProductServiceTest {

    private final CreateProductRepository repository =
            mock(CreateProductRepository.class);

    private final CreateProductUseCase useCase =
            new CreateProductService(repository);

    @Test
    void createProductTest() {
        //given & when
        useCase.createProduct(new CreateProductCommand(
                "스프링",
                "한빛미디어",
                10000,
                100
        ));

        //then
        verify(repository).save(any(Product.class));
    }
}
