package com.headline.demo.web.vo;

import java.util.List;

import com.headline.demo.mapper.entity.Headline;

public class HeadlinePageVo {
	private int pageCount;
	private List<Headline> headlines;
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<Headline> getHeadlines() {
		return headlines;
	}
	public void setHeadlines(List<Headline> headlines) {
		this.headlines = headlines;
	}
}
