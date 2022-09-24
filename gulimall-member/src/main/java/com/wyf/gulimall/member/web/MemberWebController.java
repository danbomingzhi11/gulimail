package com.wyf.gulimall.member.web;

import com.wyf.gulimall.member.entity.MemberEntity;
import com.wyf.gulimall.member.service.MemberService;
import com.wyf.gulimall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/web/member/member")
public class MemberWebController {
    @Autowired
    private MemberService memberService;


    @PostMapping("/gitee-login")
    public R giteeLogin(@RequestParam("giteeInfo") String giteeInfo) throws Exception {
        MemberEntity memberEntity = memberService.giteeLogin(giteeInfo);
        return R.ok().put("mem", memberEntity);
    }

}
