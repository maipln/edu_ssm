package com.returnpanda.domain;

import lombok.Data;

import java.util.Date;
/**
 * 课时类
 * */
@Data
public class CourseLesson {

    //主键
    private Integer id;

    //课程id
    private Integer courseId;

    //章节id
    private Integer sectionId;

    //课时主题
    private String theme;

    //课时时长
    private Integer duration;

    //是否免费
    private Integer isFree;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

    //是否删除
    private Integer isDel;

    //排序
    private Integer orderNum;

    //状态
    private Integer status;


}
