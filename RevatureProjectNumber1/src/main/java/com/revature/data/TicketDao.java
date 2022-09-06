package com.revature.data;

import com.revature.entity.Employee;
import com.revature.entity.Ticket;
import java.util.*;

public interface TicketDao {

    public Ticket insert(Ticket ticket);

    public Ticket getById(int id);
    public Ticket getByStatus(int id);

    public List<Ticket> getAllTickets();

    public Ticket update(Ticket ticket);

    public boolean delete(int id);
    // returns whether delete was succesful
    // pass in an id to determine which ticket to delete
    // might not need this method ---- switch to something else

    // put in string status
    public boolean assignTicket(int employeeId, int ticketId, String status);

    public List<Ticket> getSubmittedTickets(int employeeId);
    // was int employeeId
    // put in status?


}
