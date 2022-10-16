package com.vasl.connect.utils.crud.dal;

import com.vasl.connect.utils.crud.api.model.PageQueryParams;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class RepositoryUtils {
    public static Pageable getPageableFromPageQueryParams(PageQueryParams pageQueryParams){
        String[] queryParams = (Objects.isNull(pageQueryParams.getSortKey()) || (pageQueryParams.getSortKey().size() == 0)) ? null : pageQueryParams.getSortKey().toArray(new String[0]);
        String sortBy = Objects.isNull(pageQueryParams.getSort())? null : pageQueryParams.getSort().name();
        if (!Objects.isNull(queryParams) && !Objects.isNull(sortBy))
            return PageRequest.of(
                    pageQueryParams.getPageNumber(),
                    pageQueryParams.getPageSize(),
                    Sort.Direction.fromString(sortBy),
                    queryParams
            );
        else
            return PageRequest.of(
                    pageQueryParams.getPageNumber(),
                    pageQueryParams.getPageSize()
            );

    }
}
