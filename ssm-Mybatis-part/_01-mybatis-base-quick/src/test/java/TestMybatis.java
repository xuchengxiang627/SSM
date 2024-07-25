import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xcx.mapper.EmployeeMapper;
import com.xcx.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class TestMybatis {
    /*
        mybatis 提供的api进行方法的调用
     */
    @Test
    public void test1() throws IOException {
        // 1.创建SqlSessionFactory对象
        // ①声明Mybatis全局配置文件的路径
        String mybatisConfigFilePath = "mybatis-config.xml";

        // ②以输入流的形式加载Mybatis配置文件
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);

        // ③基于读取Mybatis配置文件的输入流创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.使用SqlSessionFactory对象开启一个会话，每次业务创建一个，用完就释放
        SqlSession session = sessionFactory.openSession();

        // 3.根据EmployeeMapper接口的Class对象获取Mapper接口类型的对象(动态代理技术)
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        // 4. 调用代理类方法既可以触发对应的SQL语句
        Employee employee = employeeMapper.queryById(1);
        System.out.println("employee = " + employee);

        Employee employee1 = new Employee(null, "小明", 1000.0);
        System.out.println("employee1 = " + employee1);
        employeeMapper.insert(employee1);
        System.out.println("employee1 = " + employee1);  // id会自动回填

        List<Employee> list = employeeMapper.queryByNameAndSalary("小明", 1000.0);
        System.out.println("list = " + list);

        String name = employeeMapper.queryNameById(1);
        System.out.println("name = " + name);

        Map<String, Object> map = employeeMapper.queryEmpNameAndMaxSalary();
        System.out.println("map = " + map);

        Employee employee2 = employeeMapper.testResultMap(1);
        System.out.println("employee2 = " + employee2);

        // 4.关闭SqlSession
        session.commit(); //提交事务 [DQL不需要,其他需要]
        session.close(); //关闭会话
    }

    @Test
    public void testIfAndWhere() throws IOException {
        String mybatisConfigFilePath = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sessionFactory.openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        List<Employee> list = employeeMapper.testIfAndWhere("小明", 1000.0);
        System.out.println("list = " + list);

        session.close();
    }

    @Test
    public void testSet() throws IOException {
        String mybatisConfigFilePath = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sessionFactory.openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        Employee employee = new Employee(4, null, 9897.0);
        employeeMapper.testSet(employee);

        session.commit();
        session.close();
    }

    @Test
    public void testForeach() throws IOException {
        String mybatisConfigFilePath = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sessionFactory.openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        List<Employee> list = employeeMapper.testForeach(List.of(1, 2, 3));
        System.out.println("list = " + list);
        session.close();
    }

    /*
        分页查询
     */
    @Test
    public void testPage() throws IOException {
        String mybatisConfigFilePath = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sessionFactory.openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        // ****************************************************************
        // 两个分页语句中只能存在一个查询语句
        // 设置分页要查询的页数和每页记录数
        PageHelper.startPage(3, 4);

        List<Employee> list = employeeMapper.testPage();

        // 将查询结果封装成PageInfo对象，包括总记录数，总页数，当前页数等
        PageInfo<Employee> pageInfo = new PageInfo<>(list);
        // ****************************************************************

        // 当前页数据
        List<Employee> list1 = pageInfo.getList();
        int pages = pageInfo.getPages(); // 总页数
        long total = pageInfo.getTotal(); // 总记录数
        int pageNum = pageInfo.getPageNum(); // 当前页数
        int pageSize = pageInfo.getPageSize(); // 每页记录数

        System.out.println("共" + pages + "页，共有" + total + "条记录，当前第"+ pageNum + "页，有" + list1.size() + "条记录");
        list1.forEach(System.out::println);

        session.close();
    }
}
