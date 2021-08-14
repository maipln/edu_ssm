package com.returnpanda.service;

import com.returnpanda.domain.Course;
import com.returnpanda.domain.CourseLesson;
import com.returnpanda.domain.CourseSection;

import java.util.List;

public interface CourseContentService {


    //根据课程id查询关联的章节信息和章节关联的课时信息
    public List<CourseSection> findSectionAndLessonByCourseId(Integer  courseId);

    //回显章节对应的课程信息
    public Course findCourseByCourseId(Integer courseId);

    //保存章节信息
    public void saveSection(CourseSection courseSection);

    //修改章节信息
    public void updateSection(CourseSection courseSection);

    //修改章节状态
    public void updateSectionStatus(Integer id,Integer status);

    //保存课程信息
    public void saveLesson(CourseLesson courseLesson);

    //修改课时信息
    public void updateLesson(CourseLesson courseLesson);

    //修改课时状态
    public void updateLessonStatus(Integer id, Integer status);

}
