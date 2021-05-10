package com.chemmstools.server.service.imp;

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
    public boolean authUserUserNameAndPassword(String username, String password) {
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return false;
        }
        return userMapper.getUserByUsernameAndPassword(username,password)!=null;
    }


}
