package com.returnpanda.dao;

import com.returnpanda.domain.Course;
import com.returnpanda.domain.CourseVO;
import com.returnpanda.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    //多条件查询课程
    public List<Course> findCourseByCondition(CourseVO courseVO);

    //新增课程信息
    public void saveCourse(Course course);

    //新增讲师信息
    public void saveTeacher(Teacher teacher);

    //根据id查询课程信息
    public CourseVO findCourseById(Integer id);

    //更新课程信息
    public void updateCourse(Course course);

    //更新讲师信息
    public void updateTeacher(Teacher teacher);

    //课程状态管理
    public void updateCourseStatus(Course course);

}
