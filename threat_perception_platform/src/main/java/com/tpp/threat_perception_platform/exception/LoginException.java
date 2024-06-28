package com.tpp.threat_perception_platform.exception;

import com.tpp.threat_perception_platform.response.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LoginException {

    @ExceptionHandler(value = TokenException.class)
    public ResponseResult getTokenException(TokenException e)
    {
        // 1002就是token异常，需要重新登录
        return new ResponseResult<Object>(1002, e.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseResult loginException(Exception e)
    {
        e.printStackTrace();
        return new ResponseResult<Object>(1001, e.getMessage());
    }
}
