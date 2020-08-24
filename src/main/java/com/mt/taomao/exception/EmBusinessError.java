package com.mt.taomao.exception;

/**
 * project： taomao
 * created by Maotao on 2020/8/24
 */
public enum EmBusinessError implements CommonError {
    // 通用错误信息
    PARAMETER_VALIDATION_ERROR("0001","参数不合法！"),
    // 10000开头为用户信息相关错误定义
    USER_NOT_EXIT("10001","该用户不存在！");
    private String ErrorCode;
    private String ErrorMsg;

    private EmBusinessError(String errorCode, String errorMsg) {
        ErrorCode = errorCode;
        ErrorMsg = errorMsg;
    }

    @Override
    public String getErrorCode() {
        return this.ErrorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.ErrorMsg;
    }

    @Override
    public CommonError setErrorMsg(String msg) {
        this.ErrorMsg = msg;
        return this;
    }
}
