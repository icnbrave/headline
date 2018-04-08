package com.headline.demo.web.vo;

import java.util.List;

public class HeadlineContrVo {

  private List<Integer> headlinePks;
  
  private String keywords;
  
  private Integer number; // 有多少句话组成一个文案

  public List<Integer> getHeadlinePks() {
    return headlinePks;
  }

  public void setHeadlinePks(List<Integer> headlinePks) {
    this.headlinePks = headlinePks;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }
  
}
