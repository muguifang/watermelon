package com.muguifang.service;

import com.muguifang.po.TInformation;

import java.util.List;

/**
 * @Description muguifang
 * @Date 2020/2/19 13:38
 * @Description
 */
public interface InformationService {
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
     * @param ids
     */
    public void deleteInfo(List<Integer> ids);

    /**
     * 添加资讯信息
     * @param tInformation
     */
    public void addInfo(TInformation tInformation);
}
