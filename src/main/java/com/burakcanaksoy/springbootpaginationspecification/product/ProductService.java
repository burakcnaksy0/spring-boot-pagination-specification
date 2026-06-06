package com.burakcanaksoy.springbootpaginationspecification.product;


import com.burakcanaksoy.common.advanced.exception.AlreadyExistsException;
import com.burakcanaksoy.common.advanced.exception.ResourceNotFoundException;
import com.burakcanaksoy.common.advanced.service.AbstractCrudService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductService extends AbstractCrudService<ProductCreateRequest, Product, ProductResponse, Long> {
    private final ProductRepository repository;
    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("id", "name", "price", "sku");

    protected ProductService(ProductRepository repository,ProductMapper mapper) {
        super(repository, mapper);
        this.repository=repository;
    }

    @Override
    public ProductResponse create(ProductCreateRequest productCreateRequest) {
        checkSkuExists(productCreateRequest.getSku());
        return super.create(productCreateRequest);
    }

    private void checkSkuExists(String sku){
        if (repository.existsBySku(sku)){
            throw new AlreadyExistsException("Product already exists with sku: "+sku);
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
        Pageable pageable = PageRequest.of(0,10,Sort.by("id").ascending());
        Page<Product> page = repository.findAll(pageable);
        List<Product> content = page.getContent();
        List<ProductResponse> productResponsesList = mapper.mapToResponseList(content);
        PageImpl<ProductResponse> productResponses = new PageImpl<>(productResponsesList, pageable, page.getTotalElements());
        return productResponses;
    }
}
