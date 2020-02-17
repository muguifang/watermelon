package com.muguifang.service.impl;

import com.muguifang.common.basic.impl.BaseServiceImpl;
import com.muguifang.common.exception.exceptions.LoginException;
import com.muguifang.common.exception.exceptions.NoDataException;
import com.muguifang.common.exception.exceptions.NoLoginException;
import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.mapper.TAdminMapper;
import com.muguifang.mapper.TLbtMapper;
import com.muguifang.mapper.TUserMapper;
import com.muguifang.po.TAdmin;
import com.muguifang.po.TLbt;
import com.muguifang.po.TUser;
import com.muguifang.po.TUserExample;
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

    public void addLbt(String path) {
        TLbt tLbt  = new TLbt();
        tLbt.setId(0);
        tLbt.setPic(path);
        tLbtMapper.insert(tLbt);
    }
}
