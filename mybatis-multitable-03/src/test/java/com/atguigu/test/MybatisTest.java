package com.atguigu.test;

import com.atguigu.mapper.CustomerMapper;
import com.atguigu.mapper.OrderMapper;
import com.atguigu.pojo.Customer;
import com.atguigu.pojo.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class MybatisTest {

    private SqlSession session;
    // junit会在每一个@Test方法前执行@BeforeEach方法

    @BeforeEach
    public void init() throws IOException {
        session = new SqlSessionFactoryBuilder()
                .build(
                        Resources.getResourceAsStream("mybatis-config.xml"))
                .openSession();
    }

    @Test
    public void test_01(){
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        Order order = orderMapper.selectOrderWithCustomer(1);
        System.out.println("order = " + order);
    }

    @Test
    public void testRelationshipToMulti() {

        CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
        // 查询Customer对象同时将关联的Order集合查询出来
        List<Customer> customers = customerMapper.queryList();
        for(Customer customer : customers){
            List<Order> orderList = customer.getOrderList();
            System.out.println("orderList = " + orderList);
        }
    }

    // junit会在每一个@Test方法后执行@@AfterEach方法
    @AfterEach
    public void clear() {
        session.commit();
        session.close();
    }
}
