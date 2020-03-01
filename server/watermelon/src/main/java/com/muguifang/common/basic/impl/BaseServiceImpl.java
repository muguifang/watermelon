package com.muguifang.common.basic.impl;

import com.muguifang.common.basic.BaseService;
import com.muguifang.po.TUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class BaseServiceImpl implements BaseService {

    @Resource
    private HttpServletRequest httpServletRequest;

    public TUser getCurrentLoginEmployee() {
        if(httpServletRequest != null){
            HttpSession session = httpServletRequest.getSession();
            TUser user = (TUser) session.getAttribute("user");
            return user;
        }
        return null;
    }
}
