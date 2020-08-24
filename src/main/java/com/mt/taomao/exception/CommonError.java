package com.mt.taomao.exception;

/**
 * project： taomao
 * created by Maotao on 2020/8/24
 */
public interface CommonError {
    public String getErrorCode();
    public String getErrorMsg();
    public CommonError setErrorMsg(String msg);
}
