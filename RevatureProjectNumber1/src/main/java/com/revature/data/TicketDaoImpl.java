package com.revature.data;

import com.revature.entity.Employee;
import com.revature.entity.Ticket;
import com.revature.service.EmployeeService;
import com.revature.service.TicketService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao{

    Connection connection;

    public TicketDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public Ticket insert(Ticket ticket) {
        System.out.println("Now we are in the Dao trying to pass in the ticket object");
        // vulnerable to sql injection
        // String sql = "insert into ticket (id, amount, description) values (default, " + ticket.getAmount() + ", "+ ticket.getDescription();
        // System.out.println(sql);
        // put in employee_id, status, ?, ?
        String sql = "insert into ticket (id, amount, description, employee_id, status) values (default, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, ticket.getAmount());
            preparedStatement.setString(2, ticket.getDescription());
            preparedStatement.setInt(3, ticket.getEmployee_id());
            preparedStatement.setString(4, ticket.getStatus());
            System.out.println(preparedStatement.toString());

           int count = preparedStatement.executeUpdate();
           if (count == 1) {
               System.out.println("We've successfully inserted a ticket");
               ResultSet resultSet = preparedStatement.getGeneratedKeys();
               resultSet.next();
               int generatedId = resultSet.getInt(1);
               ticket.setId(generatedId);
           } else {
               System.out.println("Something went wrong with insertion of ticket");
           }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong when preparing the statement");
        }
        return ticket;
    }

    @Override
    public Ticket getById(int id) {
        String sql = "select * from ticket where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {

                // make sure these parameters are spelled correctly
                int idDb = resultSet.getInt("id");
                int amount = resultSet.getInt("amount");
                String description = resultSet.getString("description");
                // put in employee_id????????????
                int employee_id = resultSet.getInt("employee_id");
                String status = resultSet.getString("status");
                Ticket ticket = new Ticket(idDb, amount, description,employee_id, status);
                return ticket;
            } else {
                System.out.println("Something went wrong when trying to query ticket. Ticket might not exist.");
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Something went wrong when trying to retrieve data");
        }
        return null;
    }

    @Override
    public Ticket getByStatus(int employeeId) {
        String sql = "select id, employee_id from ticket where status = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {

                // make sure these parameters are spelled correctly
                int idDb = resultSet.getInt("id");
                int amount = resultSet.getInt("amount");
                String description = resultSet.getString("description");
                // put in employee_id????????????
                int employee_id = resultSet.getInt("employee_id");
                String status = resultSet.getString("status");
                Ticket ticket = new Ticket(idDb, amount, description,employee_id, status);
                return ticket;
            } else {
                System.out.println("Something went wrong when trying to query ticket. Ticket might not exist.");
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Something went wrong when trying to retrieve data");
        }
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<Ticket>();
        // tickets is leaving out parameters employee_id and status here
        String sql = "select * from ticket";
        try {
            Statement statement = connection.createStatement();
            //switched statement with preparedstatement because we don't have to change any parameters
            // PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                int amount = resultSet.getInt("amount");
                String description = resultSet.getString("description");
                int employee_id = resultSet.getInt("employee_id");
                String status = resultSet.getString("status");
                // updated and put in employee_id
                Ticket ticket = new Ticket(id, amount, description,employee_id, status);

                tickets.add(ticket);

                // put in return tickets here

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public Ticket update(Ticket ticket) {
        // update this
        Employee employee = new Employee();
        EmployeeService employeeService = new EmployeeService();


        String sql = "update ticket set amount = ?, description = ?, employee_id = ?, status = ? where id = ?;";
        try {
            // put in manager can only update somehow
         //   if(employee.isManager()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticket.getAmount());
            preparedStatement.setString(2, ticket.getDescription());
            preparedStatement.setInt(3, ticket.getEmployee_id());
            preparedStatement.setString(4, ticket.getStatus());
            preparedStatement.setInt(5, ticket.getId());


           int count = preparedStatement.executeUpdate();
           if(count == 1) {
               System.out.println("Update successful");
               return ticket;
           //}
           } else {
               System.out.println("Something went wrong with update");
           }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from ticket where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                System.out.println("Deletion successful.");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong went deleting a ticket");
        }
        return false;
    }

    @Override
    public boolean assignTicket(int employeeId, int ticketId, String status) {
        // this might be wrong
        // somehow fit in if manager = true in this?
        String sql = "update ticket set employee_id = ?, status = ? where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setString(2, "pending");
            // put in "pending" so its automatically pending, see if it works
            // had these two mixed around, maybe it will work now --- yes it works now
            preparedStatement.setInt(3, ticketId);


            int count = preparedStatement.executeUpdate();
            if(count == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("something went wrong when assigning a ticket");
        }
        return false;
    }

    @Override
    public List<Ticket> getSubmittedTickets(int employeeId) {
        // parameter was int employeeId

        // will get submitted tickts from each employee
        List<Ticket> tickets = new ArrayList<>();
        String sql = "select * from ticket where employee_id = ?;";
        // String sql = "select id, employee_id from ticket where status = ?;";
       // String sql = "select ticket.id, ticket.status from ticket where status = ?;";
        // select ticket.id, ticket.status from ticket where status = 'pending' and employee_id = 1;


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
           // preparedStatement.setString(1, tStatus);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                int amount = resultSet.getInt("amount");
                String description = resultSet.getString("description");
                int employee_id = resultSet.getInt("employee_id");
                String status = resultSet.getString("status");
                Ticket ticket = new Ticket(id, amount, description,employee_id, status);

                tickets.add(ticket);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong when trying to get submitted tickets from an employee");
        }

        return tickets;
    }
    /*
    public List<Ticket> getTypesOfTickets(String status) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "select id, employee_id from ticket where status = ?;";
    }

     */
}
/* if (username already taken)
try {
throw new RegisterException
} catch (RegisterException e) {
e.printStackTrace();
}
 */
