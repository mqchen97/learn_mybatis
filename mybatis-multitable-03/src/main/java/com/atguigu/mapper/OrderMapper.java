package com.atguigu.mapper;

import com.atguigu.pojo.Order;

public interface OrderMapper {
    Order selectOrderWithCustomer(Integer orderId);
}
