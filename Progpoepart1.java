package com.mycompany.progpoepart1;

import java.util.Scanner;

public class Progpoepart1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login app = new Login();

        displayHeader(scanner);

        System.out.println("\n--- REGISTRATION FORM ---");

        System.out.print("Enter First Name: ");
        app.setFirstName(scanner.nextLine());

        System.out.print("Enter Last Name: ");
        app.setLastName(scanner.nextLine());

        System.out.print("Choose Username (must contain _ and be <= 5 chars): ");
        app.setUsername(scanner.nextLine());

        System.out.print("Choose Password (8+ chars, A-Z, 0-9, special char): ");
        app.setPassword(scanner.nextLine());

        System.out.print("Enter Cell Number (+27 followed by 9 digits): ");
        app.setCellPhoneNumber(scanner.nextLine());

        System.out.println("\n--- REGISTRATION RESULT ---");

        String result = app.registerUser();
        System.out.println(result);

        if (result.contains("successfully added")) {
            System.out.println("\n--- LOGIN SECTION ---");

            System.out.print("Username: ");
            String loginUser = scanner.nextLine();

            System.out.print("Password: ");
            String loginPass = scanner.nextLine();

            System.out.println("\n--- LOGIN RESULT ---");
            System.out.println(app.returnLoginStatus(loginUser, loginPass));

            // NEW (Part 2): only logged in users can use QuickChat
            if (app.loginUser(loginUser, loginPass)) {
                QuickChat.startChat(scanner);
            } else {
                System.out.println("You must log in successfully to send messages.");
            }

        } else {
            System.out.println("\nRegistration failed. Please restart and try again.");
        }

        System.out.println("\n==============================================");
        scanner.close();
    }

    public static void displayHeader(Scanner scanner) {
        System.out.println("==============================================");
        System.out.println("         QUICKCHAT REGISTRATION SYSTEM       ");
        System.out.println("==============================================");
        System.out.println("Welcome to the Login System!");
    }
}
