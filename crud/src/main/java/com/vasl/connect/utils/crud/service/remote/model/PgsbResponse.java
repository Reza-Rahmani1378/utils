package com.vasl.connect.utils.crud.service.remote.model;

import lombok.Data;

@Data
public class PgsbResponse<E> {

    private PgsbResult<E> result;
    private PgsbStatus status;

}
