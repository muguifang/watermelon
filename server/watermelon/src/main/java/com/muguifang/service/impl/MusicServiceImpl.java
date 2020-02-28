package com.muguifang.service.impl;

import com.muguifang.common.exception.exceptions.DataException;
import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.common.utils.Base64Util;
import com.muguifang.mapper.TCollectMapper;
import com.muguifang.mapper.TMusicMapper;
import com.muguifang.mapper.TTypeMapper;
import com.muguifang.mapper.myMapper.TMyMusicMapper;
import com.muguifang.po.*;
import com.muguifang.result.ResultVo;
import com.muguifang.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        tType.setInsertdate(new Date());
        tTypeMapper.insert(tType);
    }

    @Override
    public ResultVo deleteType(List<Integer> ids) {
        TMusicExample tMusicExample = new TMusicExample();
        TMusicExample.Criteria criteria = tMusicExample.createCriteria();
        criteria.andTypeIdIn(ids);
        List<TMusic> tMusics = tMusicMapper.selectByExample(tMusicExample);
        //如果根据编号能查出音乐 将类别挑选出来
        if(tMusics != null && tMusics.size() > 0){
            List<Integer> idList = new ArrayList<>();
            for(TMusic tMusic : tMusics){
                for(Integer integer : ids){
                    if(tMusic.getTypeId() == integer){
                        idList.add(integer);
                    }
                }
            }
            TTypeExample typeExample = new TTypeExample();
            TTypeExample.Criteria criteria2 = typeExample.createCriteria();
            criteria2.andIdIn(idList);
            List<TType> tTypes = tTypeMapper.selectByExample(typeExample);
            if(tTypes != null || tTypes.size() > 0){
                return ResultVo.sendResult(401, "类别存在音乐", tTypes);
            }
        }
        TTypeExample example = new TTypeExample();
        TTypeExample.Criteria criteria1 = example.createCriteria();
        criteria1.andIdIn(ids);
        tTypeMapper.deleteByExample(example);
        //修改音乐信息
//        Map<String, Object> param = new HashMap<>();
//        param.put("ids", ids);
//        tMyMusicMapper.updateMusic(param);
        return ResultVo.sendResult(200, "success");
    }

    @Override
    public void updateType(TType tType) {
        tTypeMapper.updateByPrimaryKey(tType);
    }

    @Override
    public List<TType> getTypeByConditions(String typename) {
        TTypeExample example = new TTypeExample();
        example.setOrderByClause("insertDate DESC");
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
        tMusicMapper.updateByPrimaryKeySelective(tMusic);
    }

    @Override
    public List<Map<String, Object>> getMusicByConditions(String musicname) {
        List<Map<String, Object>> musics = tMyMusicMapper.getAllMusic(musicname);
        for(Map map : musics){
            String musicPhoto = (String)map.get("musicPhoto");
            map.put("musicPhoto", Base64Util.base64Convert(musicPhoto));
        }
        return musics;
    }

    @Override
    public List<TMusic> getAllMusic(String name) {
        TMusicExample example = new TMusicExample();
        example.setOrderByClause("playNum DESC");
        if(name != null && !"".equals(name)){
            TMusicExample.Criteria criteria = example.createCriteria();
            criteria.andMusicnameLike("%" + name + "%");
        }
        List<TMusic> tMusics = tMusicMapper.selectByExample(example);
        for(TMusic tMusic : tMusics){
            String musicphoto = tMusic.getMusicphoto();
            tMusic.setMusicphoto(Base64Util.base64Convert(musicphoto));
        }
        return tMusics;
    }

    @Override
    public List<Map<String, Object>> getAllMusicInfo(String musicName) {
        List<Map<String, Object>> allMusicInfo = tMyMusicMapper.getAllMusicInfo(musicName);
        for(Map<String, Object> tMusicMap : allMusicInfo){
            String musicphoto = tMusicMap.get("pic").toString();
            tMusicMap.put("pic",Base64Util.base64Convert(musicphoto));
        }
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

    @Override
    public String returnBase64(String path) {
        if(path == null || "".equals(path)){
            throw new ParamException(501, "获取路径失败！");
        }
        return Base64Util.base64Convert(path);
    }


}
