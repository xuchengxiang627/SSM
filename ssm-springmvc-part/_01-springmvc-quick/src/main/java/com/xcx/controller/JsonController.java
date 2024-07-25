package com.xcx.controller;

import com.xcx.pojo.Person;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * json传参
 */
@RequestMapping("/json")
@RestController  // = @Controller + @ResponseBody
public class JsonController {

    /**
     * {
     *   "name": "张三",
     *   "age": 18,
     *   "gender": "男"
     * }
     */
    @RequestMapping("/data")
    public String data(@RequestBody Person person) {
        System.out.println(person);
        return person.toString();
    }
    // 报错 HTTP状态 415 - 不支持的媒体类型
    // 原因：Java原生api只支持param和路径传参
    // 解决：1. 导入json处理依赖 jackson 2.handlerAdapter配置json转换器


    // 返回json
    @RequestMapping("/data2")
    @ResponseBody
    public Person data2() {
        return new Person("张三", 18, "男");
    }
    @RequestMapping("/data3")
    @ResponseBody
    public List<Person> data3() {
        return List.of(
                new Person("张三", 18, "男"),
                new Person("李四", 19, "女")
        );
    }


}
