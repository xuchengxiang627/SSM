import com.xcx.Calculator;
import com.xcx.CalculatorPureImpl;
import com.xcx.config.JavaConfig;
import com.xcx.proxy.ProxyFactory;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAop {
    @Test
    public void testJDKProxy() {
        // 创建目标对象
        Calculator target = new CalculatorPureImpl();
        // 创建代理对象
        Calculator proxy = (Calculator) new ProxyFactory(target).getProxy();
        // 执行代理对象方法
        proxy.add(1, 2);
    }

    @Test
    public void testAOP() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(JavaConfig.class);
        Calculator calculator = context.getBean(Calculator.class);
        int add = calculator.add(1, 2);

        System.out.println("-------------");

        int div = calculator.div(1, 0);

    }
}
