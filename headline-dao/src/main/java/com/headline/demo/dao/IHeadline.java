package com.headline.demo.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.headline.demo.mapper.entity.Headline;
import com.headline.demo.mapper.entity.HeadlineCriteria;


public interface IHeadline {

  public List<Headline> queryOnePageDataByCondition(Integer currentPage, Integer pageSize,
      HeadlineCriteria modelCrt);

  public List<Headline> selectByExample(HeadlineCriteria example);

  public int updateByExampleSelectiveExclusiveWithUpdateTime(Headline record,
      HeadlineCriteria example);

  public Headline selectByPrimaryKey(Integer headlinePk);

  public int insertHeadine(Headline record);

  public int countByExample(HeadlineCriteria example);

  public List<Headline> selectByExampleWithRowbounds(HeadlineCriteria example, RowBounds rowBounds);

  public int updateByPrimaryKey(Headline record);
}
