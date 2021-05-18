package com.chemmstools.server.beans;

import lombok.Data;

@Data
public class ResultMessage {
    private String success;
    private String message;
    private String data;
}
