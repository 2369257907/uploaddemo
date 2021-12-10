package com.uploaddemo.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UploadService {

    /**
     * 上传文件的存储目录，形如"E://uploadFile/"
     */
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    /**
     * 返回的网页链接的前缀，形如"http://localhost:8779"
     */
    @Value("${file.staticRootPath}")
    private String staticRootPath;

    /**
     * 静态映射路径，形如"/uploadimg"
     */
    @Value("${file.staticPatternPath}")
    private String staticPatternPath;


    /**
     * 上传img
     *
     * @param multipartFile 上传的文件
     * @param dir           存储文件的目录名
     * @return {@link String} 最终返回可访问的上传文件的链接
     */
    public HashMap uploadImg(MultipartFile multipartFile,String dir){
        try{
            //1.获取上传文件的文件名
            String OriginalFilename = multipartFile.getOriginalFilename();
            //2.获取文件名的后缀
            String fileSuffix = OriginalFilename.substring(OriginalFilename.lastIndexOf("."));
            //3.生成唯一的文件名
            String newFilename = UUID.randomUUID().toString()+fileSuffix;
            //4.获取日期目录
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String datePath = dateFormat.format(new Date());
            //5.指定文件上传以后的目录,如果目录不存在，则先创建
            File targetPath = new File(uploadFolder+dir,datePath);
            if (!targetPath.exists()){
                targetPath.mkdirs();
            }
            //6.指定文件上传以后的服务器的完整文件名
            File targetFilename = new File(targetPath,newFilename);
            //7.文件上传到指定目录中
            multipartFile.transferTo(targetFilename);
            //8. 可访问的上传文件的链接，形如"http://localhost:8779//uploadimg/bbb/2021/12/09/4143c1fa-a96e-4966-979b-6122aa80a155.gif"
            String fileUrl = staticRootPath+staticPatternPath+"/"+dir +"/"+datePath+"/"+newFilename;

            HashMap<String,Object> map = new HashMap<>();
            //可访问的上传文件的链接
            map.put("url",fileUrl);
            //上传文件的大小
            map.put("size",multipartFile.getSize());
            //上传文件名
            map.put("filename",OriginalFilename);
            //服务器中存储文件的路径
            map.put("realPath",uploadFolder+dir +"/"+datePath);

            return map;
        }catch (IOException e){
            e.printStackTrace();
            HashMap<String,Object> map = new HashMap<>(1);
            map.put("msg","上传失败");
            return map;
        }

    }
}
