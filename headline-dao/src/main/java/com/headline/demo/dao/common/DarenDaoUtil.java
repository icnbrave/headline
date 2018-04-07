package com.headline.demo.dao.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

public class DarenDaoUtil {
  private DarenDaoUtil() {}

  public static void printBeginLog(Logger logger, String className, String methodName) {
    logger.debug(buildBeginEngLog(className, methodName, "BEGIN..."));
  }

  public static void printEndLog(Logger logger, String className, String methodName) {
    logger.debug(buildBeginEngLog(className, methodName, "END..."));
  }
  
  private static String buildBeginEngLog(String className, String methodName, String str) {
    return new StringBuilder().append(className).append("::").append(methodName).append(":")
        .append(str).toString();
  }

  public static void printWarnLog(Logger logger, String className, String methodName,
      String warnMsg) {
    logger.warn(new StringBuilder().append(className).append("::").append(methodName).append(":")
        .append(warnMsg).toString());
  }

  public static void printAndThrowErrorException(Logger logger, String className, String methodName,
      String errorCode, String errorMsg) throws DarenException {
    logger.error(new StringBuilder().append(className).append("::").append(methodName).append(":")
        .append("[").append(StringUtils.trimToEmpty(errorCode)).append("]")
        .append(StringUtils.trimToEmpty(errorMsg)).toString());
    throw new DarenException(errorCode, errorMsg);
  }

  public static List<String> splitStringIntoList(String searchStr, String ch) {
    List<String> retList = new ArrayList<>();
    String[] tmpArray = StringUtils.split(searchStr, ch);
    if (tmpArray != null) {
      for (String tmpStr : tmpArray) {
        if (StringUtils.isNotBlank(StringUtils.stripToEmpty(tmpStr))) {
          retList.add(StringUtils.stripToEmpty(tmpStr));
        }
      }
    }
    return retList;
  }
}
