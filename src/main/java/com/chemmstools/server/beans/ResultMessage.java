package com.chemmstools.server.beans;

import lombok.Data;

@Data
public class ResultMessage {
    private String resultCode;
    private String resultMsg;
    private String token;
}
