package com.mycompany.progpoepart1;

import java.util.Scanner;

public class QuickChat {

    // keeps asking until the user types a whole number
    public static int readNumber(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = input.nextLine().trim();
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    // runs the QuickChat menu (called after a successful login)
    public static void startChat(Scanner input) {
        System.out.println("\nWelcome to QuickChat.");

        // user decides how many messages they want to enter
        int maxMessages = readNumber(input, "How many messages do you want to enter? ");
        while (maxMessages < 1) {
            maxMessages = readNumber(input, "Please enter at least 1: ");
        }
        int messagesEntered = 0;

        boolean running = true;

        while (running) {
            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            int option = readNumber(input, "Choose an option: ");

            if (option == 1) {

                if (messagesEntered >= maxMessages) {
                    System.out.println("You have already entered all " + maxMessages + " messages.");
                } else {
                    // get the recipient number
                    String recipient = "";
                    boolean validNumber = false;

                    while (!validNumber) {
                        System.out.print("Enter the recipient number: ");
                        recipient = input.nextLine().trim();
                        String result = Message.checkRecipientCell(recipient);
                        System.out.println(result);
                        if (result.equals("Cell phone number successfully captured.")) {
                            validNumber = true;
                        }
                    }

                    // get the message
                    String text = "";
                    boolean validMessage = false;

                    while (!validMessage) {
                        System.out.print("Enter your message: ");
                        text = input.nextLine();
                        String result = Message.checkMessageLength(text);
                        System.out.println(result);
                        if (text.trim().isEmpty()) {
                            System.out.println("The message cannot be empty.");
                        } else if (result.equals("Message ready to send.")) {
                            validMessage = true;
                        }
                    }

                    // create the message (message number starts at 0)
                    Message msg = new Message(messagesEntered, recipient, text);
                    System.out.println("Message ID generated: " + msg.getMessageID());

                    // ask what to do with the message
                    System.out.println("\n1) Send Message");
                    System.out.println("2) Disregard Message");
                    System.out.println("3) Store Message to send later");
                    int choice = readNumber(input, "Choose an option: ");
                    while (choice < 1 || choice > 3) {
                        choice = readNumber(input, "Please choose 1, 2 or 3: ");
                    }

                    System.out.println(Message.sentMessage(choice));

                    if (choice == 1) {
                        Message.addToSent(msg);
                        // show the full details after sending
                        System.out.println("\nMessage ID: " + msg.getMessageID());
                        System.out.println("Message Hash: " + msg.getMessageHash());
                        System.out.println("Recipient: " + msg.getRecipient());
                        System.out.println("Message: " + msg.getMessageText());
                    } else if (choice == 2) {
                        String del = input.nextLine().trim();
                        if (del.equals("0")) {
                            System.out.println("Message deleted.");
                        }
                    } else if (choice == 3) {
                        Message.storeMessage(msg);
                    }

                    messagesEntered++;

                    // once all messages are done, show the total
                    if (messagesEntered == maxMessages) {
                        System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());
                    }
                }

            } else if (option == 2) {
                System.out.println("Coming Soon.");

            } else if (option == 3) {
                System.out.println("Total messages sent: " + Message.returnTotalMessages());
                System.out.println("Goodbye!");
                running = false;

            } else {
                System.out.println("Please choose 1, 2 or 3.");
            }
        }
    }
}
