package com.xcx.ioc_04;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<My> {

    @Override
    public My getObject() throws Exception {
        // 使用自己的方式实例化对象
        return new My();
    }

    @Override
    public Class<?> getObjectType() {
        return My.class;
    }
}
