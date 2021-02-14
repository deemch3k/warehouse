package pl.dyplom.service;

import org.springframework.web.multipart.MultipartFile;
import pl.dyplom.domain.Product;
import pl.dyplom.domain.ProductQuantity;

import java.util.List;
import java.util.Set;

public interface ProductService {


    Product add(Product product);
    void delete(Product product);
    Product update(Product productFromDb, Product product);
    List<Product> getList();
    void updateProducts(Set<ProductQuantity> productQuantities, String action);

    void saveProductsFromFile(MultipartFile multipartFile);

}
