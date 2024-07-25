package com.xcx.mapper;

import com.xcx.pojo.Customer;

import java.util.List;

public interface CustomerMapper {
  // 查询所有客户信息及其订单信息
  List<Customer> queryAllCustomer();
}