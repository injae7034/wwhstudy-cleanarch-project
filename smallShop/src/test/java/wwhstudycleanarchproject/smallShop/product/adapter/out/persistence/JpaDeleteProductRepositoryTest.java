package wwhstudycleanarchproject.smallShop.product.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.smallShop.product.application.port.out.CreateProductRepository;
import wwhstudycleanarchproject.smallShop.product.application.port.out.DeleteProductRepository;
import wwhstudycleanarchproject.smallShop.product.application.port.out.GetAllProductsRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaCreateProductRepository.class,
        JpaDeleteProductRepository.class,
        JpaGetAllProductsRepository.class})
@Transactional
class JpaDeleteProductRepositoryTest {

    @Autowired
    CreateProductRepository createProductRepository;

    @Autowired
    DeleteProductRepository deleteProductRepository;

    @Autowired
    GetAllProductsRepository getAllProductsRepository;

    @Test
    void deleteTest() {
        //given
        Product original = new Product("스프링", "한빛미디어", 10000, 100);
        System.out.println(original.getId());

        Product savedProduct = createProductRepository.save(original);
        System.out.println(savedProduct.getId());

        assertThat(getAllProductsRepository.findAll()).hasSize(1);

        //when
        deleteProductRepository.delete(original);

        //then
        assertThat(getAllProductsRepository.findAll()).hasSize(0);

    }
}
