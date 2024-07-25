package com.xcx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcx.pojo.Type;
import com.xcx.service.TypeService;
import com.xcx.mapper.TypeMapper;
import com.xcx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 27163
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-07-24 20:53:54
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService{

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Result findAllTypes() {
        return Result.ok(typeMapper.selectList(null));
    }
}




