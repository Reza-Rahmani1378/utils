package com.vasl.connect.utils.crud.service.remote.model;

import lombok.Data;

@Data
public class PgsbResult<E> {

    private E data;
    private PgsbStatus status;

}
