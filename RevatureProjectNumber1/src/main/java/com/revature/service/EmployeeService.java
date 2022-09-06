package com.revature.service;

import com.revature.data.DaoFactory;
import com.revature.data.EmployeeDao;
import com.revature.data.TicketDao;
import com.revature.entity.Employee;
import com.revature.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    public Employee login(int id, String password) {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        Employee employee = employeeDao.getById(id);
        // put manager in register, not login
        // put in here somewhere so employee is defualt, manager has to be set as true
        if(password.equals(employee.getPassword())){
            return employee;
            // put something here with manager method
        }
        return null;
    }

    // might have to switch this method up??
    // inserting new employee into database
    public Employee register(Employee employee) {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        Employee employee1 = employeeDao.registerEmployee(employee);
        return employee1;
    }

    // given a employee id and a ticket id, have that employee submit that ticket(or maybe have that
    // ticket tied to their name)

    // put in ticket Description here!?
    public boolean assignTicket(int employeeId, int ticketId, String status) {
        // have to interact with ticket database
        TicketDao ticketDao = DaoFactory.getTicketDao();
        // boolean indicates whether assigning of ticket was successful
        return ticketDao.assignTicket(employeeId, ticketId, status);
    }

    // given an employees id, return all tickets submitted by them
    public List<Ticket> getSubmittedTickets(int employeeId) {
        TicketDao ticketDao = DaoFactory.getTicketDao();

        return ticketDao.getSubmittedTickets(employeeId);
    }

}
