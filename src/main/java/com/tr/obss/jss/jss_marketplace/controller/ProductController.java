package com.tr.obss.jss.jss_marketplace.controller;

import com.tr.obss.jss.jss_marketplace.model.Product;
import com.tr.obss.jss.jss_marketplace.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public List<Product> getAllProducts(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "max_result", defaultValue = "10") Integer maxResult) {
        return productService.getProducts(page, maxResult);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
    @PatchMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
    @DeleteMapping
    public void deleteProduct(@RequestBody Product product) {
        productService.deleteProductById(product.getId());
    }
}
