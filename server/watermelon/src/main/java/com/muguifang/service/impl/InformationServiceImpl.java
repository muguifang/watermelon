package com.muguifang.service.impl;

import com.muguifang.common.exception.exceptions.NoDataException;
import com.muguifang.common.utils.Base64Util;
import com.muguifang.common.utils.FileUtil;
import com.muguifang.mapper.TInformationMapper;
import com.muguifang.po.TInformation;
import com.muguifang.po.TInformationExample;
import com.muguifang.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        example.setOrderByClause("insertDate DESC");
        if(title != null && !"".equals(title)){
            TInformationExample.Criteria criteria = example.createCriteria();
            criteria.andTitleLike("%" + title + "%");
        }
        List<TInformation> tInformations = tInformationMapper.selectByExample(example);
        if(tInformations == null || 0 >= tInformations.size()){
            throw new NoDataException(400, "资讯信息为空!");
        }
        for(TInformation tInformation : tInformations){
            String path = tInformation.getPic();
            tInformation.setPic(Base64Util.base64Convert(path));
        }
        return tInformations;
    }

    @Override
    public void updateInfo(TInformation tInformation) {
        String filePath =  tInformation.getPic();
        if(filePath != null && !"".equals(filePath)){
            tInformation.setPic("D:/img/"+filePath);
        }
        tInformationMapper.updateByPrimaryKeySelective(tInformation);
    }

    @Override
    public void deleteInfo(List<Integer> ids) {
        TInformationExample example = new TInformationExample();
        TInformationExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        List<TInformation> tInformations = tInformationMapper.selectByExample(example);
        //删除图片文件
        for(TInformation tInformation : tInformations){
            String file = tInformation.getPic();
            FileUtil.deleteFile(file);
        }
        tInformationMapper.deleteByExample(example);
    }

    @Override
    public void addInfo(TInformation tInformation) {
        String param = tInformation.getPic();
        tInformation.setPic("D:/img/"+param);
        tInformation.setInsertdate(new Date());
        tInformationMapper.insert(tInformation);
    }
}
