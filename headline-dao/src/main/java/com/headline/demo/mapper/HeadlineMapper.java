package com.headline.demo.mapper;

import com.headline.demo.mapper.entity.Headline;
import com.headline.demo.mapper.entity.HeadlineCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface HeadlineMapper {
    long countByExample(HeadlineCriteria example);

    int deleteByExample(HeadlineCriteria example);

    int deleteByPrimaryKey(Integer headlinePk);

    int insert(Headline record);

    int insertSelective(Headline record);

    List<Headline> selectByExampleWithRowbounds(HeadlineCriteria example, RowBounds rowBounds);

    List<Headline> selectByExample(HeadlineCriteria example);

    Headline selectByPrimaryKey(Integer headlinePk);

    int updateByExampleSelective(@Param("record") Headline record, @Param("example") HeadlineCriteria example);

    int updateByExample(@Param("record") Headline record, @Param("example") HeadlineCriteria example);

    int updateByPrimaryKeySelective(Headline record);

    int updateByPrimaryKey(Headline record);
}