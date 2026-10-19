# QuickChat – POE Part 2

Name:Charity 
Student number:st10460650
Module:PROG5121
Institution:Rosebank College

---

what the program does

1. The user registers (first name, last name, username, password and cell number) and then logs in. This is my Part 1 code.
2. Only a user who logs in successfully can use QuickChat.
3. QuickChat shows "Welcome to QuickChat."and asks how many messages the user wants to enter.
4. The user picks from a menu until they choose to quit:
   1) Send Messages
   2) Show recently sent messages** (still in development, shows "Coming Soon.")
   3) Quit
5. For each message the user enters a recipient number and the message text. The program then asks them to:
   - Sendthe message ("Message successfully sent.")
   - Disregard it ("Press 0 to delete the message.")
   - Store it to send later ("Message successfully stored.")
6. After a message is sent, its details are displayed in this order: Message ID, Message Hash, Recipient, Message.
7. When all the messages have been entered, the total number of messages sent is displayed.

Rules the program checks
| Message ID | Random ten-digit number, no more than ten characters |
| Recipient number | Must be `+27` followed by 9 digits (the same rule used in Part 1) |
| Message length | No more than 250 characters. If it is too long, the program says how many characters over the limit it is |
| Message hash | First two digits of the ID, a colon, the message number, a colon, then the first and last words of the message in capitals, e.g. `00:0:HITONIGHT` |

---

Structure of the program

```
Quickchat-POE2/
├── pom.xml
├── .github/workflows/maven.yml     (GitHub Actions: runs the unit tests)
└── src/
    ├── main/java/com/mycompany/progpoepart1/
    │   ├── Login.java              (Part 1 login class + checkCellPhoneNumber())
    │   ├── Progpoepart1.java       (main class: register, login, then start QuickChat)
    │   ├── Message.java            (message details and all message methods)
    │   └── QuickChat.java          (menu and user input)
    └── test/java/com/mycompany/progpoepart1/
        ├── LoginTest.java          (Part 1 tests + tests for the cell number check)
        └── MessageTest.java        (JUnit tests for the Message class)
```

What each does

Login– stores and validates the user's details (username, password, cell number) and handles registering and logging in. In Part 2 I added `checkCellPhoneNumber()` so the same cell number rule can be reused for recipients.
Progpoepart1– the main class. After a successful login it calls `QuickChat.startChat(...)`.
Message– holds the ID, hash, recipient and text of a message. Its methods are:
  - `createMessageID()` / `checkMessageID()`
  - `checkRecipientCell()` (reuses the Login cell number check)
  - `checkMessageLength()`
  - `createMessageHash()`
  - `sentMessage()`
  - `addToSent()`, `printMessages()`, `returnTotalMessages()`
  - `storeMessage()` (saves stored messages to `messages.json`)
  QuickChat– runs the menu loop and asks the user for input, repeating the question until the input is valid.



REFERENCES:
JSON.org (n.d.) *Introducing JSON*. Available at: https://www.json.org/json-en.html (Accessed: 19 October 2026).

JUnit Team (n.d.) *JUnit 5 User Guide*. Available at: https://junit.org/junit5/docs/current/user-guide/ (Accessed: 19 October 2026).

Oracle (n.d.a) *FileWriter (Java SE 17 & JDK 17)*. Available at: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/FileWriter.html (Accessed: 19 October 2026).


How to run it

1. Open the project in NetBeans with File → Open Project.
2. Right-click the project and choose Clean and Build.
3. Right-click `Progpoepart1.java` and choose Run File.
