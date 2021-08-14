package com.returnpanda.controller;

import com.github.pagehelper.PageInfo;
import com.returnpanda.domain.ResponseResult;
import com.returnpanda.domain.Role;
import com.returnpanda.domain.User;
import com.returnpanda.domain.UserVo;
import com.returnpanda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    //分页多条件查询所有用户
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody  UserVo userVo){
        PageInfo<User> pageInfo = userService.findAllUserByPage(userVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "分页多条件查询所有用户",pageInfo);
        return responseResult;
    }


    //修改用户状态
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id,String status){
        userService.updateUserStatus(id,status);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("content",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "修改用户状态",map);
        return responseResult;
    }


    //用户登录(根据用户名查询具体用户信息)
    @RequestMapping("/login")
    public ResponseResult login(@RequestBody  User user, HttpServletRequest request) throws Exception {
        ResponseResult responseResult;
        User user1 = userService.login(user);
        if (user1 != null){
             //保存用户id和access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());
            //将查询出的信息响应给前台
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());

            //将查询出的user,存入map用于登出
            map.put("user",user1);
            responseResult = new ResponseResult(true, 1, "用户登录成功",map);
            return responseResult;
        }else {
            responseResult = new ResponseResult(true, 400, "用户登录失败,请检查用户名或密码",null);
            return responseResult;

        }
    }


    //根据id查询该用户具备的角色信息
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){
        List<Role> list = userService.findUserRoleById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "根据id查询该用户具备的角色信息",list);
        return responseResult;
    }


    //分配角色
    @RequestMapping("/userContextRole")
    public  ResponseResult userContextRole(@RequestBody  UserVo userVo){
        userService.userContextRole(userVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "分配角色",null);
        return responseResult;
    }

    //获取用户权限,进行菜单的动态展
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //获取请求头中的token
        String header_token = request.getHeader("Authorization");
        //获取session中的token
        String session_token = (String) request.getSession().getAttribute("access_token");
        //判断token是否一致
        if(header_token.equals(session_token)){
            //获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            
            //进行菜单信息查询
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        }else {
            ResponseResult responseResult = new ResponseResult(true, 400, "菜单信息查询失败",null);
            return responseResult;
        }

    }




}
