package com.uploaddemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @GetMapping("/editor")
    public String editor(){
        return "wangeditor";
    }
}
