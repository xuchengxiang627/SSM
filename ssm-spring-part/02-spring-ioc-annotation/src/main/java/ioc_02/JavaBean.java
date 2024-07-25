package ioc_02;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// ioc组件生命周期和作用域
// @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON) // 默认是singleton 单例
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // 可以创建多例
@Component()
public class JavaBean {

    // 必须是 public void 无参数方法
    @PostConstruct
    public void init(){
        System.out.println("初始化方法");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁方法");
    }
}
