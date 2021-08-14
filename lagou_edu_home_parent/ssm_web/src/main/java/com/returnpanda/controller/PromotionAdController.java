package com.returnpanda.controller;

import com.github.pagehelper.PageInfo;
import com.returnpanda.domain.PromotionAd;
import com.returnpanda.domain.PromotionAdVO;
import com.returnpanda.domain.ResponseResult;
import com.returnpanda.service.PromotionAdService;
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
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    //分页查询广告信息
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO){
        PageInfo<PromotionAd> allPromotionAdByPage = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "分页查询广告信息",allPromotionAdByPage);
        return responseResult;
    }


    //图片上传
    @RequestMapping("/PromotionAdUpload")
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


    //新建/修改广告信息
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody  PromotionAd promotionAd){
        ResponseResult responseResult=null;

        if (promotionAd.getId()==null){
            promotionAdService.savePromotionAd(promotionAd);
            responseResult = new ResponseResult(true, 200, "新建广告信息", null);
            return responseResult;
        }else {
            promotionAdService.updatePromotionAd(promotionAd);
            responseResult = new ResponseResult(true, 200, "修改广告信息", null);
            return responseResult;
        }

    }


    //根据id查询广告信息
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id){
        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "根据id查询广告信息",promotionAdById);
        return responseResult;
    }


    //广告状态上下线
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(@RequestParam("id") Integer id ,@RequestParam("status") Integer status){
        promotionAdService.updatePromotionAdStatus(id, status);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "广告状态上下线修改成功",map);
        return responseResult;
    }

}
