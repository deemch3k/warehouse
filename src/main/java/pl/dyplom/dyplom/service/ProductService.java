package pl.dyplom.dyplom.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dyplom.dyplom.domain.Product;
import pl.dyplom.dyplom.domain.ProductQuantity;
import pl.dyplom.dyplom.repo.ProductRepo;

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
        BeanUtils.copyProperties(product, productFromDb);
        return productRepo.save(productFromDb);
    }

    public List<Product> getList() {
        return productRepo.findAll();
    }

    public void updateProducts(Set<ProductQuantity> productQuantities) {
        List<Product> products = productRepo.findAll();

        for (ProductQuantity pq : productQuantities) {
            for (Product p : products) {
                if (pq.getOrderedProduct().getName().equals(p.getName())) {
                    p.setTotalAmount(p.getTotalAmount() - pq.getQty());
                }
            }
        }
        productRepo.saveAll(products);
    }
}