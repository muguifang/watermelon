package com.muguifang.mapper.myMapper;

import java.util.List;
import java.util.Map;

/**
 * @Description muguifang
 * @Date 2020/2/19 15:46
 * @Description
 */
public interface TMyMusicMapper {
    public List<Map<String, Object>> getAllMusicInfo(String name);

    public void updateMusic(Map<String, Object> param);
}
