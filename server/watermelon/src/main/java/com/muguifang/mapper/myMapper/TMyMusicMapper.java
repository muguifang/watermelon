package com.muguifang.mapper.myMapper;

import com.muguifang.po.TMusic;

import java.util.List;
import java.util.Map;

/**
 * @Description muguifang
 * @Date 2020/2/19 15:46
 * @Description
 */
public interface TMyMusicMapper {
    public List<Map<String, Object>> getAllMusicInfo(String name);

    public List<Map<String, Object>> getAllMusic(String musicName);

    public List<TMusic> getTenMusic();

    public List<TMusic> getRecommendMusic();
}
