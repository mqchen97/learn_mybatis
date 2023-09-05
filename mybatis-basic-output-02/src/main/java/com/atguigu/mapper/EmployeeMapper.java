package com.atguigu.mapper;

import com.atguigu.pojo.Employee;

import java.util.Map;


public interface EmployeeMapper {
    Employee queryById(Integer id);
    Double querySalaryById(Integer id);
    String queryNameById(Integer id);
    Map<String, Object> selectEmpNameAndMaxSalary();
    int insertEmp(Employee employee);
}
