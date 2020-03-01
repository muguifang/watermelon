package com.muguifang.service.impl;

import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.mapper.TCommentMapper;
import com.muguifang.mapper.myMapper.TMyCommentMapper;
import com.muguifang.po.TComment;
import com.muguifang.po.TCommentExample;
import com.muguifang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/3/1 12:11
 * @Description
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private TMyCommentMapper tMyCommentMapper;
    @Autowired
    private TCommentMapper tCommentMapper;

    @Override
    public List<Map<String, Object>> getComments(Integer id) {
        List<Map<String, Object>> comments = tMyCommentMapper.getCommentByMusicId(id);
        return comments;
    }

    @Override
    public void insertComment(Map<String, Object> param) {
        if(param.get("u_id") == null || "".equals(param.get("u_id"))){
            throw new ParamException(501, "参数异常");
        }
        if(param.get("content") == null || "".equals(param.get("content"))){
            throw new ParamException(501, "参数异常");
        }
        if(param.get("m_id") == null || "".equals(param.get("m_id"))){
            throw new ParamException(501, "参数异常");
        }
        Integer uId = (Integer) param.get("u_id");
        Integer mId = (Integer) param.get("m_id");
        String content = (String) param.get("content");
        TComment tComment = new TComment();
        tComment.setId(0);
        tComment.setMusicId(mId);
        tComment.setUserId(uId);
        tComment.setContent(content);
        tComment.setInsertdate(new Date());
        tCommentMapper.insert(tComment);
    }
}
