package com.example.huahuoimg.controller;

import com.example.huahuoimg.common.R;
import com.example.huahuoimg.mapper.AdminMapper;
import com.example.huahuoimg.config.pojo.Admin;
import com.example.huahuoimg.service.AdminService;
import com.example.huahuoimg.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @作者 花火
 * @创建日期 2022/9/29 13:08
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminMapper adminMapper;



    @PostMapping("/login")
    public R<Admin> login(@RequestBody Admin admin, HttpServletResponse response) {
        Map<String, Object> map = adminService.login(admin);

        //将token存入Http的header中
        if(map.get("user")==null)
        {
            return R.error(map.get("token").toString());
        }




        response.setHeader(JWTUtils.USER_LOGIN_TOKEN, (String) map.get("token"));
        return R.success((Admin)map.get("user"));   // 返回一个admin
    }
}
