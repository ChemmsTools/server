package com.chemmstools.server.utils;


import com.chemmstools.server.beans.ResultMessage;
import org.springframework.stereotype.Component;

@Component
public class ResultUtils {

    private ResultMessage resultMessage;

    public ResultUtils(){
        this.resultMessage=new ResultMessage();
    }


    public ResultMessage failResult(String message){
        this.resultMessage.setSuccess("false");
        this.resultMessage.setMessage(message);
        this.resultMessage.setData("");
        return resultMessage;
    }

    public ResultMessage successResult(String data){
        this.resultMessage.setSuccess("true");
        this.resultMessage.setMessage("");
        this.resultMessage.setData(data);
        return resultMessage;
    }

}
