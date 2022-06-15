package wwhstudycleanarchproject.smallShop.product.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudycleanarchproject.smallShop.product.application.port.out.UpdateProductRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaUpdateProductRepository implements UpdateProductRepository {

    private final EntityManager em;

    @Override
    public Product update(Product product) {
        Product findProduct = em.find(Product.class, product.getId());

        findProduct.changeProductInfo(product);

        return findProduct;
    }
}
