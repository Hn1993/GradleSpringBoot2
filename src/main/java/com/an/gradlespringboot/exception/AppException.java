package com.an.gradlespringboot.exception;

import com.an.gradlespringboot.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result defaultExceptionHandler(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        return Result.Exception("服务器内部错误");
    }

}
