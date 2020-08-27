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
import org.springframework.util.Assert;

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

    /**
     *  注册用户信息
     * @param userModel
     * @throws BusinessException
     */
    @Override
    public void registUser(UserModel userModel) throws BusinessException {
        // 判断userModel是否为空
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        // UserModel转为UserDO对象
        UserDO userDO = covertFromDataObject(userModel);
        // 调用插入操作的mapper
        userDOMapper.insertSelective(userDO);
        // userModel 转为UserPasswordDO对象
        UserPasswordDO userPasswordDO = covertFromDateObject(userModel);
        // 调用UserPasswordDOMapper插入操作
        userPasswordDOMapper.insertSelective(userPasswordDO);
       return;
    }

    /**
     *  将userModel对象转为UserPasswordDO对象
     * @param userModel
     * @return
     */
    private UserPasswordDO covertFromDateObject(UserModel userModel) {
        // 判空
        if(userModel == null){
            return null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }

    /**
     *  将userModel 转为UserDO对象
     * @param userModel
     * @return
     * @throws BusinessException
     */
    private UserDO covertFromDataObject(UserModel userModel) throws BusinessException {
        if (StringUtils.isEmpty(userModel.getName()) ||
                StringUtils.isEmpty(userModel.getTelephone()) ||
                userModel.getAge() == null ||
                userModel.getGender() == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }

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
}
