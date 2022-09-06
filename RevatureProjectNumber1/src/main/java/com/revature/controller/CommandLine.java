package com.revature.controller;

import java.util.Scanner;

public class CommandLine {

    public static  void menu() {
        System.out.println("Which menu do you want to see");
        System.out.println("1 - Ticket menu");
        System.out.println("2 - Employee menu");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                TicketCommandLineInterface.menu();
                break;
            case 2:
                EmployeeCommandLineInterface.menu();
                break;
            default:
                System.out.println("please choose 1 or 2");
        }
    }

}
