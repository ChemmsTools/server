package com.chemmstools.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chemmstools.server.annotations.AuthToken;
import com.chemmstools.server.beans.AuthorizeParams;
import com.chemmstools.server.beans.ResultMessage;
import com.chemmstools.server.beans.User;
import com.chemmstools.server.service.ContentService;
import com.chemmstools.server.service.MenuService;
import com.chemmstools.server.service.TokenService;
import com.chemmstools.server.service.UserService;
import com.chemmstools.server.utils.RedisUtils;
import com.chemmstools.server.utils.ResultUtils;
import com.chemmstools.server.utils.SSOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/chemmstools")
public class indexController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ResultUtils resultUtils;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping("/login")
    @CrossOrigin
    public ResultMessage login(@RequestBody AuthorizeParams authorizeParams) {
        if (StringUtils.isAnyBlank(authorizeParams.getCode())) {
            return resultUtils.failResult("参数错误");
        } else {
            JSONObject ssoRet = SSOUtils.getInstance().authCode(authorizeParams.getCode());
            if ("200".equals(ssoRet.get("code"))) {
                JSONObject ret = (JSONObject) JSON.parse((String) ssoRet.get("resultMsg"));
                User user=userService.getUserByUsername((String) ret.get("username"));
                JSONObject result = new JSONObject();
                result.put("username", user.getUsername());
                result.put("name", user.getName());
                result.put("token", tokenService.getTokenByUsername(user.getUsername()));
                return resultUtils.successResult(result.toJSONString());
            } else {
                JSONObject ret = (JSONObject) JSON.parse((String) ssoRet.get("resultMsg"));
                if ("200".equals(ret.get("code"))) {
                    return resultUtils.successResult((String) ssoRet.get("resultMsg"));
                } else {
                    return resultUtils.failResult((String) ssoRet.get("resultMsg"));
                }

            }
        }
    }

    @PostMapping("/getcontent")
    @CrossOrigin
    public ResultMessage getContent(@RequestBody AuthorizeParams authorizeParams) {
        JSONObject result = new JSONObject();
        if (StringUtils.isAnyBlank(authorizeParams.getContentId())) {
            return resultUtils.failResult("参数错误");
        } else {
            try {
                return resultUtils.successResult(JSON.toJSONString(contentService.getContentById(Integer.parseInt(authorizeParams.getContentId()))));
            } catch (NumberFormatException e) {
                return resultUtils.failResult("非法参数");
            }
        }
    }

    @PostMapping("/getlangmenu")
    @CrossOrigin
    public ResultMessage getLangMenu(@RequestBody AuthorizeParams authorizeParams) {
        JSONObject result = new JSONObject();
        if (StringUtils.isAnyBlank(authorizeParams.getLangId())) {
            return resultUtils.failResult("参数错误");
        } else {
            try {
                return resultUtils.successResult(JSON.toJSONString(menuService.getMenuById(Integer.parseInt(authorizeParams.getLangId()))));
            } catch (NumberFormatException e) {
                return resultUtils.failResult("非法参数");
            }
        }
    }

    @PostMapping("/getlanguage")
    @CrossOrigin
    public ResultMessage getLanguage(@RequestBody AuthorizeParams authorizeParams) {
        return resultUtils.successResult(JSON.toJSONString(menuService.getLangList()));
    }


    @PostMapping("/islogin")
    @CrossOrigin
    @AuthToken
    public ResultMessage isLogin(@RequestBody AuthorizeParams authorizeParams) {
        JSONObject jsonObject = new JSONObject();
        User user = userService.getUserByUsername(tokenService.getUsernameByToken(authorizeParams.getToken()));
        jsonObject.put("username", user.getUsername());
        jsonObject.put("name", user.getName());
        return resultUtils.successResult(jsonObject.toJSONString());
    }


    private String getIP(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String ip = request.getRemoteAddr();
        final String[] headers = {"x-forwarded-for", "Proxy-Client-IP", "WL-Proxy-Client-IP", "X-Real-IP"};
        for (int i = 0; i < headers.length; ++i) {
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader(headers[i]);
            } else {
                break;
            }
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = null;
        } else if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "localhost";
        }
        return ip;
    }
}
