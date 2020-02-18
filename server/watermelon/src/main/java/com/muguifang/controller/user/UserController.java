package com.muguifang.controller.user;

import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.po.TUser;
import com.muguifang.result.ResultVo;
import com.muguifang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description qubaolai
 * @Date 2020/2/18 16:42
 * @Description 用户注册
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResultVo register(@RequestBody TUser tUser){
        if(tUser == null){
            throw new ParamException(501, "获取注册信息异常");
        }
        userService.register(tUser);
        return ResultVo.sendResult(200, "success");
    }
}
