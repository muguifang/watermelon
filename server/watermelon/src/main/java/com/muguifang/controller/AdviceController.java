package com.muguifang.controller;

import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.po.TAdvise;
import com.muguifang.result.ResultVo;
import com.muguifang.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description muguifang
 * @Date 2020/2/24 16:45
 * @Description
 */
@RestController
@RequestMapping("/advice")
public class AdviceController {
    @Autowired
    private AdviceService adviceService;

    /**
     * 添加网站建议
     * @param tAdvise
     * @return
     */
    @PostMapping("/addAdvice")
    public ResultVo addAdvice(@RequestBody TAdvise tAdvise){
        if(tAdvise == null){
            throw new ParamException(501, "请填写网站建议必要信息");
        }
        adviceService.addAdvice(tAdvise);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 批量删除网站建议
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteAdvice")
    public ResultVo deleteAdvice(@RequestBody List<Integer> ids){
        adviceService.deleteAdvice(ids);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 通过条件查询网站建议
     * @param phone
     * @return
     */
    @GetMapping("/getAdviceByConditions")
    public ResultVo getAdviceByConditions(String phone){
        List<Map<String, Object>> adviceByConditions = adviceService.getAdviceByConditions(phone);
        return ResultVo.sendResult(200, "success", adviceByConditions);
    }
}
