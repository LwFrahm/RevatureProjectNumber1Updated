package com.revature.data;

import com.revature.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Result;
import java.sql.*;

public class EmployeeDaoImpl implements EmployeeDao {

    Connection connection;

    public EmployeeDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public Employee registerEmployee(Employee employee) {
        Logger logger = LoggerFactory.getLogger("com.revature.EmployeeDaoImpl");
        // was insert method in person example, should do same thing but with different name
        String sql = "insert into employee values (default, ?, ?, ?);";
        // put in another question mark here for manager - should work now
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getUsername());
            preparedStatement.setString(2, employee.getPassword());
            preparedStatement.setBoolean(3, employee.isManager());

            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                logger.info("employee added successfully");
            }
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            employee.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            logger.warn("Something went wrong when registering");
            return null;
        }

        return employee;
    }

    @Override
    public Employee getById(int id) {
        Logger logger = LoggerFactory.getLogger("Employee dao impl");
        String sql = "select * from employee where id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                int idDb = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                boolean manager = resultSet.getBoolean("manager");
                return new Employee(idDb, username, password, manager);

                // manager
            } else {
                logger.warn("employee doesn't exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Something went wrong when trying to obtain all employee Ids");
        }
        return null;
    }
}
