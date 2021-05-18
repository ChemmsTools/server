package com.chemmstools.server.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class SSOUtils {
//    private final String appId="a253790c-74d5-4391-9d49-2bef49690f5d";
//    private final String appKey="0e7837da-6ec5-46ac-b00c-9b51d7d441bf";

    private final String appId="5a938d4a-4284-408a-9c43-c9f39a18e3e9";
    private final String appKey="9d028f07-e00d-4520-aac5-3da0d241ca32";

    private final String serverURL="http://api.midgroup.cn/sso/oauth/authcode";

    private static SSOUtils _instance;

    public static SSOUtils getInstance() {
        if (_instance == null) {
            synchronized (SSOUtils.class) {
                if (_instance == null) {
                    _instance = new SSOUtils();
                }
            }
        }
        return _instance;
    }
    private SSOUtils(){

    }

    public JSONObject authCode(String code){
        JSONObject jsObject=new JSONObject();
        jsObject.put("code",code);
        jsObject.put("appId",appId);
        jsObject.put("appKey",appKey);
        try {
            return (JSONObject) JSON.parse(HttpUtils.getInstance().doPost(serverURL,jsObject));
        }catch (JSONException e){
            JSONObject jsonObject= new JSONObject();
            jsObject.put("code","500");
            jsObject.put("resultMsg","json解析异常");
            return jsonObject;
        }
        catch (Exception e){
            JSONObject jsonObject= new JSONObject();
            jsObject.put("code","500");
            jsObject.put("resultMsg","SSO服务器异常");
            return jsonObject;
        }
    }
}
