package com.wyf.gulimall.auth.feign;


import com.wyf.gulimall.auth.vo.SocialUser;
import com.wyf.gulimall.auth.vo.UserLoginVo;
import com.wyf.gulimall.auth.vo.UserRegisterVo;
import com.wyf.gulimall.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: zhangshuaiyin
 * @createTime: 2021-04-22 17:10
 **/
@FeignClient("gulimall-member")
public interface MemberFeignService {

    @PostMapping(value = "/member/member/register")
    R register(@RequestBody UserRegisterVo vo);

    @PostMapping(value = "/member/member/login")
    R login(@RequestBody UserLoginVo vo);

    @PostMapping(value = "/member/member/oauth2/login")
    R oauthLogin(@RequestBody SocialUser socialUser) throws Exception;

    @PostMapping("/web/member/member/gitee-login")
    R giteeLogin(@RequestParam("giteeInfo") String giteeInfo);
}
