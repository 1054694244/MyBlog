package com.shenzc.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.shenzc.utils.DateUtils;
import com.shenzc.vo.AliyunVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OssService {

    /**
     * 文件上传DateUtils
     * @param file
     */
    public Map<String, String> upload(MultipartFile file){
        Map<String,String> map = new HashMap<>();
        String uploadUrl = "";
        String fileName = "";
        try{
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSSClient ossClient = new OSSClient(AliyunVo.ENDPOINT, AliyunVo.ACCESSKEYID, AliyunVo.ACCESSSECRET);
            if (!ossClient.doesBucketExist(AliyunVo.BUCKETNAME)) {
                //创建bucket
                ossClient.createBucket(AliyunVo.BUCKETNAME);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(AliyunVo.BUCKETNAME, CannedAccessControlList.PublicRead);
            }
            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = DateUtils.getDateStr(new Date(), "yyyy/MM/dd");
            //文件名：uuid.扩展名
            fileName = file.getOriginalFilename();
            String type = fileName.substring(fileName.lastIndexOf("."));
            String name = UUID.randomUUID().toString();
            name = name + type;
            String fileUrl = AliyunVo.FILEHOST + "/" +filePath + "/" + name;
            //文件上传至阿里云
            ossClient.putObject(AliyunVo.BUCKETNAME, fileUrl, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //获取url地址
            uploadUrl = "http://" + AliyunVo.BUCKETNAME + "." + AliyunVo.ENDPOINT + "/" + fileUrl;
        }catch (Exception e){
            throw new RuntimeException("OSS文件上传失败",e);
        }
        map.put("url",uploadUrl);
        map.put("name",fileName);
        return map;
    }

    //当重新上传时，删除已经上传的文件
    public void delete(String uploadUrl) {
        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSSClient ossClient = new OSSClient(AliyunVo.ENDPOINT, AliyunVo.ACCESSKEYID, AliyunVo.ACCESSSECRET);
            if (!ossClient.doesBucketExist(AliyunVo.BUCKETNAME)) {
                //创建bucket
                ossClient.createBucket(AliyunVo.BUCKETNAME);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(AliyunVo.BUCKETNAME, CannedAccessControlList.PublicRead);
            }
            // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
            ossClient.deleteObject(AliyunVo.BUCKETNAME, uploadUrl);
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (Exception e) {
            throw new RuntimeException("删除文件失败"+uploadUrl,e);
        }
    }

}
