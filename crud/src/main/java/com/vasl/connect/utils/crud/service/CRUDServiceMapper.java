package com.vasl.connect.utils.crud.service;

import com.vasl.connect.utils.crud.dal.Entity;
import org.mapstruct.MappingTarget;

public interface CRUDServiceMapper<E> {
    void updateEntity(@MappingTarget E existingEntity, E newEntity);
    void updateBaseEntity(@MappingTarget Entity existing, Entity existingBaseClone);
}
