package com.xcx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xcx.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    // 自定义一个根据年龄查询并且分页的方法
    IPage<User> selectPageByAge(IPage<User> page, @Param("age") Integer age);

}
