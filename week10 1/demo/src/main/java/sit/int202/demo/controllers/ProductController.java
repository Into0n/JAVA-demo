package sit.int202.demo.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sit.int202.demo.entities.Employee;
import sit.int202.demo.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("")
    public String getProducts(Model model) {
        model.addAttribute("products", service.findAll());
        return "product_list";
    }

    @GetMapping("/paging")
    public String getProductsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        model.addAttribute("page", service.findAll(PageRequest.of(page, size)));
        return "product_list_paging";
    }

    @GetMapping("/search-product")
    public String searchProductByText(
            @RequestParam(required = false) String searchParam, Model model) {
        model.addAttribute("products", service.findProductByText(searchParam));
        return "product_list";
    }

//    @GetMapping("/search-product-by-price")
//    public String searchProductPrice(
//            @RequestParam(defaultValue = "10.0") Double lowerPrice,
//            @RequestParam (defaultValue = "9999.99") Double upperPrice,
//            Model model) {
//        model.addAttribute("products", service.filterProductByPrice(lowerPrice, upperPrice));
//        return "product_list";
//    }


}
