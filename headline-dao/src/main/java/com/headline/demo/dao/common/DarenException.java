package com.headline.demo.dao.common;

import com.eg.egsc.common.exception.CommonException;

public class DarenException extends CommonException {

  private static final long serialVersionUID = 1L;

  public DarenException(String code) {
    super(code);
  }

  public DarenException(String code, String message, Object[] values, Throwable cause) {
    super(code, message, values, cause);
  }

  public DarenException(String code, String message) {
    super(code, message);
  }
}
