package com.example.huahuoimg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huahuoimg.pojo.Admin;
import com.example.huahuoimg.service.AdminService;
import com.example.huahuoimg.mapper.AdminMapper;
import com.example.huahuoimg.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【admin】的数据库操作Service实现
 * @createDate 2022-09-29 13:33:10
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Map<String, Object> login(Admin admin) {
        Admin one = adminMapper.selectByUserName(admin.getUsername());
        Map<String, Object> map = new HashMap<>();
        if (one == null) {
            map.put("user", null);
            map.put("token", "username_error");
            return map;
        }
      else {
           String password = admin.getPassword();
           if(one.getPassword().equals(password))
           {
            String token = JWTUtils.createToken(one.getUsername().toString());
            map.put("user", one);
            map.put("token", token);
            return map;}
           else
           {
               map.put("user",null);
               map.put("token","password_error");
               return map;
           }
      }

    }
}




