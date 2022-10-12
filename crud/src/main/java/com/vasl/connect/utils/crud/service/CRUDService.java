package com.vasl.connect.utils.crud.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface CRUDService<E> {
    E create(E entity);

    E update(E newOne);

    /**
     * @param id
     * @return
     * @throws NotFoundException with value of entity.not.found
     */
    E getById(String id);

    Optional<E> getOptionalById(String id);

    Page<E> getPage(Pageable pageable);

    /**
     * use only if there is reasonable number of entities
     *
     * @param sort spring data sort object
     * @return entity list
     */
    List<E> getSortedList(Sort sort);

    List<E> getList();

    void deleteById(String id);

    Page<E> getRoots(Pageable pageable);

    Page<E> getChildren(String parentId, Pageable pageable);

    E getParent(String childId);

}
