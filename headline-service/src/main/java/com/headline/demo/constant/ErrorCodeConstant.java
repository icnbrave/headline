package com.headline.demo.constant;

import com.eg.egsc.common.constant.CommonConstant;
import com.headline.demo.dao.common.ErrorCodeDaoConstant;

public class ErrorCodeConstant extends ErrorCodeDaoConstant {

  private ErrorCodeConstant() {}


  public static final String HEADLINE_OP_CODE_SUCCESS = CommonConstant.SUCCESS_CODE;
  public static final String HEADLINE_OP_CODE_FAILED = CommonConstant.DEFAULT_SYS_ERROR_CODE;
  public static final String HEADLINE_QUERY_KEYWORDS_NOTBLANK = "headline.query.keywords.notblank";
  public static final String HEADLINE_SERVICE_PARAM_BLANK = "headline.service.param.blank";
  public static final String HEADLINE_FILE_UPLOAD_FAIL = "headline.file.upload.fail";
  public static final String HEADLINE_FILE_READ_FAIL = "headline.file.read.fail";
  public static final String HEADLINE_PK_NOT_SELECTED = "headline.constructed.pk.notselected";
  public static final String HEADLINE_DOWNLOAD_EXCEPTION = "headline.download.exception";
  
}
