package com.tr.obss.jss.jss_marketplace.service;

import com.tr.obss.jss.jss_marketplace.model.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);

    List<Product> getProducts(Integer page, Integer max_result);

    Product addNewProduct(Product product);

    Product updateProduct(Product product);

    List<Product> searchProductsByName(String name);

    void deleteProductById(Long id);
}
