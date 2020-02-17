package com.muguifang.common.utils;

import java.util.UUID;

/**
 * @ClassName UUIDUtil
 * @Author muguifang
 * @Date 2020/01/19 11:37
 * @Description 生成UUID
 */
public class UUIDUtil {
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
}
