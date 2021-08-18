package com.tr.obss.jss.jss_marketplace.controller;

import com.tr.obss.jss.jss_marketplace.model.Product;
import com.tr.obss.jss.jss_marketplace.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public List<Product> getProducts(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "max_result", defaultValue = "10") Integer maxResult) {
        return productService.getProducts(page, maxResult);
    }

    // TODO: check for admin role
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    // TODO: check for admin role
    @PatchMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody Product product) {
        productService.deleteProductById(product.getId());
    }
}
