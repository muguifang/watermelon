package com.muguifang.controller.commonality;

import com.muguifang.common.exception.exceptions.NoLoginException;
import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.po.TUser;
import com.muguifang.result.ResultVo;
import com.muguifang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description muguifang
 * @Date 2020/2/16 15:05
 * @Description 用户Controller
 */
@RequestMapping("/common")
@RestController
public class CommonController {
    @Autowired
    private UserService userService;

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

    @PutMapping("/updatePassword")
    public ResultVo updatePassword(@RequestBody Map<String, Object> param){
        if(param == null){
            throw new ParamException(501, "参数异常!");
        }
        userService.updatePass(param);
        return ResultVo.sendResult(200, "success");
    }

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


