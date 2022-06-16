package wwhstudycleanarchproject.smallShop.product.application.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wwhstudycleanarchproject.smallShop.product.application.port.in.GetProductQuery;
import wwhstudycleanarchproject.smallShop.product.application.port.out.GetProductRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

@RequiredArgsConstructor
@Service
public class GetProductService implements GetProductQuery {

    private final GetProductRepository repository;

    @Override
    public Product getProduct(Long id) {
        return repository.findOne(id);
    }

}
