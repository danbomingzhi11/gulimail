package com.wyf.gulimall.thirdparty.controller;

import com.wyf.gulimall.thirdparty.component.SmsComponent;
import com.wyf.gulimall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/sms")
public class SmsController {
    @Autowired
    private SmsComponent smsComponent;

    @GetMapping
    public R smsSendCode(@RequestParam("phone") String phone,@RequestParam("code") String code ){
//        smsComponent.sendSmsCode(phone, code);
        return R.ok();
    }
}
