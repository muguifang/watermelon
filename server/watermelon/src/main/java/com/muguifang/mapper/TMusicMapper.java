package com.muguifang.mapper;

import com.muguifang.po.TMusic;
import com.muguifang.po.TMusicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMusicMapper {
    int countByExample(TMusicExample example);

    int deleteByExample(TMusicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TMusic record);

    int insertSelective(TMusic record);

    List<TMusic> selectByExample(TMusicExample example);

    TMusic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TMusic record, @Param("example") TMusicExample example);

    int updateByExample(@Param("record") TMusic record, @Param("example") TMusicExample example);

    int updateByPrimaryKeySelective(TMusic record);

    int updateByPrimaryKey(TMusic record);
}