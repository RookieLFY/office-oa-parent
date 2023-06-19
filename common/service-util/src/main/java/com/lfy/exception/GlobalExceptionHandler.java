package com.lfy.exception;

import com.lfy.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalError(Exception e){
        e.printStackTrace();
        return Result.fail().message("执行全局异常处理");
    }

    //自定义异常处理
    @ExceptionHandler(SelfDefinedException.class)
    @ResponseBody
    public Result selfException(SelfDefinedException e){
        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }
}
