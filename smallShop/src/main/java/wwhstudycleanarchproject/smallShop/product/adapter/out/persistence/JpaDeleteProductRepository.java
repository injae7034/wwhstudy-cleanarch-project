package wwhstudycleanarchproject.smallShop.product.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudycleanarchproject.smallShop.product.application.port.out.DeleteProductRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaDeleteProductRepository implements DeleteProductRepository {

    private final EntityManager em;

    @Override
    public Product delete(Product product) {
        em.remove(product);
        return product;
    }
}
