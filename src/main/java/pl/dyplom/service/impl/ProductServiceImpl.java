package pl.dyplom.service.impl;

import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.dyplom.domain.Product;
import pl.dyplom.domain.ProductQuantity;
import pl.dyplom.repo.ProductRepo;
import pl.dyplom.service.ProductService;
import pl.dyplom.service.XmlParser;

import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    @Qualifier("productsXmlParser")
    private final XmlParser xmlParser;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, XmlParser xmlParser) {
        this.productRepo = productRepo;
        this.xmlParser = xmlParser;
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


    public void updateProducts(Set<ProductQuantity> productQuantities, String action) {
        List<Product> products = productRepo.findAll();

        for (ProductQuantity pq : productQuantities) {
            for (Product p : products) {
                if (pq.getOrderedProduct().getName().equals(p.getName())) {
                    if (action.equals("CREATE")) {
                        p.setTotalAmount(p.getTotalAmount() - pq.getQty());
                    } else if (action.equals("DELETE")) {
                        p.setTotalAmount(p.getTotalAmount() + pq.getQty());
                    }
                }
            }
        }
        productRepo.saveAll(products);
    }

    @SneakyThrows
    @Override
    public void saveProductsFromFile(MultipartFile multipartFile) {
        List<Product> productsFromFile = (List<Product>) xmlParser.parse(multipartFile);
        productsFromFile.forEach(productRepo::save);
    }
}