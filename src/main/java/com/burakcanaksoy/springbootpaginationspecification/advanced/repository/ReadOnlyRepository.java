package com.burakcanaksoy.springbootpaginationspecification.advanced.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyRepository<Entity,ID>  extends Repository<Entity,ID> {
    Optional<Entity> findById(ID id);
    List<Entity> findAll();
    List<Entity> findAll(Sort sort);
    Page<Entity> findAll(Pageable pageable);
    boolean existsById(ID id);
    long count();
}
