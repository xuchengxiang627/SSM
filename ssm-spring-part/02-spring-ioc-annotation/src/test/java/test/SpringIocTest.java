package test;

import ioc_01.CommonComponent;
import ioc_01.XxxController;
import ioc_01.XxxDao;
import ioc_01.XxxService;
import ioc_02.JavaBean;
import ioc_03.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIocTest {

    @Test
    public void test_01() {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("spring-01.xml");
        XxxController xxxController = context.getBean(XxxController.class);
        System.out.println("Controller = " + xxxController);

        XxxDao xxxDao = context.getBean(XxxDao.class);
        System.out.println("Dao = " + xxxDao);

        XxxService xxxService = context.getBean(XxxService.class);
        System.out.println("Service = " + xxxService);

        CommonComponent commonComponent = context.getBean(CommonComponent.class);
        System.out.println("CommonComponent = " + commonComponent);
    }

    @Test
    public void test_02() {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("spring-02.xml");
        JavaBean bean = context.getBean(JavaBean.class);
        JavaBean bean1 = context.getBean(JavaBean.class);

        System.out.println(bean1 == bean);

        context.close();
    }

    @Test
    public void test_03() {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("spring-03.xml");
        UserController userController = context.getBean(UserController.class);
        userController.show();
    }


}
