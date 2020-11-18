package com.xi.xlm.exception;


import cn.hutool.core.exceptions.ValidateException;
import com.len.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;

/**
 * @Author YangTianFeng
 * @Description 校验异常解析类
 * @Date 17:01 2020/9/3
 * @Param
 * @return
 **/
@RestControllerAdvice
@Slf4j
@Resource
public class ExceptionHelper {


    /**
     * 参数校验异常
     *
     * @param exception
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleException(MethodArgumentNotValidException exception) {
        /**获取异常信息封装对象**/
        FieldError fieldError = exception.getBindingResult().getFieldError();
        return Result.error(fieldError.getDefaultMessage());
    }

    /**
     * 业务异常
     *
     * @param exception
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ServiceException.class)
    public Result handleException(ServiceException exception) {
        log.error(exception.getMessage());
        log.error(ExceptionUtils.getErrorStackTrace(exception));
        return Result.error(exception.getMessage());
    }

    /**
     * token失效异常
     *
     * @param exception
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception exception) {
        log.error(ExceptionUtils.getErrorStackTrace(exception));
        return Result.error("系统异常");
    }

    /**
     * token失效异常
     *
     * @param exception
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UnAuthorizedException.class)
    public Result handleException(UnAuthorizedException exception) {
        System.out.println("==============================");
        return Result.error(405, exception.getMessage());
    }


}
