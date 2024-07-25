package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 实现类
@Service
public class StudentServiceImpl  implements StudentService {

    @Autowired
    private StudentDao studentDao;

    /**
     * 查询全部学员业务
     * @return
     */
    @Override
    public List<Student> findAll() {

        List<Student> studentList =  studentDao.queryAll();

        return studentList;
    }
}