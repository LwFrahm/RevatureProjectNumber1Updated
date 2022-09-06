package com.revature.controller;

import com.revature.entity.Employee;
import com.revature.entity.Ticket;
import com.revature.service.EmployeeService;
import com.revature.service.TicketService;

import java.util.List;
import java.util.Scanner;

public class EmployeeCommandLineInterface {

    public static void menu() {
        EmployeeService employeeService = new EmployeeService();
        TicketService ticketService = new TicketService();
        Employee employee = null;
        Scanner intScanner = new Scanner(System.in);
        Scanner stringScanner = new Scanner(System.in);
        Scanner booleanScanner = new Scanner(System.in);

        while(true) {
            printOptions();
            int choice = intScanner.nextInt();
            switch (choice) {
                case 1:
                    if(employee != null) {
                        System.out.println("Employee already registered");
                        break;
                    }
                    System.out.println("Enter username ");
                    String username = stringScanner.nextLine();
                    System.out.println("Enter password ");
                    String password = stringScanner.nextLine();

                    // entered manager here
                    System.out.println("Enter manager status ");
                    boolean manager = booleanScanner.nextBoolean();
                    Employee employee1 = new Employee(username, password, manager);
                    employee = employeeService.register(employee1);
                    break;
                case 2:
                    if(employee != null) {
                        System.out.println("Employee already logged in");
                        break;
                    }
                    System.out.println("Enter id");
                    int id = intScanner.nextInt();
                    System.out.println("Enter password");
                    password = stringScanner.nextLine();
                    System.out.println("Is a manager?");
                    // put in manager here
                    manager = booleanScanner.nextBoolean();
                    employee = employeeService.login(id, password);
                    if(employee == null) {
                        System.out.println("Login not successful");
                        break;
                    }
                    break;
                case 3:
                    if(employee == null) {
                        System.out.println("Must be logged in to assign a ticket");
                        break;
                    }
                    System.out.println("Available tickets");
                    for(Ticket ticket : ticketService.getAllTickets()) {
                        System.out.println(ticket.toString());
                    }

                    System.out.println("Enter ticket that to assign to yourself");
                    int ticketId = intScanner.nextInt();
                    System.out.println("Status");
                    String status = stringScanner.nextLine();
                    boolean success = employeeService.assignTicket(employee.getId(), ticketId, status);
                    if(success) {
                        System.out.println("Assignment of ticket successful");
                    } else {
                        System.out.println("Assignment unsuccessful");
                    }
                    break;
                case 4:
                    if(employee == null) {
                        System.out.println("Please log in first");
                        break;
                    }
                    List<Ticket> tickets = employeeService.getSubmittedTickets(employee.getId());
                    System.out.println("Here are the employee's tickets");
                    for(Ticket ticket : tickets) {
                        System.out.println(ticket);
                    }

            }
        }
    }

    public static void printOptions() {
        System.out.println("What would you like to do?");
        System.out.println("1 - Register ");
        System.out.println("2 - Log in");
        System.out.println("3 - Assign ticket");
        System.out.println("4 - View tickets for specific employee");
        System.out.println("Enter here ->");
    }
}
