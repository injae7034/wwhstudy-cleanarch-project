package wwhstudycleanarchproject.smallShop.product.adapter.in;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import wwhstudycleanarchproject.smallShop.product.application.port.in.CreateProductUseCase;
import wwhstudycleanarchproject.smallShop.product.application.service.AuthenticationService;
import wwhstudycleanarchproject.smallShop.product.domain.Product;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin
public class CreateProductController {

    private final CreateProductUseCase useCase;

    private final AuthenticationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(
            @RequestHeader("Authorization") String authorization,
            @RequestBody Product product
    ) {
        String accessToken = authorization.substring("Bearer ".length());
        Long memberId = service.parseToken(accessToken);

        System.out.println("********* memberId: " + memberId);

        return useCase.createProduct(product);
    }
}
