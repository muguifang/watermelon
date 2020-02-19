package com.muguifang.service.impl;

import com.muguifang.mapper.TCollectMapper;
import com.muguifang.mapper.TMusicMapper;
import com.muguifang.mapper.TTypeMapper;
import com.muguifang.mapper.myMapper.TMyMusicMapper;
import com.muguifang.po.*;
import com.muguifang.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/2/19 13:53
 * @Description
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private TTypeMapper tTypeMapper;
    @Autowired
    private TMusicMapper tMusicMapper;
    @Autowired
    private TMyMusicMapper tMyMusicMapper;
    @Autowired
    private TCollectMapper tCollectMapper;

    @Override
    public void addType(TType tType) {
        tTypeMapper.insert(tType);
    }

    @Override
    public void deleteType(Integer id) {
        tTypeMapper.deleteByPrimaryKey(id);
        TMusicExample example = new TMusicExample();
        TMusicExample.Criteria criteria = example.createCriteria();
        criteria.andTypeIdEqualTo(id);
        List<TMusic> tMusics = tMusicMapper.selectByExample(example);
        if(tMusics != null && 0 < tMusics.size()){
            for(TMusic tMusic : tMusics){
                tMusic.setTypeId(null);
                tMusicMapper.updateByPrimaryKey(tMusic);
            }
        }
    }

    @Override
    public void updateType(TType tType) {
        tTypeMapper.updateByPrimaryKey(tType);
    }

    @Override
    public List<TType> getTypeByConditions(Map<String, Object> param) {
        TTypeExample example = new TTypeExample();
        if(null != param.get("name") && !"".equals(param.get("name"))){
            TTypeExample.Criteria criteria = example.createCriteria();
            criteria.andTypenameNotLike("%" + (String)param.get("name") + "%");
        }
        List<TType> tTypes = tTypeMapper.selectByExample(example);
        return tTypes;
    }

    @Override
    public void addMusic(TMusic tMusic) {
        tMusicMapper.insert(tMusic);
    }

    @Override
    public void deleteMusic(Integer id) {
        tMusicMapper.deleteByPrimaryKey(id);
        //从收藏表中删除该音乐
        TCollectExample example = new TCollectExample();
        TCollectExample.Criteria criteria = example.createCriteria();
        criteria.andMusicIdEqualTo(id);
        tCollectMapper.deleteByExample(example);
    }

    @Override
    public void updateMusic(TMusic tMusic) {
        tMusicMapper.updateByPrimaryKey(tMusic);
    }

    @Override
    public List<TMusic> getMusicByConditions(Map<String, Object> param) {
        TMusicExample example = new TMusicExample();
        //通过音乐名称模糊查询
        if(null != param.get("name") && !"".equals(param.get("name"))){
            TMusicExample.Criteria criteria = example.createCriteria();
            criteria.andMusicnameLike("%" + (String)param.get("name") + "%");
        }
        List<TMusic> tMusics = tMusicMapper.selectByExample(example);
        return tMusics;
    }

    @Override
    public List<TMusic> getAllMusic() {
        TMusicExample example = new TMusicExample();
        example.setOrderByClause("playNum DESC");
        List<TMusic> tMusics = tMusicMapper.selectByExample(example);
        return tMusics;
    }

    @Override
    public List<Map<String, Object>> getAllMusicInfo() {
        List<Map<String, Object>> allMusicInfo = tMyMusicMapper.getAllMusicInfo();
        return allMusicInfo;
    }

    @Override
    public List<TMusic> getMusicsByType(Integer id) {
        TMusicExample example = new TMusicExample();
        TMusicExample.Criteria criteria = example.createCriteria();
        criteria.andTypeIdEqualTo(id);
        List<TMusic> tMusics = tMusicMapper.selectByExample(example);
        return tMusics;
    }

}
