package com.burakcanaksoy.springbootpaginationspecification.product;


import com.burakcanaksoy.common.advanced.exception.AlreadyExistsException;
import com.burakcanaksoy.common.advanced.service.AbstractCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractCrudService<ProductCreateRequest, Product, ProductResponse, Long> {
    private final ProductRepository repository;

    protected ProductService(ProductRepository repository,ProductMapper mapper) {
        super(repository, mapper);
        this.repository=repository;
    }

    @Override
    public ProductResponse create(ProductCreateRequest productCreateRequest) {
        checkSkuExists(productCreateRequest.getSku());
        return super.create(productCreateRequest);
    }

    public Page<ProductResponse> getAllForPage(
            int pageNumber,
            int pageSize,
            String field,
            String sort) {

        Pageable pageable;

        if (field != null && !field.isBlank()) {

            Sort.Direction direction =
                    "desc".equalsIgnoreCase(sort)
                            ? Sort.Direction.DESC
                            : Sort.Direction.ASC;

            pageable = PageRequest.of(
                    pageNumber,
                    pageSize,
                    Sort.by(direction, field)
            );

        } else {

            pageable = PageRequest.of(
                    pageNumber,
                    pageSize
            );
        }

        return repository.findAll(pageable)
                .map(mapper::mapToResponse);
    }

    private void checkSkuExists(String sku){
        if (repository.existsBySku(sku)){
            throw new AlreadyExistsException("Product already exists with sku: "+sku);
        }
    }
}
