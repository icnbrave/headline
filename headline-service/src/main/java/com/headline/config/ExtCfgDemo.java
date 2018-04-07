package com.headline.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eg.egsc.framework.service.util.SpringEnvUtil;

@Component
public class ExtCfgDemo {
	
	@Autowired
	private SpringEnvUtil springEnvUtil;

	public void printMyProperty() {
	 springEnvUtil.getProperty("abc");
	}
}
