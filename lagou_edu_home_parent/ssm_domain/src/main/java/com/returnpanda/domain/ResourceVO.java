package com.returnpanda.domain;

import lombok.Data;

@Data
public class ResourceVO {

    private Integer currentPage;
    private Integer pageSize;
    private String name;
    private Integer categoryId;
    private String url;
}
