package sit.int202.demo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sit.int202.demo.entities.Employee;
import sit.int202.demo.entities.Product;
import sit.int202.demo.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;


@Service
public class ProductService {
    private ProductRepository repository;
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    public List<Product> findAll() {
        return repository.findAll();
    }
    public Product findById(String productCode) {
        return repository.findById(productCode).orElse(null);
    }

    public List<Product> findProductByText(String searchParam) {
        String keyword = "%" + searchParam + "%";
        return repository.findAllProductsByKeyword(keyword, keyword, keyword);
    }

    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

//    public List<Product> filterProductByPrice(Double lower, Double upper) {
//        return repository.filterProductsByPrice(BigDecimal.valueOf(lower), BigDecimal.valueOf(upper));
//  }

}
