package com.muguifang.common.utils;

import com.muguifang.common.exception.exceptions.ParamException;

import java.io.File;

/**
 * @Description muguifang
 * @Date 2020/2/26 22:24
 * @Description 文件操作工具类
 */
public class FileUtil {
    public static void deleteFile(String path){
        if (path == null || "".equals(path)) {
            throw new ParamException(501, "文件路径无效");
        }
        File file = new File(path);
        if (file.isFile()) {// 表示该文件不是文件夹
            file.delete();
        }
    }
}
