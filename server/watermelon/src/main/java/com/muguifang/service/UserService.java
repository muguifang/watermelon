package com.muguifang.service;

import com.muguifang.common.basic.BaseService;
import com.muguifang.po.TUser;

import java.util.Map;


/**
 * @Description qubaolai
 * @Date 2020/2/16 15:44
 * @Description 用户业务
 */
public interface UserService extends BaseService {
    /**
     * 用户登录
     * @param tUser
     * @return
     */
    public TUser login(TUser tUser);

    /**
     * 用户修改密码
     * @param param
     */
    public void updatePass(Map<String, Object> param);

}
