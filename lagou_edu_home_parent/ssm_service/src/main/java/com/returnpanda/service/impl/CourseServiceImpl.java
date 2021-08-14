package com.returnpanda.service.impl;

import com.returnpanda.dao.CourseMapper;
import com.returnpanda.domain.Course;
import com.returnpanda.domain.CourseVO;
import com.returnpanda.domain.Teacher;
import com.returnpanda.service.CourseService;
import com.returnpanda.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    //多条件查询
    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        List<Course> list = courseMapper.findCourseByCondition(courseVO);

        return list;
    }

    //新增课程信息和讲师信息
    @Override
    public void saveCourseOrTeacher(CourseVO courseVO){
/*
        分别封装Course和Teacher 调用dao,获取Course添加成功后新记录的id值
*/

        //Course封装
        Course course = new Course();
        BeanUtils.copyProperties(courseVO,course);//名称相同的值封装到course

        //补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        //保存课程
        courseMapper.saveCourse(course);
        //获取新插入数据的id(课程id)
        int id = course.getId();

        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVO,teacher);//名称相同的值封装到teacher
        //补全讲师信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);

        //保存讲师信息
        courseMapper.saveTeacher(teacher);

    }

    //根据id查询课程信息
    @Override
    public CourseVO findCourseById(int id) {
        CourseVO courseById = courseMapper.findCourseById(id);
        return courseById;
    }

    //修改课程信息
    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) {
        /*
            分别封装Course和Teacher 调用dao
        */
        //Course封装
        Course course = new Course();
        BeanUtils.copyProperties(courseVO,course);

        //补全课程信息
        Date date = new Date();
        course.setUpdateTime(date);
        //修改课程信息
        courseMapper.updateCourse(course);

        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVO,teacher);
        //补全讲师信息
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);
        //修改讲师信息
        courseMapper.updateTeacher(teacher);
    }

    //课程状态管理
    @Override
    public void updateCourseStatus(int courseid,int status) {
        Course course = new Course();
        //补全课程信息
        course.setId(courseid);
        course.setStatus(status);
        Date date = new Date();
        course.setUpdateTime(date);
        courseMapper.updateCourseStatus(course);
    }


}
