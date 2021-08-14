package com.returnpanda.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class UserVo {

    private Integer currentPage;
    private Integer pageSize;
    private String username;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startCreateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCreateTime;
    private List<Integer> roleIdList;
    private Integer userId;

}
