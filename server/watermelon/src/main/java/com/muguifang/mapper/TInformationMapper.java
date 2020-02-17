package com.muguifang.mapper;

import com.muguifang.po.TInformation;
import com.muguifang.po.TInformationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TInformationMapper {
    int countByExample(TInformationExample example);

    int deleteByExample(TInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TInformation record);

    int insertSelective(TInformation record);

    List<TInformation> selectByExample(TInformationExample example);

    TInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TInformation record, @Param("example") TInformationExample example);

    int updateByExample(@Param("record") TInformation record, @Param("example") TInformationExample example);

    int updateByPrimaryKeySelective(TInformation record);

    int updateByPrimaryKey(TInformation record);
}