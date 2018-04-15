package com.headline.demo.dao.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

public class HeadlineBaseDaoUtil {

  protected HeadlineBaseDaoUtil() {}

  public static void printBeginLog(Logger logger, String className, String methodName) {
    if (logger.isDebugEnabled()) {
      logger.debug(buildBeginEngLog(className, methodName, "BEGIN..."));
    }
  }

  public static void printEndLog(Logger logger, String className, String methodName) {
    if (logger.isDebugEnabled()) {
      logger.debug(buildBeginEngLog(className, methodName, "END..."));
    }
  }

  private static String buildBeginEngLog(String className, String methodName, String str) {
    return new StringBuilder().append(className).append("::").append(methodName).append(":")
        .append("[").append("]").append(str).toString();
  }

  public static void printAndThrowErrorException(Logger logger, String className, String methodName,
      String errorCode, String errorMsg, Exception e) throws HeadlineException {
    if (logger.isErrorEnabled()) {
      logger.error(new StringBuilder().append(className).append("::").append(methodName).append(":")
          .append("[").append("]").append(StringUtils.trimToEmpty(errorCode)).append(" ")
          .append((e != null ? e.toString() : StringUtils.trimToEmpty(errorMsg))).toString());
    }

    StringBuilder eSb =
        new StringBuilder().append("[").append("]").append(e != null ? e.getMessage() : errorMsg);

    throw new HeadlineException(errorCode, eSb.toString(), new String[] {eSb.toString()}, e);
  }

  public static void printAndThrowErrorException(Logger logger, String className, String methodName,
      String errorCode) throws HeadlineException {
    if (logger.isErrorEnabled()) {
      logger.error(new StringBuilder().append(className).append("::").append(methodName).append(":")
          .append("[").append("]").append(StringUtils.trimToEmpty(errorCode)).toString());
    }
    throw new HeadlineException(errorCode);
  }

  public static void printDebugLog(Logger logger, String className, String methodName, String msg) {
    if (logger.isWarnEnabled()) {
      logger.warn(new StringBuilder().append(className).append("::").append(methodName).append(":")
          .append("[").append("]").append(msg).toString());
    }
  }

  public static void printWarnLog(Logger logger, String className, String methodName,
      String errorCode, String errorMsg, Exception e) {
    if (logger.isWarnEnabled()) {
      logger.warn(buildWarnErrorLog(className, methodName, errorCode, errorMsg, e));
    }
  }

  public static void printErrorLog(Logger logger, String className, String methodName,
      String errorCode, String errorMsg, Exception e) {
    if (logger.isErrorEnabled()) {
      logger.error(buildWarnErrorLog(className, methodName, errorCode, errorMsg, e));
    }
  }

  private static String buildWarnErrorLog(String className, String methodName, String errorCode,
      String errorMsg, Exception e) {
    return new StringBuilder().append(className).append("::").append(methodName).append(":")
        .append("[").append("]").append(StringUtils.trimToEmpty(errorCode)).append(" ")
        .append((e != null ? e.toString() : StringUtils.trimToEmpty(errorMsg))).toString();
  }
}
