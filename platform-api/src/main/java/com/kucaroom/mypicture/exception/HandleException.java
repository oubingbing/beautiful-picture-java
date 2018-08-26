package com.kucaroom.mypicture.exception;import com.kucaroom.mypicture.util.ResponseData;import com.kucaroom.mypicture.util.ResponseUtil;import lombok.extern.slf4j.Slf4j;import org.springframework.web.bind.annotation.ControllerAdvice;import org.springframework.web.bind.annotation.ExceptionHandler;import org.springframework.web.bind.annotation.ResponseBody;@ControllerAdvice@Slf4jpublic class HandleException {    /**     * 捕获异常，进行自定义处理     * @param e     * @return     */    @ResponseBody    @ExceptionHandler(value = Exception.class)    public ResponseData handle(Exception e){        if(e instanceof ApiException){            ApiException apiException = (ApiException) e;            return ResponseUtil.fail(apiException.getCode(),apiException.getMessage());        }else{            return ResponseUtil.fail(-1,e.getMessage());        }    }}