package wwhstudyCleanarchProject.productManagementSystem.product.adapter.in;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.in.GetAllProductsQuery;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductListController {

    private final GetAllProductsQuery query;

    @GetMapping
    public String productList(Model model) {
        model.addAttribute("products", query.getAllProducts());
        return "products/products";
    }
}
