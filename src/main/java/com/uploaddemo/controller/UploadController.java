package com.uploaddemo.controller;

import com.uploaddemo.service.OssUploadService;
import com.uploaddemo.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class UploadController {
    @Autowired
    UploadService uploadService;

    @Autowired
    OssUploadService ossUploadService;
    @PostMapping("/upload/file")
    @ResponseBody
    public HashMap upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request){
        //文件为空，返回"文件有误"
        if (multipartFile.isEmpty()){
            HashMap<String,Object> map = new HashMap<>(1);
            map.put("msg","文件有误");
            return map;
        }
        //获取用户指定的文件夹。目的是做隔离，不同的业务，放在不同的文件夹中。
        String dir = request.getParameter("dir");

        //上传至本地
        return uploadService.uploadImg(multipartFile,dir);


    }
}
