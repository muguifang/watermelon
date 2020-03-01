package com.muguifang.controller;

import com.muguifang.common.exception.exceptions.NoDataException;
import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.po.TMusic;
import com.muguifang.po.TType;
import com.muguifang.result.ResultVo;
import com.muguifang.service.MusicService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    /**
     * 通过音乐类别获取音乐
     * @return
     */
    @PostMapping("/getAllMusicByType")
    public ResultVo getAllMusicByType(@RequestBody Map<String, Integer> param){
        List<TMusic> musicsByType = musicService.getMusicsByType(param.get("typeId"));
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

    /**
     * 返回base64
     * @return
     */
    @PostMapping("/returnBase64")
    public ResultVo returnBase64(@RequestBody Map<String, Object> param){
        String path = (String) param.get("path");
        String base64String = musicService.returnBase64(path);
        return ResultVo.sendResult(200, "success", base64String);
    }

    /**
     * 获取最新添加的十条音乐
     * @return
     */
    @GetMapping("/getTenMusic")
    public ResultVo getTenMusic(){
        List<TMusic> tenMusic = musicService.getTenMusic();
        return ResultVo.sendResult(200, "success", tenMusic);
    }

    /**
     * 获取推荐的十首音乐
     * @return
     */
    @GetMapping("/getRecommendMusic")
    public ResultVo getRecommendMusic(){
        List<TMusic> recommendMusic = musicService.getRecommendMusic();
        return ResultVo.sendResult(200, "success", recommendMusic);
    }

    /**
     * 通过音乐id获取音乐详情
     * @param id
     * @return
     */
    @GetMapping("/getMusicById")
    public ResultVo getMusicById(Integer id){
        TMusic musicById = musicService.getMusicById(id);
        return ResultVo.sendResult(200, "success", musicById);
    }

    /**
     * 收藏歌曲
     * @param param
     * @return
     */
    @PostMapping("/collectMusic")
    public ResultVo collectMusic(@RequestBody Map<String, Object> param, HttpServletRequest request){
        if(param == null || param.size() <= 0){
            throw new ParamException(501, "参数异常!");
        }
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if("_u_i".equals(cookie.getName())){
                if(cookie.getValue() == null || "".equals(cookie.getValue())){
                    return ResultVo.sendResult(401, "登录失效!");
                }
            }
        }
        ResultVo resultVo = musicService.collectMusic(param);
        return resultVo;
    }

    /**
     * 获取我的收藏
     * @param id
     * @return
     */
    @GetMapping("/getMyCollect")
    public ResultVo getMyCollect(Integer id){
        List<Map<String, Object>> myCollect = musicService.getMyCollect(id);
        return ResultVo.sendResult(200, "success", myCollect);
    }

    /**
     * 取消收藏
     * @param id
     * @return
     */
    @DeleteMapping("/cancelCollect")
    public ResultVo cancelCollect(Integer id){
        musicService.cancelCollect(id);
        return ResultVo.sendResult(200, "success");
    }

}
