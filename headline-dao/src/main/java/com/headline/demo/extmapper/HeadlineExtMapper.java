package com.headline.demo.extmapper;

import org.apache.ibatis.annotations.Param;

import com.headline.demo.mapper.entity.Headline;
import com.headline.demo.mapper.entity.HeadlineCriteria;

public interface HeadlineExtMapper {
  
  public int updateByExampleSelectiveExclusiveWithUpdateTime(@Param("record") Headline record, @Param("example") HeadlineCriteria example);

}
