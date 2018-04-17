package com.headline.demo.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.eg.egsc.common.component.utils.BeanUtils;
import com.headline.demo.constant.ErrorCodeConstant;
import com.headline.demo.constant.HeadlineConstant;
import com.headline.demo.dao.HeadlineDao;
import com.headline.demo.mapper.entity.Headline;
import com.headline.demo.mapper.entity.HeadlineCriteria;
import com.headline.demo.mapper.entity.HeadlineCriteria.Criteria;
import com.headline.demo.service.HeadlineService;
import com.headline.demo.util.HeadlineBaseUtil;
import com.headline.demo.util.ReadExcelUtils;
import com.headline.demo.web.vo.HeadlineContrVo;
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
  @Transactional
  public int insertIfDescNotExist(Headline headline) {
    HeadlineCriteria criteria = new HeadlineCriteria();
    criteria.createCriteria().andDescriptionEqualTo(headline.getDescription());
    List<Headline> result = headlineDao.selectByExample(criteria);
    if (CollectionUtils.isEmpty(result)) {
      return headlineDao.getMapper().insert(headline);
    }
    return 0;
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
      criteria.andAuthorLike(headlineVo.getAuthor());
    }
    if (headlineVo.getTitle() != null) {
      if (headlineVo.getTitle().startsWith("%") && headlineVo.getTitle().endsWith("%")) {
        criteria.andTitleLike(headlineVo.getTitle());
      } else {
        criteria.andTitleLike("%" + headlineVo.getTitle() + "%");
      }
    }
    if (headlineVo.getFlag() != null) {
      criteria.andFlagEqualTo(headlineVo.getFlag());
    }
    if (headlineVo.getSeperator() != null) {
      criteria.andSeperatorEqualTo(headlineVo.getSeperator());
    }
    if (headlineVo.getDescription() != null) {
      String[] tokens = headlineVo.getDescription().trim().split(" ");
      for (String keyword : tokens) {
        criteria.andDescriptionLike("%" + keyword + "%");
      }
    }
  }

  @Override
  @Transactional
  public List<Headline> uploadHeadlineFileAndReturnSplitedHeadlines(MultipartFile file) {
    String methodName = "uploadHeadlineFileAndReturnSplitedHeadlines";
    HeadlineBaseUtil.printBeginLog(logger, this.getClass().getName(), methodName);

    ReadExcelUtils excelReader = new ReadExcelUtils(file);
    Map<Integer, Map<Integer, Object>> map = null;
    try {
      map = excelReader.readExcelContent();
    } catch (Exception e) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName,
          ErrorCodeConstant.HEADLINE_FILE_READ_FAIL, e.getMessage(), e);
    }
    if (map == null) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName,
          ErrorCodeConstant.HEADLINE_FILE_READ_FAIL);
    }

    List<Headline> headlines = insertExcelContent2DB(map);

    HeadlineBaseUtil.printEndLog(logger, this.getClass().getName(), methodName);
    return headlines;
  }

  private List<Headline> insertExcelContent2DB(Map<Integer, Map<Integer, Object>> map) {
    List<Headline> headlines = new ArrayList<>();
    if (map == null) {
      return headlines;
    }
    Headline tmpHeadline = new Headline();
    tmpHeadline.setDeleteFlag(HeadlineConstant.HEADLINE_DELETE_FLAG_FALSE);
    tmpHeadline.setSelectFlag(HeadlineConstant.HEADLINE_SELECT_FLAG_INIT);
    tmpHeadline.setFlag(HeadlineConstant.HEADLINE_FLAG_INIT);
    Date now = Calendar.getInstance().getTime();
    tmpHeadline.setCreateTime(now);
    tmpHeadline.setUpdateTime(now);

    for (Integer i : map.keySet()) {
      Map<Integer, Object> map2 = map.get(i);
      String desc = "";
      if (map2 == null) {
        continue;
      }
      for (int j = 0; j < map2.size(); j++) {
        if (j == 0) {
          tmpHeadline.setTitle((String) map2.get(j));
        } else if (j == 1) {
          tmpHeadline.setAuthor((String) map2.get(j));
        } else {

          if (desc.length() > 0
              && !desc.endsWith(HeadlineConstant.HEADLINE_DESCRIPTION_SPLIT_DEFAULT_SYMBOL)) {
            desc.concat(HeadlineConstant.HEADLINE_DESCRIPTION_SPLIT_DEFAULT_SYMBOL);
          }
          desc = desc.concat((String) map2.get(j));

          if (j == map2.size() - 1) {
            tmpHeadline.setDescription(desc);
          }
        }

      }

      if (!checkExist(tmpHeadline)) {
        headlineDao.insertHeadine(tmpHeadline);

        Headline headline = new Headline();
        BeanUtils.copyProperties(headline, tmpHeadline);
        headlines.add(headline);
      }
    }
    return headlines;
  }

  private boolean checkExist(Headline headline) {
    if (headline == null) {
      return true;
    }

    HeadlineCriteria criteria = new HeadlineCriteria();
    criteria.createCriteria().andAuthorEqualTo(headline.getAuthor())
        .andTitleEqualTo(headline.getTitle()).andDescriptionEqualTo(headline.getDescription());

    List<Headline> res = headlineDao.selectByExample(criteria);
    if (res.size() > 0) {
      return true;
    }
    return false;
  }

  @Override
  public List<Headline> splitHeadlinesAndReturnWithFirstPage(String sep, Integer pageSize) {
    String methodName = "splitHeadlinesAndReturnWithFirstPage";
    HeadlineBaseUtil.printBeginLog(logger, this.getClass().getName(), methodName);

    // 1. Get all original headlines
    List<Headline> headlines = headlineDao.getAllOriginHeadlines();

    List<Headline> result = splitHeadlinesAndCreateNewOnes(sep, headlines);

    HeadlineBaseUtil.printEndLog(logger, this.getClass().getName(), methodName);
    if (pageSize == null) {
      pageSize = HeadlineConstant.HEADLINE_PAGESIZE_DEFAULT;
    }
    Integer maxIndex = result.size() > pageSize ? pageSize : result.size();
    return result.subList(0, maxIndex);
  }

  private List<Headline> splitHeadlinesAndCreateNewOnes(String sep, List<Headline> headlines) {
    List<Headline> newHeadlines = new ArrayList<>();
    if (headlines == null || headlines.size() < 1) {
      return newHeadlines;
    }
    Headline record = new Headline();
    for (Headline headline : headlines) {
      String desc = headline.getDescription();

      BeanUtils.copyProperties(record, headline);
      record.setFlag(HeadlineConstant.HEADLINE_FLAG_SPLITED);
      record.setSeperator(sep);

      if (desc != null && StringUtils.isNotBlank(desc)) {
        String[] tokens = desc.split(sep);

        for (int i = 0; i < tokens.length; i++) {
          record.setDescription(tokens[i]);
          headlineDao.insertHeadine(record);

          Headline tmp = new Headline();
          BeanUtils.copyProperties(tmp, record);
          newHeadlines.add(tmp);
        }
      }
    }

    return newHeadlines;
  }

  @Override
  public List<Headline> constructHeadlinesAndReturn(HeadlineContrVo contrVo) {
    String methodName = "constructHeadlinesAndReturn";
    List<Headline> headlinesContructed = new ArrayList<>();

    if (contrVo == null) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName,
          ErrorCodeConstant.HEADLINE_SERVICE_PARAM_BLANK);
    }


    while (true) {
      List<Integer> randomPks =
          this.randomHeadlinePks(contrVo.getHeadlinePks(), contrVo.getNumber());
      if (CollectionUtils.isEmpty(randomPks)) {
        break;
      }

      Headline ret = constructOneHeadlineWithSpecPks(randomPks, contrVo.getSep());
      if (ret != null) {
        headlinesContructed.add(ret);
      }
    }

    return headlinesContructed;
  }

  private List<Integer> randomHeadlinePks(List<Integer> headlinePks, Integer indexCount) {
    List<Integer> randomPks = new ArrayList<>();
    if (headlinePks == null) {
      return headlinePks;
    }
    if (headlinePks.size() <= indexCount) {
      randomPks.addAll(headlinePks);
      headlinePks.clear();
      return randomPks;
    }

    Random random = new Random();
    for (int i = 0; i < indexCount; i++) {
      int index = random.nextInt(headlinePks.size() - 1);
      randomPks.add(headlinePks.get(index));
      headlinePks.remove(index);
    }
    return randomPks;
  }

  private Headline constructOneHeadlineWithSpecPks(List<Integer> randomPks, String sep) {
    if (randomPks == null) {
      return null;
    }
    if (sep == null) {
      sep = HeadlineConstant.HEADLINE_DESCRIPTION_SPLIT_DEFAULT_SYMBOL;
    }

    String desc = "";
    int idx = 0;
    for (Integer pk : randomPks) {
      idx++;
      Headline result = headlineDao.selectByPrimaryKey(pk);
      if (result == null) {
        continue;
      }
      desc = desc.concat(result.getDescription());
      if (!desc.endsWith(sep) && idx < randomPks.size()) {
        desc = desc.concat(sep);
      } else if (idx == randomPks.size()
          && !desc.endsWith(HeadlineConstant.HEADLINE_DESCRIPTION_SPLIT_DEFAULT_SYMBOL)) {
        desc = desc.concat(HeadlineConstant.HEADLINE_DESCRIPTION_SPLIT_DEFAULT_SYMBOL);
      }

      result.setSelectFlag(HeadlineConstant.HEADLINE_SELECT_FLAG_SELECTED);
      result.setUpdateTime(Calendar.getInstance().getTime());
      headlineDao.updateByPrimaryKey(result);
    }

    if (desc.length() == 0) {
      return null;
    }

    Headline res = new Headline();
    res.setTitle(HeadlineConstant.HEADLINE_MOCK_TITLE);
    res.setAuthor(HeadlineConstant.HEADLINE_MOCK_AUTHOR);
    res.setDescription(desc);
    res.setDeleteFlag(HeadlineConstant.HEADLINE_DELETE_FLAG_FALSE);
    res.setFlag(HeadlineConstant.HEADLINE_FLAG_CONSTRUCTED);
    res.setUpdateTime(Calendar.getInstance().getTime());
    res.setCreateTime(Calendar.getInstance().getTime());
    headlineDao.insertHeadine(res);
    return res;
  }

  @Override
  @Transactional
  public void batchDelete(List<Integer> headlinePks) {
    headlinePks.forEach(pk -> {
      headlineDao.solfDeleteByPk(pk);
    });
  }

  @Override
  @Transactional
  public void solfItemDelte(Integer headlinePk) {
    headlineDao.solfDeleteByPk(headlinePk);
  }

  @Override
  @Transactional
  public List<Headline> getAllConstructedHeadlines() {
    HeadlineCriteria criteria = new HeadlineCriteria();
    criteria.createCriteria().andFlagEqualTo(HeadlineConstant.HEADLINE_FLAG_CONSTRUCTED)
        .andDeleteFlagEqualTo(HeadlineConstant.HEADLINE_DELETE_FLAG_FALSE);
    return headlineDao.selectByExample(criteria);
  }

  @Override
  @Transactional
  public List<Headline> getContructedHeadlinesWithSpecPks(List<Integer> headlinePks) {
    List<Headline> headlines = new ArrayList<>();
    if (headlinePks == null) {
      return headlines;
    }
    headlinePks.forEach(headlinePk -> {
      Headline record = headlineDao.selectByPrimaryKey(headlinePk);
      if (record != null) {
        headlines.add(record);
      }
    });
    return headlines;
  }


}
