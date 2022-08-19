package com.wyf.gulimall.product.web;

import com.wyf.gulimall.product.entity.CategoryEntity;
import com.wyf.gulimall.product.entity.vo.Catalog2Vo;
import com.wyf.gulimall.product.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "商城首页API", tags = {"商城首页API"})
@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "index.html"})
    public String getIndex(Model model) {
        //获取所有的一级分类
        List<CategoryEntity> catagories = categoryService.getLevel1Catagories();
        model.addAttribute("catagories", catagories);
        return "index";
    }

    // 根据一级分类 查询二级三级分类并且返回
    @ResponseBody
    @GetMapping("/index/json/catalog.json")
    public Map<String, List<Catalog2Vo>> getCategoryJson() {
        Map<String, List<Catalog2Vo>> map = categoryService.getCatelogJson();
        return map;
    }
}
