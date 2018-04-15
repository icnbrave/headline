
package com.headline.demo.web;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eg.egsc.common.component.utils.BeanUtils;
import com.eg.egsc.common.component.utils.JsonUtil;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.headline.demo.constant.ErrorCodeConstant;
import com.headline.demo.constant.HeadlineConstant;
import com.headline.demo.mapper.entity.Headline;
import com.headline.demo.service.HeadlineService;
import com.headline.demo.util.HeadlineBaseUtil;
import com.headline.demo.web.vo.ExportHeadlineVo;
import com.headline.demo.web.vo.HeadlineContrVo;
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
  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public HeadlineVo getUser(@RequestParam("headlinePk") Integer id) {

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
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName,
          ErrorCodeConstant.HEADLINE_QUERY_KEYWORDS_NOTBLANK);
      return res;
    }

    HeadlinePageVo ret = headlineServiceImpl.queryOnePageDataByCondition(itemSearchVo);
    res.setData(ret);
    return res;
  }

  @ApiOperation("文件上传与解析")
  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public ResponseDto uploadFileAndExtractHeadlineMessage(@RequestBody MultipartFile file) {
    String methodName = "uploadFileAndExtractHeadlineMessage";
    if (file == null) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName,
          ErrorCodeConstant.HEADLINE_FILE_UPLOAD_FAIL);
    }
    ResponseDto responseDto = this.getDefaultResponseDto();
    List<Headline> headlines =
        headlineServiceImpl.uploadHeadlineFileAndReturnSplitedHeadlines(file);
    responseDto.setData(headlines);
    responseDto.setMessage("文件上传并解析成功");
    return responseDto;
  }

  @ApiOperation("切割头条，并返回切割结果")
  @RequestMapping(value = "/split", method = RequestMethod.POST)
  public ResponseDto splitHeadlinesAndReturn(String seporator, Integer pageSize) {
    if (seporator == null) {
      seporator = HeadlineConstant.HEADLINE_DESCRIPTION_SPLIT_DEFAULT_SYMBOL;
    }

    ResponseDto responseDto = this.getDefaultResponseDto();

    List<Headline> result =
        headlineServiceImpl.splitHeadlinesAndReturnWithFirstPage(seporator, pageSize);
    responseDto.setData(result);
    return responseDto;
  }

  @ApiOperation("文案随机组装")
  @RequestMapping(value = "/construct", method = RequestMethod.POST)
  public ResponseDto constructHeadlinesAndReturnNewAndContructedHealdines(
      @RequestBody HeadlineContrVo reqVo) {
    String methodName = "constructHeadlinesAndReturnNewAndContructedHealdines";
    ResponseDto responseDto = this.getDefaultResponseDto();

    if (reqVo.getHeadlinePks() == null || CollectionUtils.isEmpty(reqVo.getHeadlinePks())) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName,
          ErrorCodeConstant.HEADLINE_PK_NOT_SELECTED);
    }

    List<Headline> res = headlineServiceImpl.constructHeadlinesAndReturn(reqVo);
    responseDto.setData(res);
    return responseDto;
  }

  @ApiOperation("批量删除")
  @RequestMapping(value = "/batchdelete", method = RequestMethod.POST)
  public ResponseDto batchDelete(@RequestBody List<Integer> headlinePks) {
    ResponseDto responseDto = this.getDefaultResponseDto();
    String methodName = "batchDelete";
    if (headlinePks == null && CollectionUtils.isEmpty(headlinePks)) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName,
          ErrorCodeConstant.HEADLINE_PK_NOT_SELECTED);
    }
    headlineServiceImpl.batchDelete(headlinePks);
    responseDto.setMessage("Delete success");
    return responseDto;
  }

  @ApiOperation("删除")
  @RequestMapping(value = "/softdelete", method = RequestMethod.GET)
  public ResponseDto softDelete(@RequestParam Integer headlinePk) {
    String methodName = "softDelete";
    ResponseDto responseDto = this.getDefaultResponseDto();
    if (headlinePk == null) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName,
          ErrorCodeConstant.HEADLINE_INVALID_PRIMARY_KEY);
    }
    headlineServiceImpl.solfItemDelte(headlinePk);
    responseDto.setMessage("Delete success");
    return responseDto;
  }

  @ApiOperation("更新文案")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseDto update(@RequestBody HeadlineVo headlineVo) {
    String methodName = "update";
    ResponseDto responseDto = this.getDefaultResponseDto();
    if (headlineVo == null) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName,
          ErrorCodeConstant.HEADLINE_INVALID_PRIMARY_KEY);
    }
    Headline record = new Headline();
    BeanUtils.copyProperties(record, headlineVo);
    headlineServiceImpl.updateByPrimaryKey(record);
    responseDto.setMessage("更新成功");
    return responseDto;
  }

  @ApiOperation("导出文案")
  @RequestMapping(value = "/export", method = RequestMethod.POST)
  public ResponseDto exportHeadline(@RequestBody ExportHeadlineVo exportVo, HttpServletRequest request, HttpServletResponse response) {
    String methodName = "exportHeadline";
    ResponseDto responseDto = this.getDefaultResponseDto();
    if (exportVo == null || CollectionUtils.isEmpty(exportVo.getHeadlinePks())
        && exportVo.getIsAllSelect() == null) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName,
          ErrorCodeConstant.HEADLINE_INVALID_PRIMARY_KEY);
    }
    List<Headline> headlines;
    if (exportVo.getIsAllSelect()) {
      headlines = headlineServiceImpl.getAllConstructedHeadlines();
    } else {
      headlines = headlineServiceImpl.getContructedHeadlinesWithSpecPks(exportVo.getHeadlinePks());
    }
     downloadHeadlines(headlines, request, response);

    return responseDto;
  }

  private void downloadHeadlines(List<Headline> headlines, HttpServletRequest request,
      HttpServletResponse response) {
    String methodName = "downloadFilesByStorageType";

    if (CollectionUtils.isEmpty(headlines)) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName,
          ErrorCodeConstant.HEADLINE_DOWNLOAD_EXCEPTION);
    }

    StringBuilder sb = new StringBuilder();
    headlines.forEach(headline -> {
      if (StringUtils.isNotEmpty(headline.getDescription())) {
        sb.append(headline.getDescription()).append("\r\n");
      }
    });

    this.setFileDownloadHeader(request, response, "文案.csv");

    try (OutputStream fos = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(fos);) {
      byte[] fileBytes = sb.toString().getBytes();
      bos.write(fileBytes, 0, fileBytes.length);
      bos.flush();
    } catch (Exception e) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName,
          ErrorCodeConstant.HEADLINE_DOWNLOAD_EXCEPTION, e.getMessage(), e);
    }
  }

  private void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response,
      String fileName) {
    String methodName = "setFileDownloadHeader";
    try {
      // Support CJK character
      String encodedFileName = null;
      String agent = request.getHeader("USER-AGENT");
      if (null != agent && -1 != agent.indexOf("MSIE")) {
        encodedFileName = URLEncoder.encode(fileName, HeadlineConstant.TEXT_ENCODING);
      } else if (null != agent && -1 != agent.indexOf("Mozilla")) {
        encodedFileName =
            new String(fileName.getBytes(HeadlineConstant.TEXT_ENCODING), "iso-8859-1");
      } else {
        encodedFileName = URLEncoder.encode(fileName, HeadlineConstant.TEXT_ENCODING);
      }
      response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
    } catch (UnsupportedEncodingException e) {
      HeadlineBaseUtil.printAndThrowErrorException(logger, this.getClass().getName(), methodName,
          ErrorCodeConstant.HEADLINE_DOWNLOAD_EXCEPTION, e.getMessage(), e);
    }
  }

}
