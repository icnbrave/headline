package com.headline.demo.constant;

import com.headline.demo.dao.common.HeadlineDaoConstant;

public class HeadlineConstant extends HeadlineDaoConstant {

  private HeadlineConstant() {}
  
  public static final String HEADLINE_DESCRIPTION_SPLIT_DEFAULT_SYMBOL = "。";
  public static final String HEADLINE_QUERY_KEYWORDS_SEP = " ";
  public static final Integer HEADLINE_PAGESIZE_DEFAULT = 10;
  
  public static final String HEADLINE_MOCK_AUTHOR = "模拟作者";
  
  public static final String HEADLINE_MOCK_TITLE = "模拟题目";
  
  public static final String TEXT_ENCODING = "UTF-8";
}
