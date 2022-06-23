package wwhstudyCleanarchProject.productManagementSystem.product.adapter.in.add;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wwhstudyCleanarchProject.productManagementSystem.product.application.port.in.AddProductUseCase;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class AddProductController {

    private final AddProductUseCase useCase;

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("addProductForm", new AddProductForm());
        return "products/addProductForm";
    }

}
