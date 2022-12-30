package com.xybert.springbootexception.handler;

import com.xybert.springbootexception.exception.BaseException;
import com.xybert.springbootexception.result.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xybert
 * @description 全局异常处理
 * @date 2022/12/30 16:42
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理业务异常
     *
     * @param e BaseException
     * @return ApiResponse
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ApiResponse exceptionHandler(BaseException e){
        logger.error("业务异常：{}",e.getMessage());
        return ApiResponse.fail(e);
    }

    /**
     * 处理空指针异常
     *
     * @param e NullPointerException
     * @return ApiResponse
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ApiResponse exceptionHandler(NullPointerException e){
        logger.error("空指针异常：{}",e.getMessage());
        return ApiResponse.fail(e);
    }

    /**
     * 处理系统异常
     *
     * @param e Exception
     * @return ApiResponse
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse exceptionHandler(Exception e){
        logger.error("系统异常：{}",e.getMessage());
        return ApiResponse.fail(e);
    }
}
