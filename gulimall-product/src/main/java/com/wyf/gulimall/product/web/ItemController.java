package com.wyf.gulimall.product.web;

import com.wyf.gulimall.product.entity.vo.SkuItemVo;
import com.wyf.gulimall.product.service.SkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {

    @Autowired
    private SkuInfoService skuInfoService;

    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable("skuId") Long skuId, Model model) {
        SkuItemVo skuItemVo=skuInfoService.item(skuId);
        model.addAttribute("item", skuItemVo);
        return "item";
    }

}
