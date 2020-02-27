package com.muguifang.common.utils;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description muguifang
 * @Date 2020/2/26 22:12
 * @Description base64转换类
 */
public class Base64Util {
    public static String base64Convert(String path){
        InputStream inputStream = null;
        byte[] data = null;
        String contentType="";
        try {
            String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            if ("jpg".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix)) {
                contentType = "data:image/jpeg;base64,";
            } else if ("png".equalsIgnoreCase(suffix)) {
                contentType = "data:image/png;base64,";
            } else if("aac".equalsIgnoreCase(suffix)){
                contentType = "data:audio/aac;base64,";
            } else if("mp3".equalsIgnoreCase(suffix)){
                contentType = "data:audio/x-mpeg;base64,";
            } else if("avi".equalsIgnoreCase(suffix)){
                contentType = "data:video/avi;base64,";
            }
            inputStream = new FileInputStream(path);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return contentType+encoder.encode(data).replace("\r\n", "");
    }
}
