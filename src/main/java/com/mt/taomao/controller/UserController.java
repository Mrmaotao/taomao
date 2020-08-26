package com.mt.taomao.controller;

import com.alibaba.druid.util.StringUtils;
import com.mt.taomao.controller.viewobject.UserVO;
import com.mt.taomao.exception.BusinessException;
import com.mt.taomao.exception.CommonError;
import com.mt.taomao.exception.EmBusinessError;
import com.mt.taomao.service.UserService;
import com.mt.taomao.service.model.UserModel;
import com.mt.taomao.util.response.CommonReturnType;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

import static com.mt.taomao.exception.EmBusinessError.*;

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
public class UserController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    HttpServletRequest httpServletRequest;

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    @GetMapping("/get")
    public CommonReturnType getUser(@RequestParam("id") Integer id) throws BusinessException {
        // 通过调用userServcie服务根据用户id获取用户信息
        UserModel userModel = userService.getUserById(id);
        // 若获取的用户信息不存在
        if (userModel == null) {
            throw new BusinessException(USER_NOT_EXIT);
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

    @GetMapping("/getOpt")
    public CommonReturnType getOpt(@RequestParam("telephone") String telephone) {
        // 根据规则生成验证码
        Random randomData = new Random();
        int number = randomData.nextInt(99999);
        int opt = number+10000;
        // 将验证码与手机号关联
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(telephone,opt);
        // 将验证码发送给手机号
        System.out.println("手机号："+telephone+" ---- 验证码："+opt);
        return CommonReturnType.create(opt);
    }

    @GetMapping("/regist")
    public CommonReturnType registUser(@RequestParam("telephone")String telephone,
                                       @RequestParam("opt")String opt,
                                       @RequestParam("name")String name,
                                       @RequestParam("age")Integer age,
                                       @RequestParam("gender")Integer gender,
                                       @RequestParam("password")String password) throws BusinessException {
        // 验证手机号和验证码
        String optCode = (String) httpServletRequest.getSession().getAttribute(telephone);
        if(!StringUtils.equals(optCode,opt)) {
            throw new BusinessException(EmBusinessError.PARAMETER_UNKNOWN_ERROR,"验证码错误");
        }
        // 注册用户
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setGender(Byte.valueOf(gender.toString()));
        userModel.setRegisterModel("bywechat");
        userModel.setTelephone(telephone);
        userModel.setEncrptPassword(MD5Encoder.encode(password.getBytes()));

        return  null;
    }


}
