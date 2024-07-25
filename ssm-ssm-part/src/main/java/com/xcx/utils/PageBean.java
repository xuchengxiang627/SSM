package com.xcx.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    private int currentPage;   // 当前页码
    private int pageSize;      // 每页显示的数据量
    private long total;    // 总数据条数
    private List<T> data;      // 当前页的数据集合
}

/*
需求说明
    查询全部数据页数据
请求uri
    schedule/{pageSize}/{currentPage}
请求方式
    get
响应的json
    {
        "code":200,
        "flag":true,
        "data":{
            //本页数据
            data:
            [
            {id:1,title:'学习java',completed:true},
            {id:2,title:'学习html',completed:true},
            {id:3,title:'学习css',completed:true},
            {id:4,title:'学习js',completed:true},
            {id:5,title:'学习vue',completed:true}
            ],
            //分页参数
            pageSize:5, // 每页数据条数 页大小
            total:0 ,   // 总记录数
            currentPage:1 // 当前页码
        }
    }
*/
