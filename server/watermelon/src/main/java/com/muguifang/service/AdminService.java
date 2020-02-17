package com.muguifang.service;

import com.muguifang.common.basic.BaseService;
import com.muguifang.po.TAdmin;
import com.muguifang.po.TUser;

import java.util.Map;

/**
 * @Date 2020/2/17 12:14
 * @Description 管理员业务
 */
public interface AdminService extends BaseService {

    /**
     * 添加轮播图
     * @param path
     */
    public void addLbt(String path);
}
