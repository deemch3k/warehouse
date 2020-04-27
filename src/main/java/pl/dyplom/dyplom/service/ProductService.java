package pl.dyplom.dyplom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dyplom.dyplom.domain.Product;
import pl.dyplom.dyplom.repo.ProductRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product add(Product product) {
       return productRepo.save(product);
    }

    public void delete(Product product) {
        productRepo.delete(product);
    }

    public Product update(Product productFromDb, Product product) {
        productFromDb.setDescription(product.getDescription());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setTotalAmount(product.getTotalAmount());
        productFromDb.setName(product.getName());
        productRepo.save(productFromDb);
        return productFromDb;
    }

    public List<Product> getList() {
        return productRepo.findAll();
    }

    public Set<Product> getSet(){
        return new HashSet<>(productRepo.findAll());
    }
}