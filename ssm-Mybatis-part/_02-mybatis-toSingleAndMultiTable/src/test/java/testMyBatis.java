import com.xcx.mapper.CustomerMapper;
import com.xcx.mapper.OrderMapper;
import com.xcx.pojo.Customer;
import com.xcx.pojo.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class testMyBatis {
    @Test
    public void test1() throws IOException {
        String mybatisConfigFilePath = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sessionFactory.openSession();

        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        Order order = orderMapper.queryOrderById(1);
        System.out.println(order);

        CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
        List<Customer> orderList = customerMapper.queryAllCustomer();
        orderList.forEach(System.out::println);

        session.close();
    }
}
