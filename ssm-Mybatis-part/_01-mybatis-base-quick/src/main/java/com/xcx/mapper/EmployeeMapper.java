package com.xcx.mapper;

import com.xcx.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 相当于之前的DAO，规定数据库方法
 */
public interface EmployeeMapper {
    // 根据id查询员工
    Employee queryById(Integer id);

    int deleteById(Integer id);

    int insert(Employee employee);

    List<Employee> queryByNameAndSalary(@Param("a") String name, @Param("b") Double salary);

    String queryNameById(Integer id);

    Map<String, Object> queryEmpNameAndMaxSalary();

    Employee testResultMap(Integer id);


    List<Employee> testIfAndWhere(@Param("name") String name, @Param("salary") Double salary);

    // 根据员工id更新员工数据，要求传入name和salary不为null才更新
    int testSet(Employee employee);

    // 批量查询
    List<Employee> testForeach(@Param("ids") List<Integer> ids);

    // 查询所有员工信息
    List<Employee> testPage();

}
