package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.data.EmployeeDaoImpl;
import com.revature.entity.Employee;
import com.revature.service.EmployeeService;
import com.revature.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import com.revature.entity.Ticket;
import java.util.*;

public class TicketServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get info about ticket
      //  int employeeId = (Integer) request.getSession().getAttribute("id");
        String tStatus = (String) request.getSession().getAttribute("status");

      //  System.out.println("Id from session: "+ employeeId);
        System.out.println("Status from session: " + tStatus);

        String user = request.getParameter("username");
        System.out.println(user);
        // leave as is???


        String pathInfo = request.getPathInfo();
        System.out.println("Path info: " + pathInfo);


        TicketService ticketService = new TicketService();
        // means we didn't specify an id
        PrintWriter out = response.getWriter();
        if(pathInfo == null) {
            List<Ticket> tickets = ticketService.getAllTickets();
            // out.println instead of system.out.println so it prints to postman instead of console
            for(Ticket ticket : tickets) {
                out.println(ticket);
            }
        } else {
            // will take every character starting with one to the end of the string
            String id = pathInfo.substring(1);
           // String status = pathInfo.substring(1);

            int idInteger = Integer.parseInt(id);
             //String statusString = status;
            // Ticket ticket = ticketService.getByStatus(statusString);
            Ticket ticket = ticketService.getById(idInteger);
            // Ticket ticket = ticketService.getByStatus(status);
            out.println(ticket);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        TicketService ticketService = new TicketService();

        // will convert json into java code
        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket;

        // can do error handling to check for bad requests
        try {
            ticket = mapper.readValue(req.getReader(), Ticket.class);
        } catch(Exception e) {
            e.printStackTrace();
            resp.sendError(400, "Invalid ticket format");
            return;
        }
        // ticket should have id after it is inserted
        ticket = ticketService.insert(ticket);
        out.println(ticket);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        TicketService ticketService = new TicketService();

        // will convert json into java code
        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket;

        // can do error handling to check for bad requests
        try {
            ticket = mapper.readValue(req.getReader(), Ticket.class);
        } catch(Exception e) {
            e.printStackTrace();
            resp.sendError(400, "Invalid ticket format");
            return;
        }
        // ticket should have id after it is inserted
        Employee employee;
        //req.getSession().setAttribute(("manager"), employee.isManager());
       // boolean man = Boolean.parseBoolean(req.getParameter("manager"));
      //  out.println(employee.isManager());   --- currently prints false
        //  want employee.isManager() == true if employee is manager
        String status = (String) req.getSession().getAttribute("status");
        Ticket tStatus = ticketService.getById(ticket.getId());

        boolean employeeId = (Boolean) req.getSession().getAttribute("manager");
        if(employeeId  == true) { // could als try the simplify method to make it work
            if(tStatus.getStatus().equals("pending")) {
                ticket = ticketService.updateTicket(ticket);
                out.println(ticket);
            } else {
                out.println("Must be pending to change");
            }
        } else {
            out.println("Must be manager to update ticket");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        TicketService ticketService = new TicketService();

        String pathInfo = req.getPathInfo();

        if(pathInfo == null) {
            resp.sendError(400, "Must include id");
            return;
        }
        String id = pathInfo.substring(1);
        int idInteger = Integer.parseInt(id);
        boolean success = ticketService.deleteTicket(idInteger);
        if(success) {
            out.write("Deletion successful");
        } else {
            resp.sendError(400, "Deletion unsuccessful, perhaps id doesn't exist");
        }

    }
}
