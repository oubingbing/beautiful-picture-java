package com.kucaroom.mypicture.exception;import com.kucaroom.mypicture.enums.ResponseEnum;import com.kucaroom.mypicture.util.ResponseData;import com.kucaroom.mypicture.util.ResponseUtil;import lombok.extern.slf4j.Slf4j;import org.springframework.web.bind.annotation.ControllerAdvice;import org.springframework.web.bind.annotation.ExceptionHandler;import org.springframework.web.bind.annotation.ResponseBody;import org.springframework.web.servlet.ModelAndView;@Slf4j@ControllerAdvicepublic class HandleException {    /**     * 捕获异常，进行自定义处理     * @param e     * @return     */    @ResponseBody    @ExceptionHandler(value = Exception.class)    public Object handle(Exception e){        log.info("异常信息：{}",e);        if(e instanceof WebApiException){            WebApiException webApiException = (WebApiException) e;            if(webApiException.getCode() == ResponseEnum.APP_NOT_EXIST.getCode()){                return new ModelAndView("/admin/registerApp");            }            return ResponseUtil.fail(webApiException.getCode(),webApiException.getMessage());        }else{            return ResponseUtil.fail(-1,"未知错误");        }    }}