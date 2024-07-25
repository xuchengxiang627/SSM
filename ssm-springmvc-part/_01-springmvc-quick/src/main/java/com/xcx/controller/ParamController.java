package com.xcx.controller;

import com.xcx.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * param传参
 */
@Controller
@RequestMapping("/param")
public class ParamController {
    // 直接接收  /param/data?name=root&age=18
    // 形参列表直接填写对应的参数即可，名称必须相同
    // 但参数可以不传递，因此需要考虑参数是否有空的情况 ， 基本类型必须有值，否则会报错
    @RequestMapping("/data")
    @ResponseBody
    public String data(String name, int age) {
        if (name == null) {
            name = "默认值";
        }

        System.out.println("name: " + name + ", age: " + age);
        return "name: " + name + ", age: " + age;
    }

    // 注解指定
    // 指定任意的请求参数名，要求必须传递，或不必须传递
    @RequestMapping("/data2")
    @ResponseBody
    public String data2(@RequestParam(value = "account") String name,
                        @RequestParam(required = false, defaultValue = "1") int age) {
        // 要求name必须传递，age可不传递，默认为1
        // @RequestParam("account") String name 请求中的account参数被传入name； 若请求中有name参数，不被传入形参列表中的name
        // @RequestParam(required = false, defaultValue = "1") 设置为非必须，默认值为1            required 默认值为true

        System.out.println("name: " + name + ", age: " + age);
        return "name: " + name + ", age: " + age;
    }

    // 特殊值 一名多值  key=1&key=2   直接用集合接值即可
    @RequestMapping("/data3")
    @ResponseBody
    public String data3(@RequestParam List<Integer> key) {
        // 不加@RequestParam注解，会将key对应的一个值直接赋值给List<Integer> key，产生类型异常
        // 加上@RequestParam注解，handlerAdapter会将key对应的多个值，add到List<Integer> key中
        System.out.println("key: " + key);
        return "key: " + key;
    }

    // 使用实体对象接值  属性名必须与参数名一致
    @RequestMapping("/data4")
    @ResponseBody
    public String data4(User user) {
        System.out.println("user: " + user);
        return "user: " + user;
    }

}
