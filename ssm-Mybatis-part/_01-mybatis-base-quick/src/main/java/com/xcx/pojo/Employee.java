package com.xcx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("emp")
public class Employee {

    private Integer empId;

    private String empName;

    private Double empSalary;
    
    // getter | setter
}