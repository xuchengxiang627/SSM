package com.xcx.springTest;

import com.xcx.ioc_01.HappyComponent;
import com.xcx.ioc_03.JavaBean;
import com.xcx.ioc_04.My;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class iocTest {
    @Test
    public void createIOC(){
        //创建IOC容器
        // 方案1
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-01.xml");
        // 方案2
        ClassPathXmlApplicationContext applicationContext1 = new ClassPathXmlApplicationContext();
        applicationContext1.setConfigLocations("spring-01.xml");  // 外部配置文件
        applicationContext1.refresh(); // 调用ioc和di的流程

        // 读取ioc容器中的组件
        // 方案1.根据id获取组件 不推荐
        HappyComponent happyComponent = (HappyComponent) applicationContext.getBean("happyComponent_001");
        happyComponent.doWork();

        // 方案2.根据id获取组件，同时指定类型
        HappyComponent happyComponent1 = applicationContext.getBean("happyComponent_001", HappyComponent.class);
        happyComponent1.doWork();

        // HappyComponent happyComponent3 = applicationContext.getBean("happyComponent_002", HappyComponent.class);
        // System.out.println(happyComponent1 == happyComponent3);  // false

        // 方案3.根据类型获取组件 此时配置文件中应只有一个HappyComponent对应组件
        // 也可以是 接口.class, 但配置文件中只能为具体类
        HappyComponent happyComponent2 = applicationContext.getBean(HappyComponent.class);
        happyComponent2.doWork();

        System.out.println(happyComponent == happyComponent1); // true
        System.out.println(happyComponent == happyComponent2); // true
    }

    @Test
    public void testIOC_03(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-03.xml"); // 单例在此处执行初始化方法
        System.out.println(1);
        JavaBean javaBean = classPathXmlApplicationContext.getBean(JavaBean.class); // 多例在此处初始化方法
        System.out.println(1);
        JavaBean javaBean1 = classPathXmlApplicationContext.getBean(JavaBean.class);    // 多例在此处初始化方法
        System.out.println(javaBean == javaBean1);

        // 单例时在ioc销毁时执行销毁方法；
        // 多例时使用Java垃圾回收机制销毁对象，不会执行销毁方法
        classPathXmlApplicationContext.close(); // 销毁方法
    }

    // 读取使用factoryBean工厂配置的组件对象
    @Test
    public void testIOC_04() throws Exception {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-04.xml");
        My my = classPathXmlApplicationContext.getBean("my", My.class);
        System.out.println(my); //com.xcx.ioc_04.My@770d3326

        // My 和 MyFactoryBean 均加入ioc，MyFactoryBean的id值为 & + My的id值
        System.out.println(classPathXmlApplicationContext.getBean("&my")); // com.xcx.ioc_04.MyFactoryBean@4cc8eb05

    }
}
