package com.xcx.service;

import com.xcx.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xcx.utils.Result;

/**
* @author 27163
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-07-24 20:53:54
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}
