package com.example.huahuoimg.mapper;

import com.example.huahuoimg.config.pojo.Imgs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【imgs】的数据库操作Mapper
* @createDate 2022-09-29 11:15:40
* @Entity com.example.markdownimghelper.pojo.Imgs
*/
@Mapper
public interface ImgsMapper extends BaseMapper<Imgs> {

    @Delete("DELETE FROM imgs WHERE file_key = #{name}")
    public void deleteByName(String filekey);
}




