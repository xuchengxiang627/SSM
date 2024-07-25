package com.xcx;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xcx.mapper.UserMapper;
import com.xcx.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest // springboot下测试环境注解
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    // 分页测试
    @Test
    public void testPage() {
        Page<User> page = new Page<>(1, 2);  // 1:当前页码，2：每页显示数量
        userMapper.selectPage(page, null);

        long total = page.getTotal(); // 总记录数
        System.out.println("总记录数：" + total);

        List<User> records = page.getRecords(); // 当前页记录
        records.forEach(System.out::println);

        long pages = page.getPages(); // 总页数
        System.out.println("总页数：" + pages);

        long current = page.getCurrent(); // 当前页码
        System.out.println("当前页码：" + current);

        long size = page.getSize(); // 每页显示数量
        System.out.println("每页显示数量：" + size);


        // 自定义分页方法
        Page<User> page2 = new Page<>(1, 3);
        userMapper.selectPageByAge(page2, 20);
        records = page2.getRecords();
        records.forEach(System.out::println);
    }
}
