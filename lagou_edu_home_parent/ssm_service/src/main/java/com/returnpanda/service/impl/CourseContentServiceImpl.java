package com.returnpanda.service.impl;

import com.returnpanda.dao.CourseContentMapper;
import com.returnpanda.domain.Course;
import com.returnpanda.domain.CourseLesson;
import com.returnpanda.domain.CourseSection;
import com.returnpanda.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {
    @Autowired
    private CourseContentMapper courseContentMapper;

    //根据课程id查询关联的章节信息和章节关联的课时信息
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        return list;
    }

    //回显章节对应的课程信息
    @Override
    public Course findCourseByCourseId(Integer courseId) {
        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }


    //保存章节信息
    @Override
    public void saveSection(CourseSection courseSection) {
        //补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);
        courseContentMapper.saveSection(courseSection);
    }

    //修改章节信息
    @Override
    public void updateSection(CourseSection courseSection) {
        //补全信息
        Date date = new Date();
        courseSection.setUpdateTime(date);
        courseContentMapper.updateSection(courseSection);
    }

    //修改章节状态
    @Override
    public void updateSectionStatus(Integer id, Integer status) {
        //封装信息
        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        Date date = new Date();
        courseSection.setUpdateTime(date);
        courseContentMapper.updateSectionStatus(courseSection);

    }


    //保存课时信息
    @Override
    public void saveLesson(CourseLesson courseLesson) {
        System.out.println(courseLesson);
        //补全信息
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);
        courseContentMapper.saveLesson(courseLesson);
    }

    //修改课时信息
    @Override
    public void updateLesson(CourseLesson courseLesson) {
        //补全信息
        Date date = new Date();
        courseLesson.setUpdateTime(date);
        courseContentMapper.updateLesson(courseLesson);
    }


    //修改课时状态
    @Override
    public void updateLessonStatus(Integer id, Integer status) {
        //封装信息
        CourseLesson courseLesson = new CourseLesson();
        courseLesson.setId(id);
        courseLesson.setStatus(status);
        Date date = new Date();
        courseLesson.setUpdateTime(date);
        courseContentMapper.updateLessonStatus(courseLesson);
    }


}
