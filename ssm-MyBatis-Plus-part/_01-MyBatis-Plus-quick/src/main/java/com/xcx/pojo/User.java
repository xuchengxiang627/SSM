package com.xcx.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user") //指定实体类对应的表名
public class User {
    // @TableId(value = "u_id", type = IdType.AUTO)  // 指定主键列名，如果主键列名与实体类属性名一致(u_id和uId是一致的，因为开启了驼峰式映射)，可以省略
    // value   主键字段名
    // type   主键生成策略
    //         IdType.AUTO  自增
    //         IdType.INPUT  手动输入
    //         IdType.NONE  无主键
    //         IdType.ASSIGN_ID（默认）  雪花算法,分配 ID(主键类型为 Number(Long)或 String)(since 3.3.0),
    //                           使用接口IdentifierGenerator的方法nextId(默认实现类为DefaultIdentifierGenerator雪花算法)
    private Long id;
    // @TableField(value = "u_name", exist = false)   // 指定实体类属性对应的列名, exist = false  表示该字段在数据库中不存在
    private String name;
    private Integer age;
    private String email;

    // @TableLogic
    // //逻辑删除字段 int mybatis-plus下,默认 逻辑删除值为1 未逻辑删除0
    // private Integer deleted;
}
