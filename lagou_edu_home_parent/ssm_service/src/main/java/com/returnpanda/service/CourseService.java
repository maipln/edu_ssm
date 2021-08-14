package com.returnpanda.service;

import com.returnpanda.domain.Course;
import com.returnpanda.domain.CourseVO;

import java.util.List;

public interface CourseService {
    //多条件查询
    public List<Course> findCourseByCondition(CourseVO courseVO);

    //新增课程信息和讲师信息
    public void saveCourseOrTeacher(CourseVO courseVO);

    //根据id查询课程信息
    public CourseVO findCourseById(int id);

    //修改课程信息
    public void updateCourseOrTeacher(CourseVO courseVO);

    //课程状态管理
    public void updateCourseStatus(int courseid,int status);


}
