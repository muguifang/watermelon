package com.muguifang.controller;

import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.po.TInformation;
import com.muguifang.result.ResultVo;
import com.muguifang.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description muguifang
 * @Date 2020/2/19 13:48
 * @Description
 */
@RequestMapping("/information")
@RestController
public class InformationController {
    @Autowired
    private InformationService informationService;
    /**
     * 获取所有资讯
     * @param param
     * @return
     */
    @PostMapping("/getInfo")
    public ResultVo getInfo(@RequestBody Map<String, Object> param){
        String title = null;
        if(param != null){
            title = param.get("title").toString();
        }
        List<TInformation> infosByTitle = informationService.getInfosByTitle(title);
        return ResultVo.sendResult(200, "success", infosByTitle);
    }
    /**
     * 修改资讯信息
     * @param tInformation
     * @return
     */
    @PutMapping("/updateInfo")
    public ResultVo updateInfo(@RequestBody TInformation tInformation){
        if(tInformation == null){
            throw new ParamException(501, "获取资讯信息异常");
        }
        informationService.updateInfo(tInformation);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 删除资讯
     * @param id
     * @return
     */
    @DeleteMapping("/deleteInfo")
    public ResultVo deleteInfo(Integer id){
        if(id == null){
            throw new ParamException(501, "获取资讯id异常");
        }
        informationService.deleteInfo(id);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 添加资讯
     * @param tInformation
     * @return
     */
    @PostMapping("/addInfo")
    public ResultVo addInfo(@RequestBody TInformation tInformation){
        if(tInformation == null){
            throw new ParamException(501, "获取资讯信息异常");
        }
        informationService.addInfo(tInformation);
        return ResultVo.sendResult(200, "success");
    }
}
