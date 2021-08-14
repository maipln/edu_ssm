package com.returnpanda.controller;

import com.returnpanda.domain.Course;
import com.returnpanda.domain.CourseLesson;
import com.returnpanda.domain.CourseSection;
import com.returnpanda.domain.ResponseResult;
import com.returnpanda.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    //根据课程id查询关联的章节信息和章节关联的课时信息
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(@RequestParam Integer courseId){
        List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "据课程id查询关联的章节信息和章节关联的课时信息成功", sectionList);

        return responseResult;
    }

    //回显章节对应的课程信息
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "回显章节对应的课程信息成功", course);
        return responseResult;
    }


    //保存和修改章节信息
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        ResponseResult responseResult;
        if (courseSection.getId()==null){
            courseContentService.saveSection(courseSection);
            responseResult = new ResponseResult(true, 200, "保存章节信息成功", null);
        }else {
            courseContentService.updateSection(courseSection);
            responseResult = new ResponseResult(true, 200, "修改章节信息成功", null);
        }
        return responseResult;
    }


    //修改章节状态
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam("id") int id, @RequestParam("status") int status){
        courseContentService.updateSectionStatus(id,status);
        //数据响应
        Map<Object, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "修改章节状态成功",map);
        return responseResult;
    }


    //保存和修改课时信息
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson){
        ResponseResult responseResult;
        if (courseLesson.getId()==null){
            courseContentService.saveLesson(courseLesson);
            responseResult = new ResponseResult(true, 200, "保存课时信息成功", null);
        }else {
            courseContentService.updateLesson(courseLesson);
            responseResult = new ResponseResult(true, 200, "更新课时信息成功", null);
        }

        return responseResult;
    }


    //修改课时状态
    @RequestMapping("/updateLessonStatus")
    public ResponseResult updateLessonStatus(@RequestParam("id") int id, @RequestParam("status") int status){
        courseContentService.updateLessonStatus(id,status);
        //数据响应
        Map<Object, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "修改课时状态成功",map);
        return responseResult;
    }
}
