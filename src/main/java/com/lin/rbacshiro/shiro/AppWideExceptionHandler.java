package com.lin.rbacshiro.shiro;

import com.alibaba.fastjson.JSON;
import com.lin.rbacshiro.dto.Response;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {


    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String authenticationException(AuthorizationException e) {
        Response response = new Response();
        response.setCode(HttpStatus.FORBIDDEN.value());
        response.setMessage(e.getMessage());
        response.setData(null);
        return JSON.toJSONString(response);
    }
}
