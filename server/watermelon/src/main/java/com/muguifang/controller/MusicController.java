package com.muguifang.controller;

import com.muguifang.po.TMusic;
import com.muguifang.po.TType;
import com.muguifang.result.ResultVo;
import com.muguifang.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description muguifang
 * @Date 2020/2/19 14:54
 * @Description
 */
@RequestMapping("/music")
@RestController
public class MusicController {
    @Autowired
    private MusicService musicService;

    /**
     * 添加音乐类别
     * @param tType
     * @return
     */
    @PostMapping("/addType")
    public ResultVo addMusicType(@RequestBody TType tType){
        musicService.addType(tType);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 修改音乐类别
     * @param tType
     * @return
     */
    @PutMapping("/updateType")
    public ResultVo updateType(@RequestBody TType tType){
        musicService.updateType(tType);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 删除音乐类别
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteType")
    public ResultVo deleteType(@RequestBody List<Integer> ids){
        return musicService.deleteType(ids);
    }

    /**
     * 根据条件获取音乐类别 条件为空查询全部
     * @param param
     * @return
     */
    @PostMapping("/getTypeByConditions")
    public ResultVo getTypeByConditions(@RequestBody Map<String, Object> param){
        String typename = null;
        if(param != null){
            typename = (String)param.get("typename");
        }
        List<TType> typeByConditions = musicService.getTypeByConditions(typename);
        return ResultVo.sendResult(200, "success", typeByConditions);
    }

    /**
     * 添加音乐
     * @param tMusic
     * @return
     */
    @PostMapping("/addMusic")
    public ResultVo addMusic(@RequestBody TMusic tMusic){
        tMusic.setInsertdate(new Date());
        musicService.addMusic(tMusic);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 修改音乐信息
     * @param tMusic
     * @return
     */
    @PutMapping("/updateMusic")
    public ResultVo updateMusic(@RequestBody TMusic tMusic){
        musicService.updateMusic(tMusic);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 删除音乐
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteMusic")
    public ResultVo deleteMusic(@RequestBody List<Integer> ids){
        musicService.deleteMusic(ids);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 通过条件获取音乐
     * @param param
     * @return
     */
    @PostMapping("/getMusicByConditions")
    public ResultVo getMusicByConditions(@RequestBody Map<String, Object> param){
        String musicname = null;
        if(param != null){
            musicname = (String)param.get("musicname");
        }
        List<Map<String, Object>> musicByConditions = musicService.getMusicByConditions(musicname);
        return  ResultVo.sendResult(200, "success", musicByConditions);
    }
    /**
     * 按点击量获取所有音乐
     * @return
     */
    @PostMapping("/getAllMusicByPlay")
    public ResultVo getAllMusic(@RequestBody Map<String, Object> param){
        String musicname = null;
        if(param != null){
            musicname = (String)param.get("musicname");
        }
        List<TMusic> allMusic = musicService.getAllMusic(musicname);
        return ResultVo.sendResult(200, "success", allMusic);
    }

//    /**
//     * 通过条件查询音乐 条件为空查询全部
//     * @return
//     */
//    @GetMapping("/getAllMusicByCollection")
//    public ResultVo getAllMusicByCollection(String name){
//        List<TMusic> musicByConditions = musicService.getMusicByConditions(name);
//        return ResultVo.sendResult(200, "success", musicByConditions);
//    }

    /**
     * 通过音乐类别获取音乐
     * @return
     */
    @PostMapping("/getAllMusicByType")
    public ResultVo getAllMusicByType(Integer id){
        List<TMusic> musicsByType = musicService.getMusicsByType(id);
        return ResultVo.sendResult(200, "success", musicsByType);
    }

    /**
     * 按收藏量查询音乐
     * @return
     */
    @PostMapping("/getColletMusic")
    public ResultVo getColletMusic(@RequestBody Map<String, Object> param){
        String musicname = null;
        if(param != null){
            musicname = (String)param.get("musicname");
        }
        List<Map<String, Object>> allMusicInfo = musicService.getAllMusicInfo(musicname);
        return ResultVo.sendResult(200, "success", allMusicInfo);
    }
}
