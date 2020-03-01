package com.muguifang.controller;

import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.po.TComment;
import com.muguifang.result.ResultVo;
import com.muguifang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/3/1 12:14
 * @Description
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 获取评论
     * @param id
     * @return
     */
    @GetMapping("/getComment")
    public ResultVo getComment(Integer id){
        List<Map<String, Object>> comments = commentService.getComments(id);
        return ResultVo.sendResult(200, "success", comments);
    }

    /**
     * 添加评论
     * @param param
     * @param request
     * @return
     */
    @PostMapping("/insertComment")
    public ResultVo insertComment(@RequestBody Map<String, Object> param, HttpServletRequest request){
        if(param == null || param.size() <= 0){
            throw new ParamException(501, "参数异常");
        }
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if("_u_i".equals(cookie.getName())){
                if(cookie.getValue() == null || "".equals(cookie.getValue())){
                    return ResultVo.sendResult(401, "登录失效!");
                }
            }
        }
        commentService.insertComment(param);
        return ResultVo.sendResult(200, "success");
    }
}
