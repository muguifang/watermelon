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
    public List<TType> getTypeByConditions(String typename) {
        TTypeExample example = new TTypeExample();
        if(null != typename && !"".equals(typename)){
            TTypeExample.Criteria criteria = example.createCriteria();
            criteria.andTypenameLike("%" + typename + "%");
        }
        List<TType> tTypes = tTypeMapper.selectByExample(example);
        return tTypes;
    }

    @Override
    public void addMusic(TMusic tMusic) {
        tMusicMapper.insert(tMusic);
    }

    @Override
    public void deleteMusic(List<Integer> ids) {
        TMusicExample example1 = new TMusicExample();
        TMusicExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andIdIn(ids);
        tMusicMapper.deleteByExample(example1);
        //从收藏表中删除该音乐
        TCollectExample example = new TCollectExample();
        TCollectExample.Criteria criteria = example.createCriteria();
        criteria.andMusicIdIn(ids);
        tCollectMapper.deleteByExample(example);
    }

    @Override
    public void updateMusic(TMusic tMusic) {
        tMusicMapper.updateByPrimaryKey(tMusic);
    }

    @Override
    public List<TMusic> getMusicByConditions(String musicname) {
        TMusicExample example = new TMusicExample();
        //通过音乐名称模糊查询
        if(null != musicname && !"".equals(musicname)){
            TMusicExample.Criteria criteria = example.createCriteria();
            criteria.andMusicnameLike("%" + musicname + "%");
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
