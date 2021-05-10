package com.chemmstools.server.utils;


import com.chemmstools.server.beans.ResultMessage;
import org.springframework.stereotype.Component;

@Component
public class ResultUtils {

    private ResultMessage resultMessage;

    public ResultUtils(){
        this.resultMessage=new ResultMessage();
    }


    public ResultMessage sendResult(String code,String msg,String token){
        this.resultMessage.setResultCode(code);
        this.resultMessage.setToken(token);
        this.resultMessage.setResultMsg(msg);
        return resultMessage;
    }

}
