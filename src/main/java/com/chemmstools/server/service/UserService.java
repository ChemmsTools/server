package com.chemmstools.server.service;

import com.chemmstools.server.beans.User;

public interface UserService {

    boolean authUserUserName(String username);

    User getUserByUsername(String username);
}
