package com.uploaddemo.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OssUploadService {
//    public static void main(String[] args) {
////        String uploadfile = uploadfile(new File("C:\\Users\\86171\\Desktop\\my-weibo\\pom.xml"));
////        System.out.println(uploadfile);
//    }
    public  HashMap<String,Object> uploadfile(MultipartFile multipartFile, String dir) {
        HashMap<String,Object> map = new HashMap<>();
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "xxx";
        String accessKeySecret = "xxx";
        String bucketName = "myproject-oss";
        // 填写文件名。文件名包含路径，不包含Bucket名称。例如exampledir/exampleobject.txt。

        OSS ossClient = null;
        try {
            // 1.创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            // 2.获取文件上传流
            InputStream inputStream = multipartFile.getInputStream();

            // 3.构建日期目录
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String dataPath = dateFormat.format(new Date());

            // 4.获取文件名
            String originalFilename = multipartFile.getOriginalFilename();
            String filename = UUID.randomUUID().toString();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newname = filename+suffix;
            String fileUrl = dir+"/"+dataPath+"/"+newname;
            String url = "https://" +bucketName+"." +endpoint+"/"+fileUrl;
            // 5.文件上传到阿里云服务器
            ossClient.putObject(bucketName, fileUrl, inputStream);
            // 6.返回信息
            map.put("url",url);
            map.put("error",0);
            return map;
        } catch (OSSException | IOException e){
            e.printStackTrace();
            map.put("msg","fail");
            return map;
        } finally {
            // 6.关闭OSSClient。
            ossClient.shutdown();
        }
    }
}