package com.mycompany.progpoepart1;

public class Login {
    private String firstName;
    private String lastName;
    private String userName;
    private String userPassword;
    private String cellPhoneNumber;

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String uName) {
        this.userName = uName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    // Validation Methods
    public boolean checkUserName() {
        return userName != null && userName.contains("_") && userName.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        if (userPassword == null || userPassword.length() < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : userPassword.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return hasUpper && hasDigit && hasSpecial;
    }

    // NEW: checks the cell number is +27 followed by 9 digits (the Part 1 rule)
    public boolean checkCellPhoneNumber() {
        return cellPhoneNumber != null && cellPhoneNumber.matches("^\\+27\\d{9}$");
    }

    public String registerUser() {
        if (!checkUserName()) {
            return "The username is incorrectly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "The password does not meet the complexity requirements.";
        }
        return "The two conditions have been met and the user has been successfully added.";
    }

    public boolean loginUser(String enteredUser, String enteredPass) {
        return enteredUser != null &&
               enteredPass != null &&
               userName != null &&
               userPassword != null &&
               userName.equals(enteredUser) &&
               userPassword.equals(enteredPass);
    }

    public String returnLoginStatus(String enteredUser, String enteredPass) {
        if (loginUser(enteredUser, enteredPass)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}
