package com.returnpanda.domain;

import lombok.Data;

import java.util.List;

@Data
public class RoleMenuVO {
    //在给角色分配菜单使用,接收前端传来的值
    private Integer roleId;
    private List<Integer> menuIdList;
}
