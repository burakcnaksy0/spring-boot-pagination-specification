package com.burakcanaksoy.springbootpaginationspecification.advanced.service;



import com.burakcanaksoy.springbootpaginationspecification.advanced.BaseMapper;
import com.burakcanaksoy.springbootpaginationspecification.advanced.repository.ReadOnlyRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractReadOnlyService<Request, Entity, Response, ID> implements ReadOnlyService<Response, ID> {
    protected final BaseMapper<Request, Entity, Response> mapper;
    protected final ReadOnlyRepository<Entity, ID> repository;

    protected AbstractReadOnlyService(BaseMapper<Request, Entity, Response> mapper, ReadOnlyRepository<Entity, ID> repository) {
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
        return mapper.mapToResponse(entity.get());
    }
}
