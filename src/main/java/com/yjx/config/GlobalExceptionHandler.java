package com.yjx.config;

import com.alibaba.fastjson.JSON;
import com.yjx.base.ResultInfo;
import com.yjx.exceptions.AuthException;
import com.yjx.exceptions.ParamsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ParamsException.class)
    public void handleParamsException(ParamsException e, HttpServletRequest request,
                                       HttpServletResponse response) throws IOException {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(e.getCode());
        resultInfo.setMsg(e.getMsg());
        writeJson(response, resultInfo);
    }

    @ExceptionHandler(AuthException.class)
    public void handleAuthException(AuthException e, HttpServletRequest request,
                                     HttpServletResponse response) throws IOException {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(e.getCode());
        resultInfo.setMsg(e.getMsg());
        writeJson(response, resultInfo);
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e, HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(500);
        resultInfo.setMsg("系统繁忙，请稍后再试");
        writeJson(response, resultInfo);
    }

    private void writeJson(HttpServletResponse response, ResultInfo resultInfo) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(resultInfo));
        writer.flush();
        writer.close();
    }
}
