package com.mt.taomao.controller;/**
 * project： taomao
 * created by Maotao on 2020/8/25
 */

import com.mt.taomao.exception.BusinessException;
import com.mt.taomao.exception.EmBusinessError;
import com.mt.taomao.util.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName:taomao
 * @ClassName:BaseController
 * @Description TODO
 * @Author maotao
 * @Date 2020/8/25 21:29
 * @Version 1.0
 **/
public class BaseController {
    // 定义exceptionHanler解决未被Controller接受的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Object handlerException(HttpServletRequest request, Exception ex) {
        Map<String, Object> response = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException exception = (BusinessException) ex;
            response.put("errorCode", exception.getErrorCode());
            response.put("errorMsg", exception.getErrorMsg());
        }else{
            response.put("errorCode", EmBusinessError.PARAMETER_UNKNOWN_ERROR.getErrorCode());
            response.put("errorMsg", EmBusinessError.PARAMETER_UNKNOWN_ERROR.getErrorMsg());
        }
        return CommonReturnType.create(response,"fail");
    }
}
