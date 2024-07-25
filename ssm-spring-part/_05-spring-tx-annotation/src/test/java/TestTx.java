import com.xcx.config.JavaConfig;
import com.xcx.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestTx {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(JavaConfig.class);
        StudentService studentService = context.getBean(StudentService.class);
        studentService.changeInfo();
    }


}
