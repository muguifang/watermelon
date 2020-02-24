package com.muguifang.service;

import com.muguifang.common.basic.BaseService;
import com.muguifang.po.TUser;

import java.util.List;
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

    /**
     * 用户注册
     * @param tUser
     */
    public void register(TUser tUser);
    /**
     * 获取所有用户
     * @return
     */
    public List<TUser> getAllUser(String username);

    /**
     * 修改用户
     * @param tUser
     */
    public void updateUser(TUser tUser);

    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(Integer id);
}
