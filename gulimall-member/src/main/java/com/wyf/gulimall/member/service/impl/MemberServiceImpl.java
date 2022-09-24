package com.wyf.gulimall.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wyf.gulimall.member.dao.MemberLevelDao;
import com.wyf.gulimall.member.entity.MemberLevelEntity;
import com.wyf.gulimall.member.exception.PhoneException;
import com.wyf.gulimall.member.exception.UsernameException;
import com.wyf.gulimall.member.service.MemberLevelService;
import com.wyf.gulimall.member.vo.MemberUserLoginVo;
import com.wyf.gulimall.member.vo.MemberUserRegisterVo;
import com.wyf.gulimall.utils.GitteUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.Query;

import com.wyf.gulimall.member.dao.MemberDao;
import com.wyf.gulimall.member.entity.MemberEntity;
import com.wyf.gulimall.member.service.MemberService;

import javax.annotation.Resource;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Resource
    private MemberLevelDao memberLevelDao;

    @Autowired
    private MemberLevelService memberLevelService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void register(MemberUserRegisterVo vo) {

        MemberEntity memberEntity = new MemberEntity();

        //设置默认等级
        MemberLevelEntity levelEntity = memberLevelDao.getDefaultLevel();
        memberEntity.setLevelId(levelEntity.getId());

        //设置其它的默认信息
        //检查用户名和手机号是否唯一。感知异常，异常机制
        checkPhoneUnique(vo.getPhone());
        checkUserNameUnique(vo.getUserName());

        memberEntity.setNickname(vo.getUserName());
        memberEntity.setUsername(vo.getUserName());
        //密码进行MD5加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(vo.getPassword());
        memberEntity.setPassword(encode);
        memberEntity.setMobile(vo.getPhone());
        memberEntity.setGender(0);
        memberEntity.setCreateTime(new Date());

        //保存数据
        this.baseMapper.insert(memberEntity);
    }

    @Override
    public void checkPhoneUnique(String phone) throws PhoneException {

        Integer phoneCount = this.baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("mobile", phone));

        if (phoneCount > 0) {
            throw new PhoneException();
        }

    }

    @Override
    public void checkUserNameUnique(String userName) throws UsernameException {

        Integer usernameCount = this.baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("username", userName));

        if (usernameCount > 0) {
            throw new UsernameException();
        }
    }

    @Override
    public MemberEntity giteeLogin(String giteeInfo) throws Exception {
        // 拿到accesstoken，获取用户基本信息
        JSONObject baseJson = JSON.parseObject(giteeInfo);
        Map<String, String> params = new HashMap<>();
        String accessToken = baseJson.getString("access_token");
        String expiresIn = baseJson.getString("expires_in");
        params.put("access_token", accessToken);
        HttpResponse response = GitteUtils.doGet("https://gitee.com", "/api/v5/user", "get", null, params);

        String s = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = JSON.parseObject(s);
        String id = jsonObject.getString("id");


        MemberEntity member = this.getOne(new QueryWrapper<MemberEntity>().eq("social_uid", "gitee" + "_" + id));
        if (response.getStatusLine().getStatusCode() == 200) {

            if (member != null) {
                // 说明已经注册过，更新令牌、令牌过期时间
                MemberEntity newMember = new MemberEntity();
                newMember.setId(member.getId());
                newMember.setAccessToken(accessToken);
                newMember.setExpiresIn(expiresIn);
                this.updateById(member);
            } else {
                // 第一次登录，需要注册
                MemberEntity newMember = new MemberEntity();
                newMember.setSocialUid("gitee" + "_" + id);
                newMember.setNickname(jsonObject.getString("name"));
                newMember.setAccessToken(accessToken);
                newMember.setExpiresIn(expiresIn);
                newMember.setBirth(new Date());
                newMember.setCreateTime(new Date());
                this.save(newMember);
            }
        }
        return member;
    }

    @Override
    public MemberEntity login(MemberUserLoginVo vo) {

        String loginacct = vo.getLoginAccount();
        String password = vo.getPassword();

        //1、去数据库查询 SELECT * FROM ums_member WHERE username = ? OR mobile = ?
        MemberEntity memberEntity = this.baseMapper.selectOne(new QueryWrapper<MemberEntity>()
                .eq("username", loginacct).or().eq("mobile", loginacct));

        if (memberEntity == null) {
            //登录失败
            return null;
        } else {
            //获取到数据库里的password
            String password1 = memberEntity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //进行密码匹配
            boolean matches = passwordEncoder.matches(password, password1);
            if (matches) {
                //登录成功
                return memberEntity;
            }
        }

        return null;
    }

}