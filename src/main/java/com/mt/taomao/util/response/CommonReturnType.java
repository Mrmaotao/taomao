package com.mt.taomao.util.response;/**
 * project： taomao
 * created by Maotao on 2020/8/23
 */

import lombok.Data;

/**
 * @ProjectName:taomao
 * @ClassName:CommonReturnType
 * @Description TODO
 * @Author maotao
 * @Date 2020/8/23 21:56
 * @Version 1.0
 **/
@Data
public class CommonReturnType {
    // 表明对应请求的返回处理结果，"success"或"fail"
    private String status;
    // 当status为success时，则data内返回前端需要的json数据
    // 当status为fail时，则data返回指定的错误码
    private Object data;

    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result,"success");
    }

    public static CommonReturnType create(Object result,String status) {
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setData(result);
        commonReturnType.setStatus(status);
        return commonReturnType;
    }

}
