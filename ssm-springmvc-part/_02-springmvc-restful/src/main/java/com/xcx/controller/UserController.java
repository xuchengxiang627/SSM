package com.xcx.controller;

import com.xcx.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 查询 get
     * 插入 post
     * 更新 put
     * 删除 delete
     */

    // 分页查询 GET /user?page=1&size=10
    @GetMapping
    public List<User> page(@RequestParam(required = false, defaultValue = "1") int page,
                           @RequestParam(required = false, defaultValue = "10") int size) {
        return null;
    }

    // 新增 POST /user
    @PostMapping
    public User save(@RequestBody User user) {
        return user;
    }

    // 详情 GET /user/1
    @GetMapping("/{id}")
    public User detail(@PathVariable("id") Integer id) {
        return null;
    }

    // 更新 PUT /user
    @PutMapping
    public User update(@RequestBody User user) {
        return user;
    }

    // 删除 DELETE /user/1
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {

    }

    // 模糊查询 GET /user/search?page=xxx&size=xxx&keyword=xxx
    @GetMapping("/search")
    public List<User> search(String keyword,
                             @RequestParam(required = false, defaultValue = "1") int page,
                             @RequestParam(required = false, defaultValue = "10") int size) {
        return null;
    }

}
