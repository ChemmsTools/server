package com.chemmstools.server.beans;


import lombok.Data;

@Data
public class AuthorizeParams {
    private String authType;
    private String token;
    private String username;
    private String password;
    private String code;
    private String langId;
    private String contentId;
}
