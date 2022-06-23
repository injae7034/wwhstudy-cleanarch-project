package wwhstudyCleanarchProject.productManagementSystem.product.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.out.GetProductRepository;
import wwhstudyCleanarchProject.productManagementSystem.product.domain.Product;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaGetProductRepository implements GetProductRepository {

    private final EntityManager em;

    @Override
    public Product findOne(Long id) {
        return em.find(Product.class, id);
    }
}
