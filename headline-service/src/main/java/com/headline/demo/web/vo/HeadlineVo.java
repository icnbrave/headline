package com.headline.demo.web.vo;

public class HeadlineVo {
  
  private Integer headlinePk;

  private String name;

  private String keywords; // Query for title or description

  private Short deleteFlag;

  private Short selectFlag;
  
  private Short flag;
  
  private String author;
  
  private String seperator;

  public Integer getHeadlinePk() {
    return headlinePk;
  }

  public void setHeadlinePk(Integer headlinePk) {
    this.headlinePk = headlinePk;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Short getDeleteFlag() {
    return deleteFlag;
  }

  public void setDeleteFlag(Short deleteFlag) {
    this.deleteFlag = deleteFlag;
  }

  public Short getSelectFlag() {
    return selectFlag;
  }

  public void setSelectFlag(Short selectFlag) {
    this.selectFlag = selectFlag;
  }

  public Short getFlag() {
    return flag;
  }

  public void setFlag(Short flag) {
    this.flag = flag;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getSeperator() {
    return seperator;
  }

  public void setSeperator(String seperator) {
    this.seperator = seperator;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }
  
}
