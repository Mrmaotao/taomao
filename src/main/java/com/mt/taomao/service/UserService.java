package com.mt.taomao.service;

import com.mt.taomao.exception.BusinessException;
import com.mt.taomao.service.model.UserModel;

/**
 * project： taomao
 * created by Maotao on 2020/8/23
 */
public interface UserService {
    /**
     *  根据id获取用户信息
     * @param id
     * @return
     */
    public UserModel getUserById(Integer id);

    /**
     * 注册用户
     * @param userModel
     */
    public void registUser(UserModel userModel) throws  BusinessException;


}
