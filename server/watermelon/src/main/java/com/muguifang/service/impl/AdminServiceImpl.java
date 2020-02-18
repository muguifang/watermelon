package com.muguifang.service.impl;

import com.muguifang.common.basic.impl.BaseServiceImpl;
import com.muguifang.common.exception.exceptions.LoginException;
import com.muguifang.common.exception.exceptions.NoDataException;
import com.muguifang.common.exception.exceptions.NoLoginException;
import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.mapper.TAdminMapper;
import com.muguifang.mapper.TInformationMapper;
import com.muguifang.mapper.TLbtMapper;
import com.muguifang.mapper.TUserMapper;
import com.muguifang.po.*;
import com.muguifang.service.AdminService;
import com.muguifang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Date 2020/2/17 12:14
 * @Description 管理员业务实现类
 */
@Slf4j
@Service
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {
    @Autowired
    private TLbtMapper tLbtMapper;
    @Autowired
    private TInformationMapper tInformationMapper;
    @Autowired
    private TUserMapper tUserMapper;

    public void addLbt(String path) {
        TLbt tLbt  = new TLbt();
        tLbt.setId(0);
        tLbt.setPic(path);
        tLbtMapper.insert(tLbt);
    }

    @Override
    public List<TInformation> getInfosByTitle(String title) {
        TInformationExample example =  new TInformationExample();
        if(title != null && !"".equals(title)){
            TInformationExample.Criteria criteria = example.createCriteria();
            criteria.andTitleLike("%" + title + "%");
        }
        List<TInformation> tInformations = tInformationMapper.selectByExample(example);
        if(tInformations == null || 0 >= tInformations.size()){
            throw new NoDataException(400, "资讯信息为空!");
        }
        for(TInformation tInformation : tInformations){
            String picStr = tInformation.getPic();
            String pic = picStr.substring(picStr.lastIndexOf("\\")+1,picStr.length());
            tInformation.setPic("@/assets/"+ pic);
        }
        return tInformations;
    }

    @Override
    public void updateInfo(TInformation tInformation) {
        tInformationMapper.updateByPrimaryKey(tInformation);
    }

    @Override
    public void deleteInfo(Integer id) {
        tInformationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void addInfo(TInformation tInformation) {
        tInformationMapper.insert(tInformation);
    }

    @Override
    public List<TUser> getAllUser(Map<String, Object> param) {
        TUserExample example = new TUserExample();
        if(null != param.get("username") && !"".equals(param.get("username"))){
            TUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameLike("%" + (String)param.get("username") + "%");
        }
        List<TUser> tUsers = tUserMapper.selectByExample(example);
        return tUsers;
    }

    @Override
    public void updateUser(TUser tUser) {
        tUserMapper.updateByPrimaryKeySelective(tUser);
    }

    @Override
    public void deleteUser(Integer id) {
        tUserMapper.deleteByPrimaryKey(id);
    }
}
