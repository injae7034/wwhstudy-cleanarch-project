package wwhstudycleanarchproject.smallShop.product.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wwhstudycleanarchproject.smallShop.product.application.port.in.get.GetAllProductsQuery;
import wwhstudycleanarchproject.smallShop.product.application.port.out.GetAllProductsRepository;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllProductsService implements GetAllProductsQuery {

    private final GetAllProductsRepository repository;

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
