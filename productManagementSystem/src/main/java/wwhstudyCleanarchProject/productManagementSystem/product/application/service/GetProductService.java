package wwhstudyCleanarchProject.productManagementSystem.product.application.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.in.GetProductQuery;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.out.GetProductRepository;
import wwhstudyCleanarchProject.productManagementSystem.product.domain.Product;

@RequiredArgsConstructor
@Service
public class GetProductService implements GetProductQuery {

    private final GetProductRepository repository;

    @Override
    public Product getProduct(Long id) {
        return repository.findOne(id);
    }

}
