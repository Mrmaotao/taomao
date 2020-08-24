package com.mt.taomao.exception;/**
 * project： taomao
 * created by Maotao on 2020/8/24
 */

/**
 * @ProjectName:taomao
 * @ClassName:BusinessException
 * @Description TODO
 * @Author maotao
 * @Date 2020/8/24 22:41
 * @Version 1.0
 **/
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;

    // 直接接收EmBusinessError的传参用于构造业务异常。
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    // 接收自定义的errorMsg的方式构造业务异常
    public BusinessException(CommonError commonError,String errorMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrorMsg(errorMsg);
    }
    @Override
    public String getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return this.commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrorMsg(String msg) {
        this.commonError.setErrorMsg(msg);
        return this;
    }
}
