package wwhstudycleanarchproject.smallShop.product.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.smallShop.product.application.port.in.update.UpdateProductCommand;
import wwhstudycleanarchproject.smallShop.product.application.port.in.update.UpdateProductUseCase;
import wwhstudycleanarchproject.smallShop.product.application.port.out.CreateProductRepository;
import wwhstudycleanarchproject.smallShop.product.application.port.out.UpdateProductRepository;
import wwhstudycleanarchproject.smallShop.product.application.service.UpdateProductService;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import({JpaCreateProductRepository.class, JpaUpdateProductRepository.class})
@Transactional
class JpaUpdateProductRepositoryTest {

    @Autowired
    CreateProductRepository createProductRepository;

    @Autowired
    UpdateProductRepository updateProductRepository;

    @Test
    void updateTest() {

        //given
        Product original = new Product("스프링", "한빛미디어", 10000, 100);
        System.out.println(original.getId());

        Product savedProduct = createProductRepository.save(original);
        System.out.println(savedProduct.getId());

        Product updatedProduct = new Product(
                savedProduct.getId(),
                "JPA", "가메출판사", 20000, 200);

        //when
        Product afterUpdatingProduct = updateProductRepository.update(updatedProduct);

        //then
        assertThat(afterUpdatingProduct).isEqualTo(original);
        assertThat(afterUpdatingProduct).isEqualTo(savedProduct);
        assertThat(afterUpdatingProduct.getName()).isEqualTo(updatedProduct.getName());
        assertThat(afterUpdatingProduct.getMaker()).isEqualTo(updatedProduct.getMaker());
        assertThat(afterUpdatingProduct.getPrice()).isEqualTo(updatedProduct.getPrice());
        assertThat(afterUpdatingProduct.getStockQuantity()).isEqualTo(updatedProduct.getStockQuantity());

    }
}
