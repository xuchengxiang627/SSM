package ioc_03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    // @Autowired(required = false)  // required = false 可以没有对应类型组件，但可能会造成空指针
    // 当对应组件有多个时，如 UserService 有实现类 UserServiceImpl/UserServiceImpl2 时，使用 @Autowired 会报错
    // 此时属性名可以作为组件 id，如属性名为 userServiceImpl 时，则找到 UserServiceImpl 对应组件
    // 或者使用 @Qualifier(value = "userServiceImpl"), 且 @Qualifier 必须与 @Autowired 一起使用
    // 或者使用 @Resource(name = "userServiceImpl") 相当于 @Autowired + @Qualifier
    @Autowired      // 相当于 <property name="userService" ref="userService"/>
    private UserService userService; // 引用类型赋值

    @Value("18")
    private int age;    // 基本类型赋值，通常情况下直接赋值

    // 通过外部配置文件赋值
    @Value("${user.username:defaultValue}") // : 后为默认值
    private String username;
    @Value("${user.password}")
    private String password;

    public void show() {
        userService.show();
        System.out.println("UserController show()");
        System.out.println("age = " + age);
        System.out.println("username = " + username);
        System.out.println("password = " + password);
    }
}
