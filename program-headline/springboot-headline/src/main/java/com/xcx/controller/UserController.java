package com.xcx.controller;

import com.xcx.pojo.User;
import com.xcx.service.UserService;
import com.xcx.utils.JwtHelper;
import com.xcx.utils.Result;
import com.xcx.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 用户登录
     * 大概流程:
     *    1. 账号进行数据库查询 返回用户对象
     *    2. 对比用户密码(md5加密)
     *    3. 成功,根据userId生成token -> map key=token value=token值 - result封装
     *    4. 失败,判断账号还是密码错误,封装对应的枚举错误即可
     */
    @PostMapping("login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }

    /**
     * 根据token获取用户信息
     */
    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader("token") String token) {
        return userService.getUserInfo(token);
    }

    /**
     * 检查用户名是否被注册
     */
    @PostMapping("checkUserName")
    public Result checkUserName(@RequestParam("username") String username) {
        return userService.checkUserName(username);
    }

    /**
     * 用户注册
     */
    @PostMapping("regist")
    public Result regist(@RequestBody User user) {
        return userService.regist(user);
    }

    /**
     * 测试登录是否成功
     */
    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader("token") String token) {
        if (jwtHelper.isExpiration(token)) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }
}
