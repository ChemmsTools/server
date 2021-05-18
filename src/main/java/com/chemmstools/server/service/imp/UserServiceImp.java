package com.chemmstools.server.service.imp;

import com.chemmstools.server.beans.User;
import com.chemmstools.server.mapper.UserMapper;
import com.chemmstools.server.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean authUserUserName(String username) {
        if(StringUtils.isBlank(username)){
            return false;
        }
        return userMapper.getUserByUsername(username)!=null;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
