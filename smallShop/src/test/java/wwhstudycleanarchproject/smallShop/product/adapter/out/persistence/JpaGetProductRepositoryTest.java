package wwhstudycleanarchproject.smallShop.product.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.smallShop.product.application.port.out.CreateProductRepository;
import wwhstudycleanarchproject.smallShop.product.application.port.out.GetProductRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaCreateProductRepository.class, JpaGetProductRepository.class})
@Transactional
class JpaGetProductRepositoryTest {

    @Autowired
    CreateProductRepository createProductRepository;

    @Autowired
    GetProductRepository getProductRepository;

    @Test
    void findOneTest() {
        //given & when
        Product product = new Product("스프링", "한빛미디어", 10000, 100);
        System.out.println(product.getId());

        Product savedProduct = createProductRepository.save(product);
        System.out.println(savedProduct.getId());

        //then
        assertThat(savedProduct).isEqualTo(getProductRepository.findOne(savedProduct.getId()));
        assertThat(product).isEqualTo(getProductRepository.findOne(product.getId()));

    }
}
