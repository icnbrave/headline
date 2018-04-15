package com.headline.demo.web.vo;

import java.util.List;

public class ExportHeadlineVo {
  private List<Integer> headlinePks;
  private Boolean isAllSelect;
  public List<Integer> getHeadlinePks() {
    return headlinePks;
  }
  public void setHeadlinePks(List<Integer> headlinePks) {
    this.headlinePks = headlinePks;
  }
  public Boolean getIsAllSelect() {
    return isAllSelect;
  }
  public void setIsAllSelect(Boolean isAllSelect) {
    this.isAllSelect = isAllSelect;
  }
  @Override
  public String toString() {
    return "ExportHeadlineVo [headlinePks=" + headlinePks + ", isAllSelect=" + isAllSelect + "]";
  }
}
