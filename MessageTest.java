package com.mycompany.progpoepart1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageTest {

    // test data from the assignment
    String number1 = "+27718693002";
    String text1 = "Hi Mike, can you join us for dinner tonight?";
    String number2 = "08575975889";
    String text2 = "Hi Keegan, did you receive the payment?";

    Message msg1;
    Message msg2;

    @BeforeEach
    public void setUp() {
        Message.clearMessages();
        msg1 = new Message(0, number1, text1);
        msg2 = new Message(1, number2, text2);
    }

    // ---- message length ----
    @Test
    public void testMessageLengthSuccess() {
        assertEquals("Message ready to send.", Message.checkMessageLength(text1));
    }

    @Test
    public void testMessageLengthFailure() {
        String longText = "a".repeat(260);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.",
                Message.checkMessageLength(longText));
    }

    // ---- recipient number ----
    @Test
    public void testRecipientSuccess() {
        assertEquals("Cell phone number successfully captured.", Message.checkRecipientCell(number1));
    }

    @Test
    public void testRecipientFailure() {
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                Message.checkRecipientCell(number2));
    }

    // ---- message hash ----
    @Test
    public void testMessageHashTestCase1() {
        assertEquals("00:0:HITONIGHT", Message.createMessageHash("0012345678", 0, text1));
    }

    @Test
    public void testMessageHashAllMessagesInLoop() {
        String[] ids = {"0012345678", "0087654321"};
        String[] texts = {text1, text2};
        String[] expected = {"00:0:HITONIGHT", "00:1:HIPAYMENT"};

        for (int i = 0; i < texts.length; i++) {
            assertEquals(expected[i], Message.createMessageHash(ids[i], i, texts[i]));
        }
    }

    // ---- message ID ----
    @Test
    public void testMessageIDCreated() {
        assertTrue(Message.checkMessageID(msg1.getMessageID()));
        assertEquals(10, msg1.getMessageID().length());
        System.out.println("Message ID generated: " + msg1.getMessageID());
    }

    @Test
    public void testMessageIDTooLong() {
        assertFalse(Message.checkMessageID("12345678901"));
    }

    // ---- send / disregard / store ----
    @Test
    public void testSendMessage() {
        assertEquals("Message successfully sent.", Message.sentMessage(1));
    }

    @Test
    public void testDisregardMessage() {
        assertEquals("Press 0 to delete the message.", Message.sentMessage(2));
    }

    @Test
    public void testStoreMessage() {
        assertEquals("Message successfully stored.", Message.sentMessage(3));
    }

    // ---- total messages sent (message 1 is sent, message 2 is discarded) ----
    @Test
    public void testTotalMessagesSent() {
        Message.addToSent(msg1);
        assertEquals(1, Message.returnTotalMessages());
    }

    @Test
    public void testPrintMessages() {
        Message.addToSent(msg1);
        String printed = Message.printMessages();
        assertTrue(printed.contains(msg1.getMessageID()));
        assertTrue(printed.contains(msg1.getMessageHash()));
        assertTrue(printed.contains(number1));
        assertTrue(printed.contains(text1));
    }
}
