package com.atguigu.mapper;

import com.atguigu.pojo.Customer;

import java.util.List;

public interface CustomerMapper {

    // 查询所有信息和订单信息
    List<Customer> queryList();
}
