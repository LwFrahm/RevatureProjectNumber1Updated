package com.revature.data;

import com.revature.entity.Employee;

public class DaoFactory {

    private static TicketDao ticketDao = null;
    private static EmployeeDao employeeDao = null;

    private DaoFactory() {
    }

    public static TicketDao getTicketDao() {
        if(ticketDao == null) {
            System.out.println("This should only be called once (Ticket dao creation)");
            ticketDao = new TicketDaoImpl();
        }
        return ticketDao;
    }
    public static EmployeeDao getEmployeeDao() {
        if (employeeDao == null) {
            employeeDao = new EmployeeDaoImpl();
            }
        return employeeDao;
        }
}

