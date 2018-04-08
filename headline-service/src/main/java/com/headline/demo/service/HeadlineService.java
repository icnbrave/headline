package com.headline.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.headline.demo.mapper.entity.Headline;
import com.headline.demo.mapper.entity.HeadlineCriteria;
import com.headline.demo.web.vo.HeadlineContrVo;
import com.headline.demo.web.vo.HeadlinePageVo;
import com.headline.demo.web.vo.HeadlineVo;
import com.headline.demo.web.vo.ItemSearchVo;

public interface HeadlineService {

  public Headline getHeadline(Integer id);

  int insert(Headline headline);

  int countByExample(HeadlineCriteria example);

  Headline selectByPrimaryKey(Integer id);

  int deleteByPrimaryKey(Integer id);

  int updateByPrimaryKey(Headline record);

  HeadlinePageVo queryOnePageDataByCondition(ItemSearchVo<HeadlineVo> itemSearchVo);
  
  int updateByExampleSelective(Headline record, HeadlineCriteria example);

  int updateByExample(Headline record, HeadlineCriteria example);
  
  List<Headline> uploadHeadlineFileAndReturnSplitedHeadlines(MultipartFile file);
  
  List<Headline> splitHeadlinesAndReturnWithFirstPage(String sep, Integer pageSize);
  
  List<Headline> constructHeadlinesAndReturn(HeadlineContrVo contrVo);

}
