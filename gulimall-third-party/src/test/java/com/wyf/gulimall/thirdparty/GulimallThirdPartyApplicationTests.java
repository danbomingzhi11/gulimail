package com.wyf.gulimall.thirdparty;

import com.aliyun.oss.OSSClient;
import com.wyf.gulimall.thirdparty.component.SmsComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallThirdPartyApplicationTests {

    @Autowired
    private SmsComponent smsComponent;

    @Resource
    OSSClient ossClient;


    @Test
    public void sendSmsCode() {
        smsComponent.sendSmsCode("19819069411", "6666");
    }

    @Test
    public void testUpload() throws FileNotFoundException {
//        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "oss-cn-beijing.aliyuncs.com";
//        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
//        String accessKeyId = "LTAI4FwvfjSycd1APnuG9bjj";
//        String accessKeySecret = "O6xaxyiWfSIitcOkSuK27ju4hXT5Hl";
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = new FileInputStream("C:\\Users\\lfy\\Pictures\\Camera Roll\\59f2db04139a2.jpg");

        ossClient.putObject("gulimall-hello", "hahaha.jpg", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        System.out.println("上传完成...");
    }
}
