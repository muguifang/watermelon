package com.muguifang.controller;

import com.muguifang.common.exception.exceptions.NoLoginException;
import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.po.TUser;
import com.muguifang.result.ResultVo;
import com.muguifang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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

    /**
     * 注册
     * @param tUser
     * @return
     */
    @PostMapping("/register")
    public ResultVo register(@RequestBody TUser tUser){
        if(tUser == null){
            throw new ParamException(501, "获取注册信息异常");
        }
        userService.register(tUser);
        return ResultVo.sendResult(200, "success");
    }
    /**
     * 获取所有用户
     * @param username
     * @return
     */
    @GetMapping("/getAllUser")
    public ResultVo getAllUser(String username){
        if(username == null){
            throw new ParamException(501, "获取参数异常");
        }
        List<TUser> allUser = userService.getAllUser(username);
        return ResultVo.sendResult(200, "success", allUser);
    }

    /**
     * 修改员工
     * @param tUser
     * @return
     */
    @PutMapping("/updateUser")
    public ResultVo updateUser(@RequestBody TUser tUser){
        if(tUser == null){
            throw new ParamException(501, "获取用户信息异常");
        }
        userService.updateUser(tUser);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 删除员工
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteUser")
    public ResultVo seleteUser(List<Integer> ids){
        if(ids == null){
            throw new ParamException(501, "获取用户id异常");
        }
        userService.deleteUser(ids);
        return ResultVo.sendResult(200, "success");
    }
    /**
     * 用户登录
     * @param tUser
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResultVo login(@RequestBody TUser tUser, HttpServletRequest request){
        TUser user = userService.login(tUser);
        //将user存入session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return ResultVo.sendResult(200, "success", user);
    }

    /**
     * 修改密码
     * @param param
     * @return
     */
    @PutMapping("/updatePassword")
    public ResultVo updatePassword(@RequestBody Map<String, Object> param){
        if(param == null){
            throw new ParamException(501, "参数异常!");
        }
        userService.updatePass(param);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public ResultVo logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        TUser currentLoginEmployee = userService.getCurrentLoginEmployee();
        if(currentLoginEmployee == null){
            throw new NoLoginException(401, "登录失效!");
        }
        session.removeAttribute("user");
        return ResultVo.sendResult(200, "success");
    }
}
