package com.muguifang.service.impl;

import com.muguifang.common.basic.impl.BaseServiceImpl;
import com.muguifang.common.exception.exceptions.LoginException;
import com.muguifang.common.exception.exceptions.NoDataException;
import com.muguifang.common.exception.exceptions.NoLoginException;
import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.common.utils.Base64Util;
import com.muguifang.mapper.TUserMapper;
import com.muguifang.po.TUser;
import com.muguifang.po.TUserExample;
import com.muguifang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description muguifang
 * @Date 2020/2/16 15:44
 * @Description 用户业务实现类
 */
@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private UserService userService;

    public TUser login(TUser tUser) {
        if(null == tUser){
            log.error("获取用户信息异常!{}","异常方法:login()");
            throw new ParamException(501, "获取用户信息异常!");
        }
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(tUser.getUsername());
        List<TUser> tUsers = tUserMapper.selectByExample(example);
        //tUsers为空表示用户不存在
        if(null == tUsers || 0 >= tUsers.size()){
            throw new NoDataException(400, "该用户不存在!");
        }
        TUser user = tUsers.get(0);
        //判断密码
        if(!user.getPassword().equals(tUser.getPassword())){
            throw new NoLoginException(502, "密码错误!");
        }
        if(user.getRole() == 1){
            user.setHeadpic(Base64Util.base64Convert(user.getHeadpic()));
        }
        return user;
    }

    public void updatePass(Map<String, Object> param) {
        //判断旧密码是否接收
        if(StringUtils.isBlank((String)param.get("oldPassword"))){
            throw new ParamException(501, "获取旧密码异常");
        }
        //判断新密码是否接收
        if(StringUtils.isBlank((String)param.get("newPassword"))){
            throw new ParamException(501, "获取新密码异常");
        }
        //判断确认密码是否接收
        if(StringUtils.isBlank((String)param.get("confirmPassword"))){
            throw new ParamException(501, "获取确认密码异常");
        }
        //获取当前登录用户
        TUser currentLoginEmployee = userService.getCurrentLoginEmployee();
        if(null == currentLoginEmployee){
            throw new NoLoginException(401, "登录失效!");
        }
        //判断新密码与确认密码是否相同
        String confirmPassword = (String) param.get("confirmPassword");
        String newPassword = (String) param.get("newPassword");
        if(!confirmPassword.equals(newPassword)){
            throw new LoginException(501, "新密码与确认密码不一致!");
        }
        //判断旧密码是否正确
//        if(!currentLoginEmployee.getPassword().equals((String)param.get("oldPassword"))){
//            throw new LoginException(501, "旧密码错误!");
//        }
        currentLoginEmployee.setPassword((String)param.get("newPassword"));
        tUserMapper.updateByPrimaryKey(currentLoginEmployee);
    }

    @Override
    public void register(TUser tUser) {
        tUser.setRole(1);
        tUser.setHeadpic("D:/img/photo.png");
        tUserMapper.insert(tUser);
    }

    @Override
    public List<TUser> getAllUser(String username) {
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        criteria.andRoleEqualTo(1);
        if(null != username && !"".equals(username)){
            criteria.andUsernameLike("%" + username + "%");
        }
        List<TUser> tUsers = tUserMapper.selectByExample(example);
        return tUsers;
    }

    @Override
    public void updateUser(TUser tUser) {
        tUserMapper.updateByPrimaryKeySelective(tUser);
    }

    @Override
    public void deleteUser(List<Integer> ids) {
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        tUserMapper.deleteByExample(example);
    }

    @Override
    public TUser getUserInfo(Integer id) {
        TUser tUser = tUserMapper.selectByPrimaryKey(id);
        tUser.setHeadpic(Base64Util.base64Convert(tUser.getHeadpic()));
        return tUser;
    }

}
