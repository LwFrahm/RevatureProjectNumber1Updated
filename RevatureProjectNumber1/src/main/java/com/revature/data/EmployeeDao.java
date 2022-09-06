package com.revature.data;

import com.revature.entity.Employee;

public interface EmployeeDao {

    public Employee registerEmployee(Employee employee);

    public Employee getById(int id);


}
