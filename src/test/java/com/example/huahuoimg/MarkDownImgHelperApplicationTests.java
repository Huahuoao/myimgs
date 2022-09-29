package com.example.huahuoimg;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.huahuoimg.utils.UrlUtil.removePrefix;

@SpringBootTest
class MarkDownImgHelperApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
            void test()
    {   String prefix = "pre_";//去掉字符串前缀为pre_、log_
        String str = "pre_name";//测试字符串
        System.out.println(removePrefix(str,prefix));}


}
