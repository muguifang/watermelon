package com.muguifang.service.impl;

import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.mapper.TAdviseMapper;
import com.muguifang.mapper.myMapper.TMyAdviseMapper;
import com.muguifang.po.TAdvise;
import com.muguifang.po.TAdviseExample;
import com.muguifang.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description muguifang
 * @Date 2020/2/24 16:51
 * @Description
 */
@Service
public class AdviceServiceImpl implements AdviceService {
    @Autowired
    private TAdviseMapper tAdviseMapper;
    @Autowired
    private TMyAdviseMapper tMyAdviseMapper;

//    @Override
//    public void addAdvice(TAdvise tAdvise) {
//        tAdviseMapper.insert(tAdvise);
//    }

    @Override
    public void deleteAdvice(List<Integer> ids) {
        TAdviseExample example = new TAdviseExample();
        TAdviseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        tAdviseMapper.deleteByExample(example);
    }

    @Override
    public List<Map<String, Object>> getAdviceByConditions(String conditions) {
//        TAdviseExample example = new TAdviseExample();
//        if(conditions != null && !"".equals(conditions)){
//            TAdviseExample.Criteria criteria = example.createCriteria();
//            criteria.andPhoneLike("%" + conditions + "%");
//        }
//        List<TAdvise> tAdvises = tAdviseMapper.selectByExample(example);
        List<Map<String, Object>> allAdviseInfo = tMyAdviseMapper.getAllAdviseInfo(conditions);
        return allAdviseInfo;
    }

    @Override
    public void addAdvice(Map<String, Object> param) {
        if(param.get("u_id") == null || "".equals(param.get("u_id"))){
            throw new ParamException(501, "参数异常");
        }
        if(param.get("content") == null || "".equals(param.get("content"))){
            throw new ParamException(501, "参数异常");
        }
        if(param.get("phone") == null || "".equals(param.get("phone"))){
            throw new ParamException(501, "参数异常");
        }
        Integer uId = (Integer) param.get("u_id");
        String content = (String) param.get("content");
        String phone = (String)param.get("phone");
        TAdvise tAdvise = new TAdvise();
        tAdvise.setId(0);
        tAdvise.setUserId(uId);
        tAdvise.setContent(content);
        tAdvise.setPhone(phone);
        tAdvise.setInsertdate(new Date());
        tAdviseMapper.insert(tAdvise);
    }
}
