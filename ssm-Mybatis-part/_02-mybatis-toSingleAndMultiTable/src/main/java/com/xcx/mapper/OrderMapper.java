package com.xcx.mapper;

import com.xcx.pojo.Order;

public interface OrderMapper {
  // 根据id查询订单信息和订单对应的客户
  Order queryOrderById(Integer orderId);
}