package com.wyf.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.member.exception.PhoneException;
import com.wyf.gulimall.member.exception.UsernameException;
import com.wyf.gulimall.member.vo.MemberUserLoginVo;
import com.wyf.gulimall.member.vo.MemberUserRegisterVo;
import com.wyf.gulimall.utils.PageUtils;

import com.wyf.gulimall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author wyf
 * @email 
 * @date 2022-08-03 09:09:47
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
    /**
     * 用户注册
     * @param vo
     */
    void register(MemberUserRegisterVo vo);

    /**
     * 判断邮箱是否重复
     * @param phone
     * @return
     */
    void checkPhoneUnique(String phone) throws PhoneException;

    /**
     * 判断用户名是否重复
     * @param userName
     * @return
     */
    void checkUserNameUnique(String userName) throws UsernameException;

    /**
     * 用户登录
     * @param vo
     * @return
     */
    MemberEntity login(MemberUserLoginVo vo);

    /**
     * gitte 登陆
     * @param giteeInfo
     * @throws Exception
     * @return
     */
    MemberEntity giteeLogin(String giteeInfo) throws Exception;


}

