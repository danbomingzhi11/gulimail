package com.wyf.gulimall.thirdparty.component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.client.utils.StringUtils;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: zhangshuaiyin
 * @createTime: 2021-04-22 09:39
 **/
@ConfigurationProperties(prefix = "spring.cloud.alicloud.sms")
@Data
@Component
public class SmsComponent {

    //aliyuncs的参数
    private String accessKeyID;
    private String accessKeySecret;
    //您的申请签名
    private String signName;
    //你的模板
    private String templateCode;

    public void sendSmsCode(String phone, String code) {
        //判断手机号是否为空
        if (StringUtils.isEmpty(phone)) {
            System.out.println("手机号为空");
        }else {
            DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyID, accessKeySecret);
            IAcsClient client = new DefaultAcsClient(profile);

            //设置相关固定的参数
            CommonRequest request = new CommonRequest();
            //提交方式
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");

            //设置发送相关的参数
            //手机号
            request.putQueryParameter("PhoneNumbers", phone);
            //申请的阿里云的 签名名称
            request.putQueryParameter("SignName", signName);
            //申请的阿里云的 模板code
            request.putQueryParameter("TemplateCode", templateCode);

            HashMap<String, Object> params = new HashMap<>();
            params.put("code",code);
            //验证码数据，转换json数据传递
            request.putQueryParameter("TemplateParam", JSONObject.toJSONString(params));

            try {
                //最终发送
                CommonResponse response = client.getCommonResponse(request);
                System.out.println("发送成功");
                //判断成功还是失败
            }catch (ServerException e) {
                e.printStackTrace();
                System.out.println("发送失败1");
            } catch (ClientException e) {
                e.printStackTrace();
                System.out.println("发送失败2");
            }
        }
    }

}
