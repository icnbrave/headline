package com.headline.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import com.eg.egsc.framework.service.core.ApplicationStarter;

@SpringBootApplication
@ComponentScan(basePackages = {"com.headline"})
@MapperScan(basePackages = {"com.headline.demo"})
public class HeadlineServiceApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(HeadlineServiceApplication.class);
  }

  @SuppressWarnings("static-access")
  public static void main(String[] args) {
    new ApplicationStarter().run(HeadlineServiceApplication.class, args);
  }

}
