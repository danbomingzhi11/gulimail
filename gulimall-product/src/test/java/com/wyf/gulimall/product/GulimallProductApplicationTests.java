package com.wyf.gulimall.product;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyf.gulimall.product.entity.CategoryEntity;
import com.wyf.gulimall.product.entity.bo.CategoryBo;
import com.wyf.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 1、引入oss-starter
 * 2、配置key，endpoint相关信息即可
 * 3、使用OSSClient 进行相关操作
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallProductApplicationTests {


    @Autowired
    CategoryService categoryService;


    @Test
    public void testCategoryEntity() {




    }


}
