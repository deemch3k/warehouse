package pl.dyplom.dyplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dyplom.dyplom.domain.Product;
import pl.dyplom.dyplom.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("{id}")
    public Product getOne(@PathVariable("id") Product product){
        return product;
    }

    @GetMapping
    public List<Product> list(){
        return productService.getList();
    }

    @PostMapping
    public Product create(@RequestBody Product product){
       return productService.add(product);
    }

    @PutMapping("{id}")
    public Product update(@PathVariable("id") Product productFromDb, @RequestBody Product product){
        return productService.update(productFromDb, product);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Product product){
        productService.delete(product);
    }

}
