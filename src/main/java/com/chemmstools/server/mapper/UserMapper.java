package com.chemmstools.server.mapper;


import com.chemmstools.server.beans.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * from tb_user where username=#{username}")
    User getUserByUsername(String username);
}
