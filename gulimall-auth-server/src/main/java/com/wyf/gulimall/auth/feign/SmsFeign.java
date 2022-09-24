package com.wyf.gulimall.auth.feign;

import com.wyf.gulimall.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("gulimall-third-party")
public interface SmsFeign {
    @GetMapping("/sms")
    R smsSendCode(@RequestParam("phone") String phone, @RequestParam("code") String code );

}
