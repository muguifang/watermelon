package com.muguifang.service;

import com.muguifang.common.basic.BaseService;
import com.muguifang.po.TAdmin;
import com.muguifang.po.TInformation;
import com.muguifang.po.TUser;

import java.util.List;
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

    /**
     * 获取所有资讯
     * @param title
     * @return
     */
    public List<TInformation> getInfosByTitle(String title);

    /**
     * 修改资讯信息
     * @param tInformation
     */
    public void updateInfo(TInformation tInformation);

    /**
     * 删除指定id的资讯
     * @param id
     */
    public void deleteInfo(Integer id);

    /**
     * 添加资讯信息
     * @param tInformation
     */
    public void addInfo(TInformation tInformation);

    /**
     * 获取所有用户
     * @return
     */
    public List<TUser> getAllUser(Map<String, Object> param);

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
