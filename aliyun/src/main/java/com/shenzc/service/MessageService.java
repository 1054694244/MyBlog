package com.shenzc.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.shenzc.vo.AliyunVo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageService {


    /***
     * 阿里云短信服务
     * @return
     */
    public boolean sendMessage(Map<String,Object> map){
        DefaultProfile profile = DefaultProfile.getProfile(AliyunVo.REGIONID, AliyunVo.ACCESSKEYID, AliyunVo.ACCESSSECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(AliyunVo.SYSDOMAIN);
        request.setSysVersion(AliyunVo.SYSVERSION);
        request.setSysAction(AliyunVo.SYSACTION);
        request.putQueryParameter("RegionId", AliyunVo.REGIONID);
        request.putQueryParameter("PhoneNumbers", (String) map.get("phone"));
        request.putQueryParameter("SignName", AliyunVo.SIGNNAME);
        request.putQueryParameter("TemplateCode", AliyunVo.TEMPLATECODE);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",(String)map.get("code"));
        request.putQueryParameter("TemplateParam", jsonObject.toJSONString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            if (response.getHttpStatus()!=200){
                throw new RuntimeException("短信发送失败");
            }
        } catch (ServerException e) {
            throw new RuntimeException("服务端异常",e);
        } catch (ClientException e) {
            throw new RuntimeException("客户端异常",e);
        }
        return true;
    }

}
