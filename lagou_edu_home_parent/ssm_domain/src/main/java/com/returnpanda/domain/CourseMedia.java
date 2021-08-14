package com.returnpanda.domain;

import lombok.Data;

import java.util.Date;

/**
 * 课程媒体表
 * */
@Data
public class CourseMedia {

    //id
    private Integer id;
    
    //课程id
    private Integer courseId;

    //章节id
    private Integer sectionId;

    //课时id
    private Integer lessonId;

    //封面图URL
    private String coverImageUrl;

    //时长
    private String duration;

    //媒体资源文件对应的EDK
    private String fileEdk;

    //文件大小MB
    private double fileSize;

    //文件名称
    private String fileName;

    //媒体资源文件对应的DK
    private String fileDk;

    //时长，秒数
    private Integer durationNum;

    //媒体资源文件ID
    private String fileId;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //是否删除，0未删除，1删除
    private Integer isDel;


}
