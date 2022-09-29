package com.example.huahuoimg.mapper;

import com.example.huahuoimg.config.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
* @author Administrator
* @description 针对表【admin】的数据库操作Mapper
* @createDate 2022-09-29 13:33:10
* @Entity com.example.huahuoimg.pojo.Admin
*/@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("select * from admin where username = #{username}")
    public Admin selectByUserName(String username);

}




