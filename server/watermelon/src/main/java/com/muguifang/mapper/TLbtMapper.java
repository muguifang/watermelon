package com.muguifang.mapper;

import com.muguifang.po.TLbt;
import com.muguifang.po.TLbtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TLbtMapper {
    int countByExample(TLbtExample example);

    int deleteByExample(TLbtExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TLbt record);

    int insertSelective(TLbt record);

    List<TLbt> selectByExample(TLbtExample example);

    TLbt selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TLbt record, @Param("example") TLbtExample example);

    int updateByExample(@Param("record") TLbt record, @Param("example") TLbtExample example);

    int updateByPrimaryKeySelective(TLbt record);

    int updateByPrimaryKey(TLbt record);
}