package com.example.huahuoimg.service;

import com.example.huahuoimg.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author Administrator
* @description 针对表【admin】的数据库操作Service
* @createDate 2022-09-29 13:33:10
*/
public interface AdminService extends IService<Admin> {
    public Map<String,Object> login(Admin admin);
}
