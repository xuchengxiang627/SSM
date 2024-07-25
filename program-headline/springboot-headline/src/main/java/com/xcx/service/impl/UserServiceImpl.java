package com.xcx.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcx.mapper.UserMapper;
import com.xcx.pojo.User;
import com.xcx.service.UserService;
import com.xcx.utils.JwtHelper;
import com.xcx.utils.MD5Util;
import com.xcx.utils.Result;
import com.xcx.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author 27163
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-07-24 20:53:54
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());

        User selectUser = userMapper.selectOne(queryWrapper);

        if (selectUser == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        if (!StringUtils.isEmpty(user.getUserPwd())
                && MD5Util.encrypt(user.getUserPwd()).equals(selectUser.getUserPwd())) {
            // 登录成功，生成token
            String token = jwtHelper.createToken(Long.valueOf(selectUser.getUid()));
            Map<String, String> data = new HashMap<>();
            data.put("token", token);
            return Result.ok(data);
        }
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {
        if (jwtHelper.isExpiration(token)) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        User user = userMapper.selectById(jwtHelper.getUserId(token));
        user.setUserPwd("");
        Map<String, User> data = new HashMap<>();
        data.put("loginUser", user);
        return Result.ok(data);
    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        if (userMapper.selectCount(queryWrapper) <= 0) {
            return Result.ok(null);
        }
        return Result.build(null, ResultCodeEnum.USERNAME_USED);
    }

    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        if (userMapper.selectCount(queryWrapper) > 0) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        userMapper.insert(user);
        return Result.ok(null);
    }
}




