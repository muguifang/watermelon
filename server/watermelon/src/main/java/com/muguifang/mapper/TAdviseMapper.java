package com.muguifang.mapper;

import com.muguifang.po.TAdvise;
import com.muguifang.po.TAdviseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAdviseMapper {
    int countByExample(TAdviseExample example);

    int deleteByExample(TAdviseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAdvise record);

    int insertSelective(TAdvise record);

    List<TAdvise> selectByExample(TAdviseExample example);

    TAdvise selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAdvise record, @Param("example") TAdviseExample example);

    int updateByExample(@Param("record") TAdvise record, @Param("example") TAdviseExample example);

    int updateByPrimaryKeySelective(TAdvise record);

    int updateByPrimaryKey(TAdvise record);
}