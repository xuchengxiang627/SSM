package com.xcx.service;

import com.xcx.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xcx.utils.Result;

/**
* @author 27163
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-07-24 20:53:54
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);
}
