package com.muguifang.mapper.myMapper;

import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/3/1 12:39
 * @Description
 */
public interface TMyCommentMapper {
    public List<Map<String, Object>> getCommentByMusicId(Integer id);
}
