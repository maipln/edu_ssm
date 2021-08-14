package com.returnpanda.controller;

import com.returnpanda.domain.Course;
import com.returnpanda.domain.CourseSection;
import com.returnpanda.domain.CourseVO;
import com.returnpanda.domain.ResponseResult;
import com.returnpanda.service.CourseContentService;
import com.returnpanda.service.CourseService;
import com.returnpanda.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;



    //多条件查询课程
    @RequestMapping("/findCourseByCondition")
    private ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO) {
        List<Course> list = courseService.findCourseByCondition(courseVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "多条件查询课程成功", list);
        return responseResult;
    }

    //课程图片上传
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        // 判断接收到的上传文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }
        //获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");

        //截取获取的路径(第零个到"ssm-web"之前的路径)
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));

        //获取源文件名
        String originalFilename = file.getOriginalFilename();

        //生成新文件名
        String newFileName = UUIDUtils.getUUID() + "_" + originalFilename;

        //文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);
        //如果目录不存在就创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }
        //正式上传文件
        file.transferTo(filePath);

        //将文件名和文件路径返回,进行响应回显
        Map<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "上传成功", map);
        return responseResult;


    }

    //新增课程信息和讲师信息(新增和修改)
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO){
        ResponseResult responseResult=null;
        if(courseVO.getId()==null){
            courseService.saveCourseOrTeacher(courseVO);
            responseResult = new ResponseResult(true, 200, "新增课程信息和讲师信息成功", null);
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            responseResult = new ResponseResult(true, 200, "修改课程信息和讲师信息成功", null);
        }

        return responseResult;
    }


    //根据id查询课程信息
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVO courseVO = courseService.findCourseById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "根据课程查询ID信息成功", courseVO);
        return responseResult;
    }


    //课程状态管理
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam Integer id,@RequestParam Integer status){
        courseService.updateCourseStatus(id,status);
        //响应
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "课程状态变更成功", map);
        return responseResult;
    }





}