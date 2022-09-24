package com.wyf.gulimall.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wyf.gulimall.auth.feign.MemberFeignService;
import com.wyf.gulimall.constant.AuthServerConstant;
import com.wyf.gulimall.utils.GitteUtils;
import com.wyf.gulimall.utils.HttpUtils;
import com.wyf.gulimall.utils.R;
import com.wyf.gulimall.vo.MemberResponseVo;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/web/oauth2")
public class OAuth2Controller {
    @Resource
    private MemberFeignService memberFeignService;

    @GetMapping("/gitee/success")
    public String gitee(@RequestParam("code") String code, HttpSession session) throws Exception {
        // 准备请求参数
        Map<String,String> params = new HashMap<>();
        params.put("client_id","f12672f5be6259afc2ccae56fd05eec01bd38e1969357c5dfa0eb5b60ae57e6d");
        params.put("redirect_uri","http://auth.gulimall.com/web/oauth2/gitee/success");
        params.put("client_secret","0b714cc01897f6a53ef879aa1d9517bb3eee69d3d36af43130115b6d10ee6a65");
        params.put("code",code);
        params.put("grant_type","authorization_code");

        // 获取accesstoken
        HttpResponse response = GitteUtils.doPost("https://gitee.com", "/oauth/token", "post", null, null, params);

        if (response.getStatusLine().getStatusCode() == 200) {
            // 说明获取到了
            // 取出返回数据
            String giteeInfo = EntityUtils.toString(response.getEntity());
            R r = memberFeignService.giteeLogin(giteeInfo);

            if (r.getCode() == 0) {
                String jsonString = JSON.toJSONString(r.get("mem"));
                System.out.println("----------------"+jsonString);
                MemberResponseVo memberResponseVo = JSON.parseObject(jsonString, new TypeReference<MemberResponseVo>() {
                });
                System.out.println("----------------"+memberResponseVo);
                session.setAttribute(AuthServerConstant.LOGIN_USER, memberResponseVo);
                return "redirect:http://gulimall.com/";
            }

        }else{
            return "redirect:http://auth.gulimall.com/login.html";
        }

        return "redirect:http://auth.gulimall.com/login.html";
    }
}
