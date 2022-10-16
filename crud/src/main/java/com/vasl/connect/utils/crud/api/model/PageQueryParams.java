package com.vasl.connect.utils.crud.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class PageQueryParams implements Cloneable{

    @ApiModelProperty(example = "1", value = "start from 0")
    private Integer pageNumber = 0;
    @ApiModelProperty(example = "10", value = "must be greater than 0")
    private Integer pageSize = 10;
    @ApiModelProperty(example = "ASC", value = "ASC=ascending ,DESC=descending")
    private Sort sort;
    @ApiModelProperty(example = "[key1,key2]", value = "sort will be based on these words")
    private Set<String> sortKey;
}
