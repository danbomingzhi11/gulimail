package com.wyf.gulimall.product.feign;

import com.wyf.gulimall.to.es.SkuEsModel;
import com.wyf.gulimall.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("gulimall-search")
public interface SearchFeignService {
    @PostMapping("/search/save/product")
    R saveProductAsIndices(@RequestBody List<SkuEsModel> skuEsModels);
}
