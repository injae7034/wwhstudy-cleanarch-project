package wwhstudycleanarchproject.smallShop.product.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudycleanarchproject.smallShop.product.application.port.out.CreateProductRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaCreateProductRepository implements CreateProductRepository {

    private final EntityManager em;

    @Override
    public Product save(Product product) {
        em.persist(product);
        return product;
    }
}
