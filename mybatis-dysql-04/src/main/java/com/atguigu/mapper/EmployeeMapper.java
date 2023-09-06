package com.atguigu.mapper;

import com.atguigu.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {

    List<Employee> query(
            @Param("name") String name,
            @Param("salary") Double salary
    );
    int update(Employee employee);
    List<Employee> queryBatch(@Param("ids") List<Integer> ids);
    int deleteBatch(@Param("ids") List<Integer> ids);
    int insertBatch(@Param("list") List<Employee> employees);
    int updateBatch(@Param("list") List<Employee> employees);
}
