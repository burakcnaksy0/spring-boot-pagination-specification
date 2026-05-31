package com.burakcanaksoy.springbootpaginationspecification.product;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Boolean isActive;
    private ProductCategory category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}