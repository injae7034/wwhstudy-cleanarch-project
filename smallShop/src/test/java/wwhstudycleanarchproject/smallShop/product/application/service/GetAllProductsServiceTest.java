package wwhstudycleanarchproject.smallShop.product.application.service;

import org.junit.jupiter.api.Test;
import wwhstudycleanarchproject.smallShop.product.application.port.in.get.GetAllProductsQuery;
import wwhstudycleanarchproject.smallShop.product.application.port.out.GetAllProductsRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetAllProductsServiceTest {

    private final GetAllProductsRepository repository =
            mock(GetAllProductsRepository.class);

    private final GetAllProductsQuery query =
            new GetAllProductsService(repository);

    @Test
    void getAllProductsTest() {
        //given & when
        query.getAllProducts();

        //then
        verify(repository).findAll();
    }
}
