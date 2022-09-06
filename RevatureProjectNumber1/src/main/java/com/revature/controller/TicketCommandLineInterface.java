package com.revature.controller;



import com.revature.entity.Ticket;
import com.revature.service.TicketService;

import java.util.List;
import java.util.Scanner;

public class TicketCommandLineInterface {

    public static void menu() {
        TicketService ticketService = new TicketService();
        Scanner intScanner = new Scanner(System.in);
        Scanner stringScanner = new Scanner(System.in);
        while (true) {
            printOptions();
            int choice = intScanner.nextInt();
            switch(choice) {
                case 1:
                    System.out.println("Enter ticket information");
                    System.out.println("Ticket amount");
                    int amount = intScanner.nextInt();
                    System.out.println("Ticket description");
                    String description = stringScanner.nextLine();
                    System.out.println("Employee that owns ticket");
                    int employee_id = intScanner.nextInt();
                    System.out.println("Ticket status");
                    String status = stringScanner.nextLine();
                    Ticket newTicket = new Ticket(amount, description, employee_id, status);
                    System.out.println(ticketService.insert(newTicket));
                    break;

                case 2 :
                    System.out.println("Enter a ticket id");
                    int id = intScanner.nextInt();
                    System.out.println(ticketService.getById(id));
                    break;

                case 3:
                    List<Ticket> tickets = ticketService.getAllTickets();
                    for (int i = 0; i< tickets.size(); i++) {
                        System.out.println(tickets.get(i));
                    }
                    break;

                case 4:
                    System.out.println("Enter ticket information");
                    System.out.println("Ticket id");
                    id = intScanner.nextInt();
                    System.out.println("Ticket amount");
                    amount = intScanner.nextInt();
                    System.out.println("Ticket description");
                    description = stringScanner.nextLine();
                    System.out.println("Employee id that owns ticket");
                    employee_id = intScanner.nextInt();
                    System.out.println("Ticket status");
                    status = stringScanner.nextLine();
                    Ticket ticket = new Ticket(id, amount, description,employee_id, status);
                    System.out.println(ticketService.updateTicket(ticket));
                    break;

                case 5:
                    System.out.println("Id to delete");
                    id = intScanner.nextInt();
                    if(ticketService.deleteTicket(id)) {
                        System.out.println("Ticket deleted");
                    } else {
                        System.out.println("Something went wrong when deleting ticket");
                    }
                    break;

                default:
                    System.out.println("Not an option");
                    break;

            }
        }
    }

    public static void printOptions() {
            System.out.println("What would you like to do?");
            System.out.println("1 - Add ticket ");
            System.out.println("2 - Get a ticket by id");
            System.out.println("3 - Get all tickets");
            System.out.println("4 - Update ticket");
            System.out.println("5 - Delete ticket");
            System.out.println("Enter here ->");
        }



}
