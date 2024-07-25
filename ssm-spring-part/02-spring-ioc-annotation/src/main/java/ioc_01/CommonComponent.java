package ioc_01;

import org.springframework.stereotype.Component;

// @Component @Controller @Service @Repository作用相同，相当于
// <bean id="commonComponent" class="ioc_01.CommonComponent"/>
// 分别用于普通组件/控制层/业务层/数据层以作为标识
@Component("commonComponent")          // 默认id为类名首字母小写，指定id可以给value赋值(value可以省略)
public class CommonComponent {
}
