package com.shenzc.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.shenzc.utils.FastJsonUtils;
import com.shenzc.vo.AliyunVo;
import com.shenzc.vo.TemplateParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageService {

    @Autowired
    private AliyunVo aliyunVo;

    /***
     * 阿里云短信服务
     * @param code
     * @param phone
     * @return
     */
    public boolean sendMessage(Map<String,Object> map){
        DefaultProfile profile = DefaultProfile.getProfile(aliyunVo.getRegionId(), aliyunVo.getAccessKeyId(), aliyunVo.getAccessSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(aliyunVo.getSysDomain());
        request.setSysVersion(aliyunVo.getSysVersion());
        request.setSysAction(aliyunVo.getSysAction());
        request.putQueryParameter("RegionId", aliyunVo.getRegionId());
        request.putQueryParameter("PhoneNumbers", (String) map.get("phone"));
        request.putQueryParameter("SignName", aliyunVo.getSignName());
        request.putQueryParameter("TemplateCode", aliyunVo.getTemplateCode());
        TemplateParamVo templateParamVo = new TemplateParamVo("code",(String)map.get("code"));
        request.putQueryParameter("TemplateParam", FastJsonUtils.convertObjectToJSON(templateParamVo));
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
