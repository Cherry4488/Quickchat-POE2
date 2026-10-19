package com.mycompany.progpoepart1;

public class LoginTest {

    public static void main(String[] args) {
        System.out.println("=== RUNNING LOGIN TESTS ===");
        
        LoginTest test = new LoginTest();
        
        try {
            test.testSetUsername();
            System.out.println("[PASS] testSetUsername");
        } catch (Throwable e) {
            System.out.println("[FAIL] testSetUsername: " + e.getMessage());
        }

        try {
            test.testSetPassword();
            System.out.println("[PASS] testSetPassword");
        } catch (Throwable e) {
            System.out.println("[FAIL] testSetPassword: " + e.getMessage());
        }

        try {
            test.testSetCellPhoneNumber();
            System.out.println("[PASS] testSetCellPhoneNumber");
        } catch (Throwable e) {
            System.out.println("[FAIL] testSetCellPhoneNumber: " + e.getMessage());
        }

        // ---- NEW (Part 2): tests for the cell number check ----
        try {
            test.testCellPhoneNumberCorrect();
            System.out.println("[PASS] testCellPhoneNumberCorrect");
        } catch (Throwable e) {
            System.out.println("[FAIL] testCellPhoneNumberCorrect: " + e.getMessage());
        }

        try {
            test.testCellPhoneNumberNoInternationalCode();
            System.out.println("[PASS] testCellPhoneNumberNoInternationalCode");
        } catch (Throwable e) {
            System.out.println("[FAIL] testCellPhoneNumberNoInternationalCode: " + e.getMessage());
        }

        try {
            test.testCellPhoneNumberTooLong();
            System.out.println("[PASS] testCellPhoneNumberTooLong");
        } catch (Throwable e) {
            System.out.println("[FAIL] testCellPhoneNumberTooLong: " + e.getMessage());
        }

        try {
            test.testCellPhoneNumberWrongCountryCode();
            System.out.println("[PASS] testCellPhoneNumberWrongCountryCode");
        } catch (Throwable e) {
            System.out.println("[FAIL] testCellPhoneNumberWrongCountryCode: " + e.getMessage());
        }
    }

    public void testSetUsername() {
        String uName = "kyl_1";
        Login instance = new Login();
        instance.setUsername(uName);
        if (!uName.equals(instance.getUserName())) {
            throw new AssertionError("Expected " + uName + " but got " + instance.getUserName());
        }
    }

    public void testSetPassword() {
        String pWord = "Ch3ck#p@ss";
        Login instance = new Login();
        instance.setPassword(pWord);
        if (!pWord.equals(instance.getUserPassword())) {
            throw new AssertionError("Expected " + pWord + " but got " + instance.getUserPassword());
        }
    }

    public void testSetCellPhoneNumber() {
        String phone = "+27821234567";
        Login instance = new Login();
        instance.setCellPhoneNumber(phone);
        if (!phone.equals(instance.getCellPhoneNumber())) {
            throw new AssertionError("Expected " + phone + " but got " + instance.getCellPhoneNumber());
        }
    }

    // ---- NEW (Part 2): cell number check tests ----
    public void testCellPhoneNumberCorrect() {
        Login instance = new Login();
        instance.setCellPhoneNumber("+27821234567");
        if (!instance.checkCellPhoneNumber()) {
            throw new AssertionError("Expected +27821234567 to be accepted");
        }
    }

    public void testCellPhoneNumberNoInternationalCode() {
        Login instance = new Login();
        instance.setCellPhoneNumber("0821234567");
        if (instance.checkCellPhoneNumber()) {
            throw new AssertionError("Expected 0821234567 to be rejected");
        }
    }

    public void testCellPhoneNumberTooLong() {
        Login instance = new Login();
        instance.setCellPhoneNumber("+2782123456789012");
        if (instance.checkCellPhoneNumber()) {
            throw new AssertionError("Expected +2782123456789012 to be rejected");
        }
    }

    public void testCellPhoneNumberWrongCountryCode() {
        Login instance = new Login();
        instance.setCellPhoneNumber("+44821234567");
        if (instance.checkCellPhoneNumber()) {
            throw new AssertionError("Expected +44821234567 to be rejected");
        }
    }
}
