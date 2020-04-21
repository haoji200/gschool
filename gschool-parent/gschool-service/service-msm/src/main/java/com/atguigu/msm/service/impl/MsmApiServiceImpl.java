package com.atguigu.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.msm.service.MsmApiService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * MsmApiServiceImpl
 *
 * @Author: wd
 * @CreateTime: 2020-04-20
 * @Description:
 */
@Service
public class MsmApiServiceImpl implements MsmApiService {
    @Override
    public boolean sendCode(String phone, String code) {
        //创建初始化对象
        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4FmbyacASjjMRJ658E3Z", "woYAqk0ML3wRJ3GaTbdtCl34Fczkbi");
        IAcsClient client = new DefaultAcsClient(profile);
        //创建request对象
        CommonRequest request = new CommonRequest();
        //向request设置相关参数
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("PhoneNumbers", phone); //手机号
        request.putQueryParameter("SignName", "谷粒商城在线教育网站"); //签名名称
        request.putQueryParameter("TemplateCode", "SMS_188565105");  //模版CODE
        // {'code','6759'}
        Map<String,Object> param = new HashMap<>();
        param.put("number",code);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
        //调用方法发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}