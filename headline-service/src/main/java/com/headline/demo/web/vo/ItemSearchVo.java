package com.headline.demo.web.vo;

public class ItemSearchVo<T> {
  private int currentPage;
  private int pageSize;
  private String orderBy;
  private T condition;

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public T getCondition() {
    return condition;
  }

  public void setCondition(T condition) {
    this.condition = condition;
  }


}
