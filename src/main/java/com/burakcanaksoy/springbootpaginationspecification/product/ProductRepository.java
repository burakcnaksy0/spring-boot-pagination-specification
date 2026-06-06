package com.burakcanaksoy.springbootpaginationspecification.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsBySku(String sku);

    Page<Product> findByCategory(ProductCategory category, Pageable pageable);

    Page<Product> findByName(String value, Pageable pageable);

    Page<Product> findByPrice(BigDecimal i, Pageable pageable);

    Page<Product> findBySku(String value, Pageable pageable);

    Page<Product> findByStockQuantity(Integer i, Pageable pageable);

}
