package com.chemmstools.server.beans;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String permission;
}
