package wwhstudycleanarchproject.smallShop.product.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.smallShop.product.application.port.out.CreateProductRepository;
import wwhstudycleanarchproject.smallShop.product.application.port.out.GetAllProductsRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaCreateProductRepository.class, JpaGetAllProductsRepository.class})
@Transactional
class JpaGetAllProductsRepositoryTest {

    @Autowired
    CreateProductRepository createProductRepository;

    @Autowired
    GetAllProductsRepository getAllProductsRepository;

    @Test
    void findAllTest() {
        //given & when
        Product one = new Product("스프링", "한빛미디어", 10000, 100);
        System.out.println(one.getId());

        Product savedOne = createProductRepository.save(one);
        System.out.println(savedOne.getId());

        Product two = new Product("JPA", "가메출판사", 20000, 200);
        System.out.println(two.getId());

        Product savedTwo = createProductRepository.save(two);
        System.out.println(savedTwo.getId());

        //then
        assertThat(getAllProductsRepository.findAll())
                .contains(one, two);
        assertThat(getAllProductsRepository.findAll())
                .contains(savedOne, savedTwo);
    }
}
