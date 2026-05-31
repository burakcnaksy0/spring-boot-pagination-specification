package com.burakcanaksoy.springbootpaginationspecification.product;

import com.burakcanaksoy.common.advanced.BaseMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductMapper implements BaseMapper<ProductCreateRequest, Product, ProductResponse> {
    @Override
    public Product mapToEntity(ProductCreateRequest productCreateRequest) {
        if (productCreateRequest == null){
            return null;
        }
        return Product.builder()
                .sku(productCreateRequest.getSku())
                .name(productCreateRequest.getName())
                .description(productCreateRequest.getDescription())
                .price(productCreateRequest.getPrice())
                .stockQuantity(productCreateRequest.getStockQuantity())
                .isActive(true)
                .category(productCreateRequest.getCategory())
                .build();
    }

    @Override
    public ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .isActive(product.getIsActive())
                .category(product.getCategory())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
 }
