package com.xcx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public void show() {
        userService.show();
        System.out.println("UserController show");
    }
}
