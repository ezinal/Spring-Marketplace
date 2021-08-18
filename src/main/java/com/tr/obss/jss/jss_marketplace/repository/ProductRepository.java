package com.tr.obss.jss.jss_marketplace.repository;

import com.tr.obss.jss.jss_marketplace.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    List<Product> findAll();

    void removeProductById(Long id);
}
