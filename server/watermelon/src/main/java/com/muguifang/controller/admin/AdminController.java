package com.muguifang.controller.admin;

import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.po.TInformation;
import com.muguifang.po.TUser;
import com.muguifang.result.ResultVo;
import com.muguifang.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Date 2020/2/17 12:12
 * @Description 管理员Controller
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 获取所有资讯
     * @param title
     * @return
     */
    @GetMapping("/getInfo")
    public ResultVo getInfo(String title){
        List<TInformation> infosByTitle = adminService.getInfosByTitle(title);
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
        adminService.updateInfo(tInformation);
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
        adminService.deleteInfo(id);
        return ResultVo.sendResult(200, "success");
    }

    @PostMapping("/addInfo")
    public ResultVo addInfo(@RequestBody TInformation tInformation){
        if(tInformation == null){
            throw new ParamException(501, "获取资讯信息异常");
        }
        adminService.addInfo(tInformation);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 获取所有用户
     * @param param
     * @return
     */
    @GetMapping("/getAllUser")
    public ResultVo getAllUser(@RequestBody Map<String, Object> param){
        if(param == null){
            throw new ParamException(501, "获取参数异常");
        }
        List<TUser> allUser = adminService.getAllUser(param);
        return ResultVo.sendResult(200, "success", allUser);
    }

    @PutMapping("/updateUser")
    public ResultVo updateUser(@RequestBody TUser tUser){
        if(tUser == null){
            throw new ParamException(501, "获取用户信息异常");
        }
        adminService.updateUser(tUser);
        return ResultVo.sendResult(200, "success");
    }

    @DeleteMapping("/deleteUser")
    public ResultVo seleteUser(Integer id){
        if(id == null){
            throw new ParamException(501, "获取用户id异常");
        }
        adminService.deleteUser(id);
        return ResultVo.sendResult(200, "success");
    }
}
