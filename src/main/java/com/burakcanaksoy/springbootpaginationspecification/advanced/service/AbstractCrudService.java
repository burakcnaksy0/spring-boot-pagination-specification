package com.burakcanaksoy.springbootpaginationspecification.advanced.service;


import com.burakcanaksoy.springbootpaginationspecification.advanced.BaseMapper;
import com.burakcanaksoy.springbootpaginationspecification.advanced.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudService<Request, Entity, Response, ID>
        implements CrudService<Request, Response, ID> {

    protected final JpaRepository<Entity, ID> repository;
    protected final BaseMapper<Request, Entity, Response> mapper;

    protected AbstractCrudService(JpaRepository<Entity, ID> repository, BaseMapper<Request, Entity, Response> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Response> getAll() {
        List<Entity> entityList = repository.findAll();
        return mapper.mapToResponseList(entityList);
    }

    @Override
    public Response getById(ID id) {
        Optional<Entity> entity = repository.findById(id);
        if (entity.isEmpty()){
            throw new ResourceNotFoundException("Resource not found with this id : "+ id);
        }
        return mapper.mapToResponse(entity.get());
    }

    @Override
    public Response create(Request request) {
        Entity entity = mapper.mapToEntity(request);
        repository.save(entity);
        return mapper.mapToResponse(entity);
    }

    @Override
    @Transactional
    public List<Response> createBatch(List<Request> requests) {
        List<Entity> entityList = mapper.mapToEntityList(requests);
        List<Entity> saved = repository.saveAll(entityList);
        return mapper.mapToResponseList(saved);
    }

    @Override
    public void delete(ID id) {
        Optional<Entity> entity = repository.findById(id);
        if (entity.isEmpty()){
            throw new ResourceNotFoundException("Resource not found with this id : "+ id);
        }
        repository.delete(entity.get());
    }
}
