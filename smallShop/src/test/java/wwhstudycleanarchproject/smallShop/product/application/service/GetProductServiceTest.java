package wwhstudycleanarchproject.smallShop.product.application.service;

import org.junit.jupiter.api.Test;
import wwhstudycleanarchproject.smallShop.product.application.port.in.GetProductQuery;
import wwhstudycleanarchproject.smallShop.product.application.port.out.GetProductRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetProductServiceTest {

    private final GetProductRepository repository = mock(GetProductRepository.class);

    private final GetProductQuery useCase = new GetProductService(repository);

    private final static long ANY_ID = 1L;

    @Test
    void getProductTest() {
        //given & when
        useCase.getProduct(ANY_ID);

        //then
        verify(repository).findOne(ANY_ID);
    }
}
