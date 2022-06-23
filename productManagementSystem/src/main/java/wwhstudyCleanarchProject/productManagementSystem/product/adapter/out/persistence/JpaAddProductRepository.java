package wwhstudyCleanarchProject.productManagementSystem.product.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.out.AddProductRepository;
import wwhstudyCleanarchProject.productManagementSystem.product.domain.Product;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaAddProductRepository implements AddProductRepository {

    private final EntityManager em;

    @Override
    public Product save(Product product) {
        em.persist(product);
        return product;
    }
}
