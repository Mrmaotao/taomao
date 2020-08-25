package com.mt.taomao.controller;

import com.mt.taomao.controller.viewobject.UserVO;
import com.mt.taomao.exception.BusinessException;
import com.mt.taomao.exception.EmBusinessError;
import com.mt.taomao.service.UserService;
import com.mt.taomao.service.model.UserModel;
import com.mt.taomao.util.response.CommonReturnType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName:taomao
 * @ClassName:UserController
 * @Description TODO
 * @Author maotao
 * @Date 2020/8/23 19:52
 * @Version 1.0
 **/
@RestController("user")
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    UserService userService;

    @GetMapping("/get")
    public CommonReturnType getUser(@RequestParam("id") Integer id) throws BusinessException {
        // 通过调用userServcie服务根据用户id获取用户信息
        UserModel userModel = userService.getUserById(id);
        // 若获取的用户信息不存在
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIT);
        }
        // 将核心领域模型用户对象转为可供UI使用的viewobject
        UserVO userVO = covertFromDataObjcet(userModel);
        return CommonReturnType.create(userVO);
    }

    /**
     * 将核心领域模型用户对象转为可供UI使用的viewobject
     *
     * @param userModel
     * @return
     */
    private UserVO covertFromDataObjcet(UserModel userModel) {
        UserVO userVO = new UserVO();
        if (userModel == null) {
            return null;
        }
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }



}
