package com.mycompany.progpoepart1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Message {

    // details of one message
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // lists that keep track of messages while the program is running
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();

    // constructor - the ID and hash are created automatically
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageID = createMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash(messageID, messageNumber, messageText);
    }

    // getters
    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    // creates a random ten digit message ID
    public static String createMessageID() {
        Random rand = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            id = id + rand.nextInt(10);
        }
        return id;
    }

    // checks that the message ID is not more than ten characters
    public static boolean checkMessageID(String id) {
        return id != null && id.length() <= 10;
    }

    // checks the recipient number - reuses the cell number check from Part 1 (Login class)
    public static String checkRecipientCell(String cell) {
        Login checker = new Login();
        checker.setCellPhoneNumber(cell);

        if (checker.checkCellPhoneNumber()) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // checks that the message is not longer than 250 characters
    public static String checkMessageLength(String text) {
        if (text.length() <= 250) {
            return "Message ready to send.";
        } else {
            int extra = text.length() - 250;
            return "Message exceeds 250 characters by " + extra + "; please reduce the size.";
        }
    }

    // creates the hash e.g. 00:0:HITHANKS
    public static String createMessageHash(String id, int number, String text) {
        String firstTwo = id.substring(0, 2);

        // split the message into words
        String[] words = text.trim().split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        // remove commas, question marks etc so only letters and numbers are left
        firstWord = firstWord.replaceAll("[^a-zA-Z0-9]", "");
        lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "");

        String hash = firstTwo + ":" + number + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // returns the message depending on what the user chose
    // 1 = send, 2 = disregard, 3 = store
    public static String sentMessage(int choice) {
        if (choice == 1) {
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }

    // adds a message to the sent list
    public static void addToSent(Message m) {
        sentMessages.add(m);
    }

    // returns all the messages that were sent
    public static String printMessages() {
        String all = "";
        for (Message m : sentMessages) {
            all = all + "Message ID: " + m.getMessageID() + "\n"
                    + "Message Hash: " + m.getMessageHash() + "\n"
                    + "Recipient: " + m.getRecipient() + "\n"
                    + "Message: " + m.getMessageText() + "\n\n";
        }
        return all;
    }

    // returns the total number of messages sent
    public static int returnTotalMessages() {
        return sentMessages.size();
    }

    // stores a message in a JSON file
    public static void storeMessage(Message m) {
        storedMessages.add(m);

        try {
            FileWriter writer = new FileWriter("messages.json");
            writer.write("[\n");

            for (int i = 0; i < storedMessages.size(); i++) {
                Message msg = storedMessages.get(i);
                writer.write("  {\n");
                writer.write("    \"messageID\": \"" + msg.getMessageID() + "\",\n");
                writer.write("    \"messageHash\": \"" + msg.getMessageHash() + "\",\n");
                writer.write("    \"recipient\": \"" + msg.getRecipient() + "\",\n");
                writer.write("    \"message\": \"" + msg.getMessageText().replace("\"", "\\\"") + "\"\n");
                writer.write("  }");

                // no comma after the last one
                if (i < storedMessages.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }

            writer.write("]");
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not save the message: " + e.getMessage());
        }
    }

    // clears the lists (used by the unit tests)
    public static void clearMessages() {
        sentMessages.clear();
        storedMessages.clear();
    }
}
