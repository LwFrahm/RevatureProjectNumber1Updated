package com.revature.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.entity.Employee;
import com.revature.entity.Ticket;
import com.revature.service.EmployeeService;
import com.revature.service.TicketService;

import java.util.*;

public class EmployeeServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        EmployeeService employeeService = new EmployeeService();
        // registering/creating new user and also logs us in
        ObjectMapper mapper = new ObjectMapper();
        Employee employee; // this could be the issue?


        try {
            employee = mapper.readValue(req.getReader(), Employee.class);
        } catch(Exception e) {
            e.printStackTrace();
            resp.sendError(400, "invalid employee register format");
            return;
        }
        // put in if(employee!=null ..... out.println("username already taken)?
        String authType = req.getParameter("auth");
        if(authType.equals("login")) {

            employee = employeeService.login(employee.getId(), employee.getPassword());
        } else if(authType.equals("register")) {
            // put manager method in here?
            employee=employeeService.register(employee);
        }

        if(employee == null) {
            resp.sendError(400,"invalid credentials");
            return;
        }


        out.println(employee.getUsername());

        // update session with persons id, indicates if we are logged in/registered
        // manager logic here
        req.getSession().setAttribute("id", employee.getId());
        // manager logic, put in ticketServlet?
        req.getSession().setAttribute(("manager"), employee.isManager());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // login will be getting a user
        // set up a DTO, data transfer object, which is a class and this will store id and password
        // login method in doPost method now
        //
        // registering/creating new user and also logs us in
        ObjectMapper mapper = new ObjectMapper();
        Employee employee; // this could be the issue?

        PrintWriter out = resp.getWriter();
        EmployeeService employeeService = new EmployeeService();

        int employeeId;
        try {
            employeeId = (Integer) req.getSession().getAttribute("id");
        } catch (Exception e) {
            resp.sendError(400, "must be logged in to view tickets");
            return;
        }

        List<Ticket> tickets = employeeService.getSubmittedTickets(employeeId);

        for (Ticket tick : tickets) {
            out.println(tick);
        }

   /*  boolean employeeId = (Boolean) req.getSession().getAttribute("manager");
        if(employeeId  == true) { // could als try the simplify method to make it work
            if(tStatus.getStatus().equals("pending")) {
                ticket = ticketService.updateTicket(ticket);
                out.println(ticket);
            }
    */
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        EmployeeService employeeService = new EmployeeService();

        int employeeId;
        int ticketId;
        String status;
        // try to get employee id from session
        try {
            employeeId = (Integer) req.getSession().getAttribute("id");
        } catch(Exception e) {
            resp.sendError(400, "Must be logged in to submit");
            return;
        }
        // get ticket id
        try {
            ticketId = (Integer.parseInt(req.getParameter("ticketId")));
        } catch(Exception e) {
            resp.sendError(400, "Must include ticket id");
            return;
        }

        // get ticket status
        try {
            status = req.getParameter("status");
                    //(Integer.parseInt(req.getParameter("ticketId")));
        } catch(Exception e) {
            resp.sendError(400, "Must include ticket status");
            return;
        }
        // assign ticket
        try {
            employeeService.assignTicket(employeeId, ticketId, status);
            // put in status
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(400, "Make sure ticket id exists!");
            return;
        }
        out.println("Ticket assigned successfully!");
    }
}
