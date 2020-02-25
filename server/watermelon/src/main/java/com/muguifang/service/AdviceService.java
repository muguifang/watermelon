package com.muguifang.service;

import com.muguifang.po.TAdvise;

import java.util.List;
import java.util.Map;

/**
 * @Description muguifang
 * @Date 2020/2/24 16:47
 * @Description
 */
public interface AdviceService {
    /**
     * 添加网站建议
     * @param tAdvise
     */
    public void addAdvice(TAdvise tAdvise);

    /**
     * 批量删除网站建议
     * @param ids
     */
    public void deleteAdvice(List<Integer> ids);

    public List<Map<String, Object>> getAdviceByConditions(String conditions);
}
