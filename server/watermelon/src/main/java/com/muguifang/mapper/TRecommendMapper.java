package com.muguifang.mapper;

import com.muguifang.po.TRecommend;
import com.muguifang.po.TRecommendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRecommendMapper {
    int countByExample(TRecommendExample example);

    int deleteByExample(TRecommendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRecommend record);

    int insertSelective(TRecommend record);

    List<TRecommend> selectByExample(TRecommendExample example);

    TRecommend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRecommend record, @Param("example") TRecommendExample example);

    int updateByExample(@Param("record") TRecommend record, @Param("example") TRecommendExample example);

    int updateByPrimaryKeySelective(TRecommend record);

    int updateByPrimaryKey(TRecommend record);
}