package com.wyf.gulimall.product.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置静态资源映射
 **/
//@Component
//class WebMvcConfig implements WebMvcConfigurer {
//
//    /**
//     * 添加静态资源文件，外部可以直接访问地址
//     *
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        //此处还可继续增加目录。。。。
//    }
//
//}