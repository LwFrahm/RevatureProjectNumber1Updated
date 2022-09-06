package com.revature.service;

import com.revature.data.DaoFactory;
import com.revature.data.TicketDao;
import com.revature.data.TicketDaoImpl;
import com.revature.data.TicketDaoImplTemp;
import com.revature.entity.Ticket;

import java.util.List;

public class TicketService {

    public Ticket insert(Ticket ticket) {
        System.out.println("In the service layer, getting the DAO and calling the insert DAO method");
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.insert(ticket);
    }

    public Ticket getById(int id) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.getById(id);
    }
    public Ticket getByStatus(int id) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.getByStatus(id);
    }

    public List<Ticket> getAllTickets() {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return  ticketDao.getAllTickets();
    }

    public Ticket updateTicket(Ticket ticket) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.update(ticket);
       // return ticketDao.update(ticket, ticket.getId());
        // ticket.getId as second parameter??
    }

    public boolean deleteTicket(int id) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.delete(id);
    }
}
