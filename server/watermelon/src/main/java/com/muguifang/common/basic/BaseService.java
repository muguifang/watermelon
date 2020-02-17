package com.muguifang.common.basic;


import com.muguifang.po.TUser;

public interface BaseService {
    /**
     * 获取当前登陆用户
     */
    public TUser getCurrentLoginEmployee();
}
