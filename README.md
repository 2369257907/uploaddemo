# springboot上传文件简介
包含了上传文件至阿里云oss和本地两种文件上传方式

如何开始：

1.git clone https://github.com/2369257907/uploaddemo.git

2.修改 application-dev.yml 中的uploadFolder为本机存储上传文件的目录

3.启动项目

4.访问http://localhost:8779/upload 测试上传文件

5.在设置的存储目录中查看上传的文件

功能：
1.上传图片到服务器中

返回信息：Json数据，包含以下内容（上传成功时：）
    1.url：可访问的上传文件的链接
    2.size：上传文件的大小  
    3.filename：上传的文件名
    4.realPath：服务器中存储文件的路径
形如：{"filename":"zhuchang.png",
"size":913803,
"realPath":"E://uploadFile/bbb/2021/12/09",
"url":"http://localhost:8779/uploadimg/bbb/2021/12/09/ffcb4864-dda9-4e63-ad5c-e7dae607ed8c.png"}
    }
    
    上传失败：返回{{msg："上传失败"}}
