package com.headline.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.headline.demo.constant.ErrorCodeConstant;
import com.headline.demo.dao.HeadlineDao;
import com.headline.demo.mapper.entity.Headline;
import com.headline.demo.mapper.entity.HeadlineCriteria;
import com.headline.demo.mapper.entity.HeadlineCriteria.Criteria;
import com.headline.demo.service.HeadlineService;
import com.headline.demo.util.HeadlineBaseUtil;
import com.headline.demo.web.vo.HeadlinePageVo;
import com.headline.demo.web.vo.HeadlineVo;
import com.headline.demo.web.vo.ItemSearchVo;

@Service
public class HeadlineServiceImpl implements HeadlineService {

  private static final Logger logger = LoggerFactory.getLogger(HeadlineServiceImpl.class);

  @Autowired
  private HeadlineDao headlineDao;

  @Override
  public Headline getHeadline(Integer id) {
    return headlineDao.getMapper().selectByPrimaryKey(id);
  }

  @Override
  @Transactional
  public int insert(Headline headline) {
    return headlineDao.getMapper().insert(headline);
  }

  @Override
  @Transactional(readOnly = true)
  public int countByExample(HeadlineCriteria example) {
    return (int) headlineDao.getMapper().countByExample(example);
  }

  @Override
  @Transactional(readOnly = true)
  public Headline selectByPrimaryKey(Integer id) {
    return headlineDao.getMapper().selectByPrimaryKey(id);
  }

  @Override
  @Transactional
  public int deleteByPrimaryKey(Integer id) {
    return headlineDao.getMapper().deleteByPrimaryKey(id);
  }

  @Override
  @Transactional
  public int updateByPrimaryKey(Headline record) {
    return headlineDao.getMapper().updateByPrimaryKey(record);
  }

  @Override
  @Transactional
  public int updateByExampleSelective(Headline record, HeadlineCriteria example) {
    return headlineDao.getMapper().updateByExampleSelective(record, example);
  }

  @Override
  @Transactional
  public int updateByExample(Headline record, HeadlineCriteria example) {
    return headlineDao.getMapper().updateByExample(record, example);
  }

  @Override
  public HeadlinePageVo queryOnePageDataByCondition(ItemSearchVo<HeadlineVo> searchVo) {
    String methodName = "queryOnePageDataByCondition";
    if (searchVo == null || searchVo.getCondition() == null) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName,
          ErrorCodeConstant.HEADLINE_SERVICE_PARAM_BLANK);
    }
    HeadlineCriteria criteria = new HeadlineCriteria();
    HeadlineCriteria.Criteria crr = criteria.createCriteria();
    convertHeadlineCondition2Criteria(crr, searchVo.getCondition());

    List<Headline> headlines = headlineDao.queryOnePageDataByCondition(searchVo.getCurrentPage(),
        searchVo.getPageSize(), criteria);
    
    int count = this.countByExample(criteria);

    HeadlinePageVo pageVo = new HeadlinePageVo();
    
    pageVo.setPageCount(count);
    pageVo.setHeadlines(headlines);
    return pageVo;
  }

  private void convertHeadlineCondition2Criteria(Criteria criteria, HeadlineVo headlineVo) {
    if (headlineVo == null) {
      return;
    }

    if (headlineVo.getHeadlinePk() != null) {
      criteria.andHeadlinePkEqualTo(headlineVo.getHeadlinePk());
    }
    if (headlineVo.getDeleteFlag() != null) {
      criteria.andDeleteFlagEqualTo(headlineVo.getDeleteFlag());
    }
    if (headlineVo.getName() != null) {
      criteria.andNameEqualTo(headlineVo.getName());
    }
    if (headlineVo.getSelectFlag() != null) {
      criteria.andSelectFlagEqualTo(headlineVo.getSelectFlag());
    }
    if (headlineVo.getAuthor() != null) {
      criteria.andAuthorEqualTo(headlineVo.getAuthor());
    }
    if (headlineVo.getTitle() != null) {
      criteria.andTitleEqualTo(headlineVo.getTitle());
    }
    if (headlineVo.getFlag() != null) {
      criteria.andFlagEqualTo(headlineVo.getFlag());
    }
    if (headlineVo.getDescription() != null) {
      String[] tokens = headlineVo.getDescription().trim().split(" ");
      for (String keyword : tokens) {
        criteria.andDescriptionLike("%"+keyword+"%");
      }
    }
  }

  @Override
  @Transactional
  public List<Headline> uploadHeadlineFileAndReturnSplitedHeadlines(MultipartFile file) {
    String methodName = "uploadHeadlineFileAndReturnSplitedHeadlines";
    HeadlineBaseUtil.printBeginLog(logger, this.getClass().getName(), methodName);
    
    
    
    HeadlineBaseUtil.printEndLog(logger, this.getClass().getName(), methodName);
    return null;
  }

}
