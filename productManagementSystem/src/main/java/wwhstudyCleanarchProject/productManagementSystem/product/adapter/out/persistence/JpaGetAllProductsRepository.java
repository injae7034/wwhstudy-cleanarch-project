package wwhstudyCleanarchProject.productManagementSystem.product.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.out.GetAllProductsRepository;
import wwhstudyCleanarchProject.productManagementSystem.product.domain.Product;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaGetAllProductsRepository implements GetAllProductsRepository {

    private final EntityManager em;

    @Override
    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class)
                .getResultList();
    }
}
