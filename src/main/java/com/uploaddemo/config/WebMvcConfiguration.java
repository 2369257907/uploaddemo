package com.uploaddemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 86171
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Value("${file.staticPatternPath}")
    private String staticPatternPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    //这个就是springboot中springmvc让程序开发着去配置文件上传的静态资源的配置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticPatternPath+"/**").addResourceLocations("file:"+uploadFolder);
    }
}
