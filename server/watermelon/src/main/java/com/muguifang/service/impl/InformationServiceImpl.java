package com.muguifang.service.impl;

import com.muguifang.common.exception.exceptions.NoDataException;
import com.muguifang.mapper.TInformationMapper;
import com.muguifang.po.TInformation;
import com.muguifang.po.TInformationExample;
import com.muguifang.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description muguifang
 * @Date 2020/2/19 13:38
 * @Description
 */
@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    private TInformationMapper tInformationMapper;
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
            String pic = "";
            if(picStr != null){
                pic = picStr.substring(picStr.lastIndexOf("\\")+1,picStr.length());
                tInformation.setPic("@/assets/"+ pic);
            }
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
}
