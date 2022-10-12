package com.vasl.connect.utils.crud.dal;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

@Data
public class Entity implements Cloneable{
    @Id
    private String id;
    @Version
    protected Long version;
    protected Boolean deleted=false;
    @CreatedDate
    protected LocalDateTime createdDate;
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;
    private String parentId;
    private Long childrenCount;

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
}