package com.mt.taomao.service.impl;/**
 * project： taomao
 * created by Maotao on 2020/8/23
 */

import com.mt.taomao.dao.UserDOMapper;
import com.mt.taomao.dao.UserPasswordDOMapper;
import com.mt.taomao.dataobject.UserDO;
import com.mt.taomao.dataobject.UserPasswordDO;
import com.mt.taomao.exception.BusinessException;
import com.mt.taomao.exception.CommonError;
import com.mt.taomao.exception.EmBusinessError;
import com.mt.taomao.service.UserService;
import com.mt.taomao.service.model.UserModel;
import com.mt.taomao.util.response.CommonReturnType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName:taomao
 * @ClassName:UserServiceImpl
 * @Description TODO
 * @Author maotao
 * @Date 2020/8/23 19:55
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDOMapper userDOMapper;
    @Autowired
    UserPasswordDOMapper userPasswordDOMapper;

    /**
     * 将用户userDO和userPasswordDO转为UserModel对象
     *
     * @param userDO
     * @param userPasswordDO
     * @return
     */
    private UserModel covertFromDataObect(UserDO userDO, UserPasswordDO userPasswordDO) {
        if (userDO == null) {
            return null;
        }
        if (userPasswordDO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        return userModel;
    }

    @Override
    public UserModel getUserById(Integer id) {
        // 调用UserDOmapper获取dataobject
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        // 该用户不存在
        if (userDO == null) {
            return null;
        }
        // 通过用户id获取对应的用户加密密码信息
        UserPasswordDO userPasswordDO = userPasswordDOMapper.getUserPasswordByUserId(id);
        return covertFromDataObect(userDO, userPasswordDO);
    }

    @Override
    public void registUser(UserModel userModel) throws  BusinessException {
        // 判断userModel是否为空
        if(userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        // UserModel转为UserDO对象

        // 调用插入操作的mapper
    }

    private UserDO covertFromDataObject(UserModel userModel,UserDO userDO){
        return null;

    }
}
