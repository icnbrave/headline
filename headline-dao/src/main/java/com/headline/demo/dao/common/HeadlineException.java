package com.headline.demo.dao.common;

import com.eg.egsc.common.exception.CommonException;

public class HeadlineException extends CommonException {

  private static final long serialVersionUID = 1620521772541012990L;
 
  /**
   * @param code 异常码
   * @param message 异常信息
   * @param values 参数
   * @param cause  异常
   */
  public HeadlineException(String code, String message, Object[] values, Throwable cause) {
    super(code, message, values, cause);
  }

  /**
   * @param code 异常码
   * @param message 异常信息
   * @param values   参数
   */
  public HeadlineException(String code, String message, Object[] values) {
    super(code, message, values);
  }

  /**
   * @param code 异常码
   * @param message 异常信息
   */
  public HeadlineException(String code, String message) {
    super(code, message);
  }

  /**
   * @param code 异常码
   * @param values 参数
   */
  public HeadlineException(String code, Object[] values) {
    super(code, null, values, null);
  }
  
  /**
   * @param code 异常码
   */
  public HeadlineException(String code) {
    super(code);
  }
  
  
}
