package com.headline.demo.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.eg.egsc.framework.dao.base.BaseDao;
import com.eg.egsc.framework.paging.PageUtils;
import com.headline.demo.dao.common.DarenDaoUtil;
import com.headline.demo.extmapper.HeadlineExtMapper;
import com.headline.demo.mapper.HeadlineMapper;
import com.headline.demo.mapper.entity.Headline;
import com.headline.demo.mapper.entity.HeadlineCriteria;

@Repository
public class HeadlineDao extends BaseDao<HeadlineMapper, HeadlineExtMapper, Headline>
    implements IHeadline {

  private static final Logger LOGGER = LoggerFactory.getLogger(HeadlineDao.class);

  public HeadlineDao() {
    this.setMapperClass();
    this.setEntityClass();
  }

  @Override
  protected void setEntityClass() {
    this.setEntityClass(Headline.class);
  }

  @Override
  protected void setMapperClass() {
    this.setMapperClass(HeadlineMapper.class);
    this.setExtMapperClass(HeadlineExtMapper.class);
  }

  @Override
  public List<Headline> queryOnePageDataByCondition(Integer currentPage, Integer pageSize, HeadlineCriteria modelCrt) {
    RowBounds rowBounds =
        new RowBounds(PageUtils.getOffset(currentPage, pageSize), PageUtils.getLimit(pageSize));
    return this.getMapper().selectByExampleWithRowbounds(modelCrt, rowBounds);
  }

  @Override
  public List<Headline> selectByExample(HeadlineCriteria example) {
    String methodName = "selectByExample";
    DarenDaoUtil.printBeginLog(LOGGER, this.getClass().getName(), methodName);
    List<Headline> res = this.getMapper().selectByExample(example);
    DarenDaoUtil.printBeginLog(LOGGER, this.getClass().getName(), methodName);
    return res;
  }

  @Override
  public int updateByExampleSelectiveExclusiveWithUpdateTime(Headline record,
      HeadlineCriteria example) {
    String methodName = "updateByExampleSelectiveExclusiveWithUpdateTime";
    DarenDaoUtil.printBeginLog(LOGGER, this.getClass().getName(), methodName);
    int res = this.getExtMapper().updateByExampleSelectiveExclusiveWithUpdateTime(record, example);
    DarenDaoUtil.printEndLog(LOGGER, this.getClass().getName(), methodName);
    return res;
  }

  @Override
  public Headline selectByPrimaryKey(Integer headlinePk) {
    String methodName = "selectByPrimaryKey";
    DarenDaoUtil.printBeginLog(LOGGER, this.getClass().getName(), methodName);
    Headline res = this.getMapper().selectByPrimaryKey(headlinePk);
    DarenDaoUtil.printEndLog(LOGGER, this.getClass().getName(), methodName);
    return res;
  }

  @Override
  public int insertHeadine(Headline record) {
    String methodName = "selectByNameOrCode";
    DarenDaoUtil.printBeginLog(LOGGER, this.getClass().getName(), methodName);
    int res = this.getMapper().insert(record);
    DarenDaoUtil.printEndLog(LOGGER, this.getClass().getName(), methodName);
    return res;
  }

  @Override
  public int countByExample(HeadlineCriteria example) {
    String methodName = "countByExample";
    DarenDaoUtil.printBeginLog(LOGGER, this.getClass().getName(), methodName);
    long res = this.getMapper().countByExample(example);
    DarenDaoUtil.printEndLog(LOGGER, this.getClass().getName(), methodName);
    return (int) res;
  }

  @Override
  public List<Headline> selectByExampleWithRowbounds(HeadlineCriteria example,
      RowBounds rowBounds) {
    String methodName = "countByExample";
    DarenDaoUtil.printBeginLog(LOGGER, this.getClass().getName(), methodName);
     List<Headline> res = this.getMapper().selectByExampleWithRowbounds(example, rowBounds);
    DarenDaoUtil.printEndLog(LOGGER, this.getClass().getName(), methodName);
    return res;
  }

  @Override
  public int updateByPrimaryKey(Headline record) {
    String methodName = "countByExample";
    DarenDaoUtil.printBeginLog(LOGGER, this.getClass().getName(), methodName);
    long res = this.getMapper().updateByPrimaryKey(record);
    DarenDaoUtil.printEndLog(LOGGER, this.getClass().getName(), methodName);
    return (int) res;
  }


}
