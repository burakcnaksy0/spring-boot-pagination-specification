package com.burakcanaksoy.springbootpaginationspecification.product;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    public static Specification<Product> hasName(String name) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("name"), name);
        });
    }

    public static Specification<Product> hasSku(String sku) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("sku"), sku);
        });
    }

    public static Specification<Product> hasPrice(BigDecimal price) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("price"), price);
        });
    }

    public static Specification<Product> hasStockQuantity(int stockQuantity) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("stockQuantity"), stockQuantity);
        });
    }

    public static Specification<Product> hasCategory(ProductCategory category) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("category"), category);
        });
    }

    public static Specification<Product> greaterThanPrice(BigDecimal price) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get("price"), price);
        });
    }

    public static Specification<Product> lessThanPrice(BigDecimal price) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThan(root.get("price"), price);
        });
    }

    public static Specification<Product> betweenPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        return ((root, query, criteriaBuilder) -> {
            if (minPrice == null && maxPrice == null) {
                return null;
            }
            if (minPrice == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            }
            if (maxPrice == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            }
            return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
        });
    }

    public static Specification<Product> filter(ProductFilter filter) {
        return (root, query, cb) -> cb.and(
                cb.equal(root.get("name"), filter.getName()),
                cb.greaterThanOrEqualTo(root.get("price"), filter.getMinPrice()),
                cb.lessThanOrEqualTo(root.get("price"), filter.getMaxPrice()),
                cb.greaterThanOrEqualTo(root.get("stockQuantity"), filter.getMinStockQuantity()),
                cb.lessThanOrEqualTo(root.get("stockQuantity"), filter.getMaxStockQuantity())
        );
    }
}
