package com.imooc.ad.advice;

import com.imooc.ad.exception.AdException;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class) // 告诉spring这里只处理AdException异常，
    public CommonResponse<String> handlerAdException(HttpServletRequest request, AdException exception){
        CommonResponse<String> response = new CommonResponse<>(-1, "business error");
        response.setData(exception.getMessage());
        return response;
    }
}
