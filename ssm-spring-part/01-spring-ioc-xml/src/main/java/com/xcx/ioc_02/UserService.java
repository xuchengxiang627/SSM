package com.xcx.ioc_02;

public class UserService {
    
    private UserDao userDao;
    private int age;
    private String name;

    public UserService() {
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService(int age , String name ,UserDao userDao) {
        this.userDao = userDao;
        this.age = age;
        this.name = name;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setAge(int age) {
        this.age = age;
    }
}