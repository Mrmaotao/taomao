package com.mt.taomao.service.model;/**
 * project： taomao
 * created by Maotao on 2020/8/23
 */

import lombok.Data;

/**
 * @ProjectName:taomao
 * @ClassName:UserModel
 * @Description TODO
 * @Author maotao
 * @Date 2020/8/23 19:56
 * @Version 1.0
 **/
@Data
public class UserModel {

    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String telephone;
    private String registerModel;
    private String thirdPartyId;
    // 加密密码
    private String encrptPassword;

}
