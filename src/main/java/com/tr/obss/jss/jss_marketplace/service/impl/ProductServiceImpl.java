package com.tr.obss.jss.jss_marketplace.service.impl;

import com.tr.obss.jss.jss_marketplace.model.Product;
import com.tr.obss.jss.jss_marketplace.repository.ProductRepository;
import com.tr.obss.jss.jss_marketplace.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public List<Product> getProducts(Integer page, Integer maxResult) {
        Objects.requireNonNull(page, "page cannot be null");
        Objects.requireNonNull(maxResult, "max_result cannot be null");
        return productRepository.findAll(PageRequest.of(page, maxResult)).getContent();
    }

    @Override
    public Product addNewProduct(Product product) {
        Objects.requireNonNull(product, "product cannot be null");
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Objects.requireNonNull(product, "product cannot be null");
        return this.productRepository.save(product);
    }

    // TODO: Implement
    @Override
    public List<Product> searchProductsByName(String name) {
        Objects.requireNonNull(name, "name cannot be empty");
        return null;
    }

    @Transactional
    @Override
    public void deleteProductById(Long id) {
        Objects.requireNonNull(id);
        productRepository.removeProductById(id);
    }
}
