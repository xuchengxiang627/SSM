package com.xcx;

import com.example.JavaConfig;
import com.example.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class testJava {
    @Test
    public void test() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(JavaConfiguration.class);

        UserController userController = context.getBean(UserController.class);
        userController.show();
    }

    @Test
    public void testExample() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(JavaConfig.class);

        StudentController studentController = applicationContext.getBean(StudentController.class);

        studentController.findAll();
    }
}
