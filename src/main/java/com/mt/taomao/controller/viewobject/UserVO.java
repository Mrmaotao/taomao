package com.mt.taomao.controller.viewobject;/**
 * project： taomao
 * created by Maotao on 2020/8/23
 */

import lombok.Data;

/**
 * @ProjectName:taomao
 * @ClassName:UserVO
 * @Description TODO
 * @Author maotao
 * @Date 2020/8/23 21:42
 * @Version 1.0
 **/
@Data
public class UserVO {
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String telephone;
}
