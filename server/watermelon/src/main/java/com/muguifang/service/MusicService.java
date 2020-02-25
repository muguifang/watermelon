package com.muguifang.service;

import com.muguifang.po.TMusic;
import com.muguifang.po.TType;
import com.muguifang.result.ResultVo;

import java.util.List;
import java.util.Map;

/**
 * @Description muguifang
 * @Date 2020/2/19 13:52
 * @Description
 */
public interface MusicService {
    /**
     * 添加音乐类别
     * @param tType
     */
    public void addType(TType tType);

    /**
     * 删除音乐类别
     * @param ids
     */
    public ResultVo deleteType(List<Integer> ids);

    /**
     * 修改音乐类别
     * @param tType
     */
    public void updateType(TType tType);

    /**
     * 按条件获取音乐类别 条件为空时查询所有
     * @param typename
     * @return
     */
    public List<TType> getTypeByConditions(String typename);

    /**
     * 添加音乐
     * @param tMusic
     */
    public void addMusic(TMusic tMusic);

    /**
     * 删除音乐
     * @param ids
     */
    public void deleteMusic(List<Integer> ids);

    /**
     * 修改音乐
     * @param tMusic
     */
    public void updateMusic(TMusic tMusic);

    /**
     * 通过条件获取音乐信息 条件为空查询所有
     * @param musicname
     * @return
     */
    public List<Map<String, Object>> getMusicByConditions(String musicname);

    /**
     * 按点击量倒序查询音乐
     * @return
     */
    public List<TMusic> getAllMusic(String name);

    /**
     * 获取音乐收藏信息
     * @return
     */
    public List<Map<String, Object>> getAllMusicInfo(String musicName);

    /**
     * 通过音乐类别id获取音乐
     * @param id
     * @return
     */
    public List<TMusic> getMusicsByType(Integer id);
}
