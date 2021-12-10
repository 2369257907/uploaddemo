package com.uploaddemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.uploaddemo.entity.User;

import java.util.ArrayList;

@Mapper
@Repository
public interface EditorMapper {

    @Select("SELECT * FROM User ")
    ArrayList<User> findAllUser();

}
