package com.example.huahuoimg.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.huahuoimg.common.ImgR;
import com.example.huahuoimg.mapper.ImgsMapper;
import com.example.huahuoimg.pojo.Imgs;
import com.example.huahuoimg.service.ImgsService;
import com.example.huahuoimg.service.QiniuService;
import com.example.huahuoimg.common.R;
import com.example.huahuoimg.utils.UrlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/img")
@Slf4j
@Api("图片控制")
public class ImgController {
    private String prefix = "http://riwbp7bw1.hn-bkt.clouddn.com/";
    private String form = "&250px";
    @Autowired
    private QiniuService qiniuService;
    @Autowired
    ImgsService imgsService;
    @Autowired
    ImgsMapper imgsMapper;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    @ApiParam("支持批量")
    public R<Object> uploadImg(@RequestParam("file") List<MultipartFile> files, HttpServletRequest request) {

               String MdUrl = null;
                List<ImgR> list = new ArrayList<>();

        for (MultipartFile file : files) {
            ImgR imgR = new ImgR();
        if (file.isEmpty()) {
            return R.error("文件为空，请重新上传");}
            try {

                MdUrl=null;
                String url = null;
                String fileUrl = qiniuService.saveImage(file);
                url = "http://" + fileUrl;
                MdUrl = "![img](" + url + ")";
                Imgs imgs = new Imgs();
                imgs.setUrl(url);
                imgs.setMrakdownUrl(MdUrl);
                imgs.setCreateTime(LocalDateTime.now());
                imgs.setName(UrlUtil.removePrefix(url, prefix));
                imgs.setFileKey(UrlUtil.removePrefix(url, prefix));
                imgs.setUpdateTime(LocalDateTime.now());
                imgs.setMiniurl(url + form);
                imgsService.save(imgs);
                imgR.setCode("200");
                imgR.setFileName(UrlUtil.removePrefix(url, prefix));
                imgR.setUrl(url);
                if(imgR.getUrl().equals("http://null"))
                imgR.setCode("error");
                list.add(imgR);
            } catch (IOException e) {
                e.printStackTrace();
                imgR.setCode("500");
            }
        }
        return R.success((Object) list);
    }


    @PostMapping("/delete")
    @ApiOperation("文件删除")
    @ApiParam("支持批量")
    public R<String> delete(@RequestParam List<String> filekeys) {
        for (String filekey : filekeys) {
            qiniuService.deleteImg(filekey);
            imgsMapper.deleteByName(filekey);
        }
        return R.success("删除成功！");
    }

    @PostMapping("/update")
    public R<String> update(@RequestParam Integer id, @RequestParam String name) {
        Imgs img = imgsService.getById(id);
        img.setName(name);
        img.setUpdateTime(LocalDateTime.now());
        imgsService.updateById(img);
        return R.success("修改成功！");
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public R<Page> page(int page, int pageSize) {
        Page<Imgs> imgsPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Imgs> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Imgs::getUpdateTime);
        imgsService.page(imgsPage,queryWrapper);
        return R.success(imgsPage);
    }
}


