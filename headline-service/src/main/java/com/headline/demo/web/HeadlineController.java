
package com.headline.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eg.egsc.common.component.utils.BeanUtils;
import com.eg.egsc.common.component.utils.JsonUtil;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.headline.demo.constant.ErrorCodeConstant;
import com.headline.demo.mapper.entity.Headline;
import com.headline.demo.service.HeadlineService;
import com.headline.demo.util.HeadlineBaseUtil;
import com.headline.demo.web.vo.HeadlinePageVo;
import com.headline.demo.web.vo.HeadlineVo;
import com.headline.demo.web.vo.ItemSearchVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/headline")
public class HeadlineController extends HeadlineBaseWebController {

  protected final Logger logger = LoggerFactory.getLogger(HeadlineController.class);

  @Autowired
  private HeadlineService headlineServiceImpl;

  /**
   * 根据id获取头条对象
   * 
   * @param id 查询条件
   * @return HealineVo 返回的VO对象
   */
  @ApiOperation("根据id获取头条对象")
  @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
  public HeadlineVo getUser(@PathVariable("id") Integer id) {

    HeadlineVo headlineVo = new HeadlineVo();
    Headline headline = headlineServiceImpl.getHeadline(id);
    if (headline != null) {
      BeanUtils.copyProperties(headlineVo, headline);
    }
    return headlineVo;
  }


  /**
   * 此方法用于分页查询
   * 
   * @param currentPage 当前页码数
   * @param pageSize 当前页码显示的数据个数
   * @param keywords 分页查询的条件
   * @return HeadlinePageVo 返回的值
   */
  @ApiOperation("此方法用于分页查询")
  @RequestMapping(value = "/queryPageData", method = RequestMethod.POST)
  public ResponseDto queryPageData(@RequestBody ItemSearchVo<HeadlineVo> itemSearchVo) {
    String methodName = "queryPageData";
    logger.debug(new StringBuilder().append(this.getClass().getName()).append("::")
        .append(methodName).append("::").append("PARAM").append(":")
        .append(JsonUtil.toJsonString(itemSearchVo)).toString());
    ResponseDto res = this.getDefaultResponseDto();
    if (itemSearchVo == null) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName, ErrorCodeConstant.HEADLINE_QUERY_KEYWORDS_NOTBLANK);
      return res;
    }
    
    HeadlinePageVo ret = headlineServiceImpl.queryOnePageDataByCondition(itemSearchVo);
    res.setData(ret);
    return res;
  }
  
  @ApiOperation("文件上传与解析")
  @RequestMapping(value="/upload", method=RequestMethod.POST)
  public ResponseDto uploadFileAndExtractHeadlineMessage(@RequestBody MultipartFile file) {
    String methodName = "uploadFileAndExtractHeadlineMessage";
    if (file == null) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName, ErrorCodeConstant.HEADLINE_FILE_UPLOAD_FAIL);
    }
    ResponseDto responseDto = this.getDefaultResponseDto();
    return responseDto;
    
  }

}
