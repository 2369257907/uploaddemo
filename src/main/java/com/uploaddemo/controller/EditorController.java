package com.uploaddemo.controller;

import com.uploaddemo.mapper.EditorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uploaddemo.entity.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EditorController {
    @Autowired
    private EditorMapper userMapper;

    @RequestMapping("/queryUserList")
    public List<User> queryUserList() {
        List<User> userList = new ArrayList<>();
        userList = userMapper.findAllUser();
        System.out.println(userList);
        return userList;
    }
}
