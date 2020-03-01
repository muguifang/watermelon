package com.muguifang.service;

import com.muguifang.po.TComment;

import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/3/1 12:11
 * @Description
 */
public interface CommentService {
    /**
     * 获取所有评论
     * @param id
     * @return
     */
    public List<Map<String, Object>> getComments(Integer id);

    /**
     * 添加评论
     * @param param
     */
    public void insertComment(Map<String, Object> param);
}
