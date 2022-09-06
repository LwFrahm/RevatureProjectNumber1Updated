package com.revature.data;

import com.revature.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketDaoImplTemp implements TicketDao{

    public Ticket insert(Ticket ticket) {
        // print out dummy data
        System.out.println("Insert ticket " + ticket.toString());
        ticket.setId(1);
        return ticket;
    }

    public Ticket getById(int id) {
        Ticket dummyTicket = new Ticket(id, 200, "Hotel",3, "pending");
        return dummyTicket;
    }

    @Override
    public Ticket getByStatus(int id) {
        return null;
    }

    public List<Ticket> getAllTickets() {
        Ticket ticket1 = new Ticket(1, 50, "gas", 1, "pending");
        Ticket ticket2 = new Ticket(2,100, "hotel and gas", 2, "pending");
        List<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(ticket1);
        tickets.add(ticket2);
        return tickets;
    }

    public Ticket update(Ticket ticket) {
        System.out.println("Updated ticket ");
        Ticket updatedTicket = new Ticket();
        updatedTicket.setId(ticket.getId());
        updatedTicket.setAmount(ticket.getAmount());
        updatedTicket.setDescription(ticket.getDescription());
        return updatedTicket;
        // or return ticket    ?
    }

    public boolean delete(int id) {
        System.out.println("Deleted ticket with id " + id);
        return true;
    }

    @Override
    // put in status
    public boolean assignTicket(int employeeId, int ticketId, String status) {
        return false;
    }

    @Override
    public List<Ticket> getSubmittedTickets(int employeeId) {
        return null;
    }
    // these methods will give out dummy data - to make sure everything is working

}
