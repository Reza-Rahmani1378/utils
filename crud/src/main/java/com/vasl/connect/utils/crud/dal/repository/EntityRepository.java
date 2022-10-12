package com.vasl.connect.utils.crud.dal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

public interface EntityRepository <E>{

    @Query("{ $or : [ { \"parentId\" : { $exists : false } }, { \"parentId\" : {$in:[null, \"\", \" \"]} }] }")
    Page<E> findAllRoots(Pageable pageable);

    Page<E> findAllByParentId(String parentId, Pageable pageable);
}
