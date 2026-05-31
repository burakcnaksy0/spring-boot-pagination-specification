package com.burakcanaksoy.springbootpaginationspecification.advanced;

import java.util.List;

public interface BaseMapper<Request, Entity, Response> {
    Entity mapToEntity(Request request);
    Response mapToResponse(Entity entity);
    default List<Response> mapToResponseList(List<Entity> entityList) {
        if (entityList == null || entityList.isEmpty()) {
            return List.of();
        }
        return entityList.stream()
                .map(this::mapToResponse)
                .toList();
    }
    default List<Entity> mapToEntityList(List<Request> requests) {
        if (requests == null || requests.isEmpty()) {
            return List.of();
        }
        return requests.stream()
                .map(this::mapToEntity)
                .toList();
    }
}
