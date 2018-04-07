package com.headline.demo.web;

import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.service.base.web.BaseWebController;
import com.headline.demo.constant.ErrorCodeConstant;

public abstract class HeadlineBaseWebController extends BaseWebController {

  public ResponseDto getDefaultResponseDto() {
    return new ResponseDto(ErrorCodeConstant.HEADLINE_OP_CODE_SUCCESS, null, "");
  }
}
