package com.burakcanaksoy.springbootpaginationspecification.product;


import com.burakcanaksoy.common.advanced.exception.AlreadyExistsException;
import com.burakcanaksoy.common.advanced.exception.ResourceNotFoundException;
import com.burakcanaksoy.common.advanced.service.AbstractCrudService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ProductService extends AbstractCrudService<ProductCreateRequest, Product, ProductResponse, Long> {
    private final ProductRepository repository;
    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("id", "name", "price", "sku");

    protected ProductService(ProductRepository repository, ProductMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
    }

    @Override
    public ProductResponse create(ProductCreateRequest productCreateRequest) {
        checkSkuExists(productCreateRequest.getSku());
        return super.create(productCreateRequest);
    }

    private void checkSkuExists(String sku) {
        if (repository.existsBySku(sku)) {
            throw new AlreadyExistsException("Product already exists with sku: " + sku);
        }
    }

    public Page<ProductResponse> getAllForPage(int pageNumber, int pageSize,
                                               String field, String sort) {
        Pageable pageable = buildPageable(pageNumber, pageSize, field, sort);
        return repository.findAll(pageable).map(mapper::mapToResponse);
    }

    private Pageable buildPageable(int pageNumber, int pageSize, String field, String sort) {
        if (field == null || field.isBlank()) {
            return PageRequest.of(pageNumber, pageSize);
        }

        if (!ALLOWED_SORT_FIELDS.contains(field)) {
            throw new ResourceNotFoundException("Geçersiz sort alanı: " + field);
        }

        Sort.Direction direction = "desc".equalsIgnoreCase(sort)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return PageRequest.of(pageNumber, pageSize, Sort.by(direction, field));
    }

    public Page<ProductResponse> getForPage() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").ascending());
        Page<Product> page = repository.findAll(pageable);
        List<ProductResponse> productResponsesList = mapper.mapToResponseList(page.getContent());
        PageImpl<ProductResponse> productResponses = new PageImpl<>(productResponsesList, pageable, page.getTotalElements());
        return productResponses;
    }


    public Map<String, Object> info() {
        Pageable pageable = PageRequest.of(0, 15);
        Page<Product> page = repository.findAll(pageable);
        Map<String, Object> stringMap = Map.of(
                "toplam eleman sayısı: ", page.getTotalElements(),
                "toplam sayfa sayısı: ", page.getTotalPages(),
                "şuan hangi sayfa? : ", page.getNumber(),
                "son sayfa mı? : ", page.isLast(),
                "ilk sayfa mı? : ", page.isFirst(),
                "sayfadaki eleman sayısı: ", page.getNumberOfElements()
        );
        return stringMap;
    }

    public Page<Product> searchAndPage(int page, int size, String field, String value) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> saved;

        switch (field) {
            case "name": {
                saved = repository.findByName(value, pageable);
                break;
            }

            case "price": {
                saved = repository.findByPrice(new BigDecimal(value), pageable);
                break;
            }

            case "sku": {
                saved = repository.findBySku(value, pageable);
                break;
            }

            case "stock_quantity": {
                saved = repository.findByStockQuantity(Integer.parseInt(value), pageable);
                break;
            }

            case "category": {
                ProductCategory productCategory = ProductCategory.valueOf(value.toUpperCase());
                saved = repository.findByCategory(productCategory, pageable);
                break;
            }

            default: {
                throw new IllegalArgumentException("Invalid field : "+ field);
            }
        }
        return saved;

    }
}
