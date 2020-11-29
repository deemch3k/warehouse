package pl.dyplom.dyplom.service;

import pl.dyplom.dyplom.domain.Product;
import pl.dyplom.dyplom.domain.ProductQuantity;

import java.util.List;
import java.util.Set;

public interface ProductService {


    Product add(Product product);
    void delete(Product product);
    Product update(Product productFromDb, Product product);
    List<Product> getList();
    void updateProducts(Set<ProductQuantity> productQuantities, String action);

}
