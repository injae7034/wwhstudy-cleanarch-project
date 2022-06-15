package wwhstudycleanarchproject.smallShop.product.application.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wwhstudycleanarchproject.smallShop.product.application.port.in.get.GetProductCommand;
import wwhstudycleanarchproject.smallShop.product.application.port.in.get.GetProductQuery;
import wwhstudycleanarchproject.smallShop.product.application.port.out.GetProductRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

@RequiredArgsConstructor
@Service
public class GetProductService implements GetProductQuery {

    private final GetProductRepository repository;

    @Override
    public Product getProduct(GetProductCommand command) {
        return repository.findOne(command.getId());
    }

}
