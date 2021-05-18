package com.chemmstools.server.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static HttpUtils _instance;

    public static HttpUtils getInstance() {
        if (_instance == null) {
            synchronized (HttpUtils.class) {
                if (_instance == null) {
                    _instance = new HttpUtils();
                }
            }
        }
        return _instance;
    }

    private HttpUtils() {

    }

    private final boolean openAPI = true;

    private final String parsePictureAPI = "http://192.168.3.70:8080/testMathpix";

    private final String parseCharAPI = "http://192.168.3.70:8080/testBaidu";

//    private final String parseCharAPI = "http://192.168.3.70:8080/testTencent";

    public String parsePicture(String pictureData) {
        if (!openAPI) {
            return "[图片识别功能已关闭]";
        }
        if (pictureData == null) {
            return "";
        }
        JSONObject data = new JSONObject();
        data.put("data", pictureData);
        JSONObject result = (JSONObject) JSON.parse(doPost(parsePictureAPI, data));
        return result.get("latex_styled") == null ? "" : (String) result.get("latex_styled");
    }

    public String parseChar(String pictureData) {
        if (!openAPI) {
            return "[图片识别功能已关闭]";
        }
        if (pictureData == null) {
            return "";
        }

        JSONObject data = new JSONObject();
        data.put("data", pictureData);
        String ret=doPost(parseCharAPI, data);
        System.out.println("文字识别"+ret);
        return ret;
    }

    public String sendGet(String url, String param) {
        StringBuilder result = new StringBuilder();
        BufferedReader buffer = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            buffer = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = buffer.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (buffer != null) {
                    buffer.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
    }

    @SneakyThrows
    public String doPost(String url, JSONObject data) {
        StringBuffer bs = new StringBuffer();
        URL sendUrl = new URL(url.trim());
        URLConnection connection = sendUrl.openConnection();
        connection.setConnectTimeout(3000);
        connection.setReadTimeout(3000);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json;chert=UTF-8");
        OutputStreamWriter out = new OutputStreamWriter(
                connection.getOutputStream(), "UTF-8");
        out.write(data.toJSONString());
        out.flush();
        out.close();
        connection.connect();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String l = null;
        while ((l = buffer.readLine()) != null) {
            bs.append(l);
        }
        return bs.toString();
    }

}