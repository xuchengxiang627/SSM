package com.xcx.controller;

import com.xcx.pojo.Schedule;
import com.xcx.service.ScheduleService;
import com.xcx.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin  // 允许跨域
@RestController
@RequestMapping("/schedule")
@Slf4j  // 日志
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    /**
     * 分页查询
     * 需求说明
     *     查询全部数据页数据
     * 请求uri
     *     schedule/{pageSize}/{currentPage}
     * 请求方式
     *     get
     * 响应的json
     *     {
     *         "code":200,
     *         "flag":true,
     *         "data":{
     *             //本页数据
     *             data:
     *             [
     *             {id:1,title:'学习java',completed:true},
     *             {id:2,title:'学习html',completed:true},
     *             {id:3,title:'学习css',completed:true},
     *             {id:4,title:'学习js',completed:true},
     *             {id:5,title:'学习vue',completed:true}
     *             ],
     *             //分页参数
     *             pageSize:5, // 每页数据条数 页大小
     *             total:0 ,   // 总记录数
     *             currentPage:1 // 当前页码
     *         }
     *     }
     */
    @GetMapping("/{pageSize}/{currentPage}")
    public Result page(@PathVariable int pageSize,
                       @PathVariable int currentPage) {
        Result result = scheduleService.page(pageSize, currentPage);
        // 打印日志
        log.info("分页查询数据为：{}", result);
        return result;
    }

    /**
     * 删除
     * 需求说明
     *     根据id删除日程
     * 请求uri
     *     schedule/{id}
     * 请求方式
     *     delete
     * 响应的json
     *     {
     *         "code":200,
     *         "flag":true,
     *         "data":null
     *     }
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        Result result = scheduleService.delete(id);
        // 打印日志
        log.info("删除数据为：{}", result);
        return result;
    }

    /**
     * 保存(新增数据)
     * 需求说明
     *     增加日程
     * 请求uri
     *     schedule
     * 请求方式
     *     post
     * 请求体中的JSON
     *     {
     *         title: '',
     *         completed: false
     *     }
     * 响应的json
     *     {
     *         "code":200,
     *         "flag":true,
     *         "data":null
     *     }
     */
    @PostMapping
    public Result save(@Validated @RequestBody Schedule schedule, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.fail("参数为空");
        }
        Result result = scheduleService.save(schedule);
        log.info("保存数据为：{}", result);
        return result;
    }

    /**
     * 修改
     * 需求说明
     *     根据id修改数据
     * 请求uri
     *     schedule
     * 请求方式
     *     put
     * 请求体中的JSON
     *     {
     *         id: 1,
     *         title: '',
     *         completed: false
     *     }
     * 响应的json
     *     {
     *         "code":200,
     *         "flag":true,
     *         "data":null
     *     }
     */
    @PutMapping
    public Result update(@Validated @RequestBody Schedule schedule, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.fail("参数为空");
        }
        Result result = scheduleService.update(schedule);
        log.info("修改数据为：{}", result);
        return result;
    }

}
