/* Copyright (C) 2022 Natanon Ritta et al. - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class AccountTest {

    // Attribute for testing
    private double actualWithdraw;
    private Account account;
    private String[] output;
    private String type;
    private String operation;

    // Control Flag
    boolean enabledAssertThrowsTest = false;

    // Utilities for test method
    static DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    private static boolean hasAnyChar(String input) {
        String firstChar = input.substring(0, 1);
        if (firstChar.equals("-")) {
            return false;
        }
        try {
            double d = Double.parseDouble(firstChar);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    private static String[] testGetInputUtil(Account account, String inputAmount, double initBalance, String type, String operation) {
        String input = inputAmount + "\n";
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        System.setIn(stdin);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        String whichBe = "Be";
        String junction = " from ";

        if (operation.equals("withdraw")) {
            if (type.equals("Checkings")) {
                account.getCheckingWithdrawInput();
                whichBe = "be";
            }
            else if (type.equals("Savings")) {
                account.getsavingWithdrawInput();
            }
        }
        else if (operation.equals("deposit")) {
            if (type.equals("Checkings")) {
                account.getCheckingDepositInput();
            }
            else if (type.equals("Savings")) {
                account.getSavingDepositInput();
                junction = " into your ";
            }
        }

        System.setOut(stdout);
        String output = byteArrayOutputStream.toString();
        String defaultDisplayMessage = "\nCurrent " + type + " Account Balance: "
                + moneyFormat.format(initBalance) + "\n" +
                "\nAmount you want to " + operation + junction + type + " Account: ";

        char[] inputChars = inputAmount.toCharArray();

        String negativeValueMessage;
        if (!hasAnyChar(inputAmount)) {
            negativeValueMessage =
                    ((inputChars[0] == '-') || (Double.parseDouble(inputAmount) > initBalance))
                            ? "\nBalance Cannot " + whichBe + " Negative.\n" + defaultDisplayMessage
                            : "";
        }
        else {
            negativeValueMessage = "";
        }

        double balance = type.equals("Checkings") ? account.getCheckingBalance() : account.getSavingBalance();
        String invalidChoiceMessage = hasAnyChar(inputAmount) ? "\nInvalid Choice.\n" + defaultDisplayMessage : "";
        String expectedOutput = defaultDisplayMessage + negativeValueMessage + invalidChoiceMessage +
                "\nCurrent " + type + " Account Balance: " + moneyFormat.format(balance) + "\n";

        return new String[]{output, expectedOutput};
    }

    // Test cases

    @Test
    public void testAccountConstructor() {
        // 1 (C1b1, C2b1)
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> new Account(-1, -1));

        // 2 (C1b2, C2b1)
        account = new Account(0, 0);
        assertEquals(account.getCustomerNumber(), 0);
        assertEquals(account.getPinNumber(), 0);
        assertEquals(account.getCustomerNumber(), account.getPinNumber());

        // 3 (C1b3, C2b1)
        account = new Account(1, 1);
        assertEquals(account.getCustomerNumber(), 1);
        assertEquals(account.getPinNumber(), 1);
        assertEquals(account.getCustomerNumber(), account.getPinNumber());

        // 4 (C1b1, C2b2)
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> new Account(-1, 0));

        // 5 (C1b2, C2b2)
        account = new Account(0, 1);
        assertEquals(account.getCustomerNumber(), 0);
        assertEquals(account.getPinNumber(), 1);
        assertNotEquals(account.getCustomerNumber(), account.getPinNumber());

        // 6 (C1b3, C2b2)
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> new Account(1, -1));

    }

    @Test
    public void testAccountWithBalanceConstructor() {
        // 1 (C1b1, C2b1)
        if (enabledAssertThrowsTest)
            assertThrows(
                IllegalArgumentException.class,
                () -> new Account(0, 0, -1, -1)
            );

        // 2 (C1b2, C2b1)
        account = new Account(0, 0, 0, 0);
        assertEquals(account.getCheckingBalance(), 0,0.0);
        assertEquals(account.getSavingBalance(), 0, 0.0);
        assertEquals(account.getCustomerNumber(), account.getPinNumber());

        // 3 (C1b3, C2b1)
        account = new Account(0, 0, 1, 1);
        assertEquals(account.getCheckingBalance(), 1,0.0);
        assertEquals(account.getSavingBalance(), 1, 0.0);
        assertEquals(account.getCustomerNumber(), account.getPinNumber());

        // 4 (C1b1, C2b2)
        if (enabledAssertThrowsTest)
            assertThrows(
                IllegalArgumentException.class,
                () -> new Account(0, 0, -1, 0)
            );


        // 5 (C1b2, C2b2)
        Account account = new Account(0, 0, 0, 1);
        assertEquals(account.getCheckingBalance(), 0,0.0);
        assertEquals(account.getSavingBalance(), 1, 0.0);

        // 6 (C1b3, C2b2)
        if (enabledAssertThrowsTest)
            assertThrows(
                IllegalArgumentException.class,
                () -> new Account(0, 0, 1, -1)
            );
    }

    @Test
    public void testCalcCheckingWithdraw() {
        // 1 (C1b1, C2b1)
        account = new Account(0, 0, 1, 0);
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> account.calcCheckingDeposit(0));

        // 2 (C1b2, C2b1)
        account = new Account(0, 0, 1, 0);
        assertEquals(account.calcCheckingWithdraw(0), 1, 0.0);

        // 3 (C1b3, C2b1)
        account = new Account(0, 0, 1, 0);
        assertEquals(account.calcCheckingWithdraw(1), 0, 0.0);
    }

    @Test
    public void testCalcCheckingDeposit() {
        // 1 (C1b1, C2b1)
        account = new Account(0, 0, 1, 0);
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> account.calcCheckingDeposit(-1));

        // 2 (C1b2, C2b1)
        account = new Account(0, 0, 1, 0);
        assertEquals(account.calcCheckingDeposit(0), 1, 0.0);

        // 3 (C1b3, C2b1)
        account = new Account(0, 0, 1, 0);
        assertEquals(account.calcCheckingDeposit(1), 2, 0.0);
    }

    @Test
    public void testCalcCheckTransfer() { 
        // 1 (C1b1, C2b1)
        account = new Account(0, 0, 3, 0);
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> account.calcCheckTransfer(-1));

        // 2 (C1b2, C2b1)
        account = new Account(0, 0, 3, 0);
        account.calcCheckTransfer(0);
        assertEquals(account.getCheckingBalance(), 3,0.0);
        assertEquals(account.getSavingBalance(), 0, 0.0);

        // 3 (C1b3, C2b1)
        account = new Account(0, 0, 3, 0);
        account.calcCheckTransfer(1);
        assertEquals(account.getCheckingBalance(), 2,0.0);
        assertEquals(account.getSavingBalance(), 1, 0.0);

        // Assume that we can set checkingBalance = -2 (Actually it can.)

        // 4 (C1b1, C2b2)
        account = new Account(0, 0, -2, 0);
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> account.calcCheckingDeposit(-1));

        // 5 (C1b2, C2b2)
        account = new Account(0, 0, -2, 0);
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> account.calcCheckingDeposit(0));

        // 6 (C1b3, C2b3)
        account = new Account(0, 0, -2, 0);
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> account.calcCheckingDeposit(2));
    }

    @Test
    public void testGetCheckingWithdrawInput() {
        double initBalance = 100;
        type = "Checkings";
        operation = "withdraw";

        // 1 (C1b1, C2b1)
        actualWithdraw = 1;
        account = new Account(0,0, initBalance, 0);
        output = testGetInputUtil(account, "" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getCheckingBalance(), initBalance - actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 2 (C1b1, C2b2)
        actualWithdraw = 1;
        account = new Account(0,0, initBalance, 0);
        String[] output = testGetInputUtil(account, "-1\n" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getCheckingBalance(), initBalance - actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 3 (C1b1, C2b3)
        actualWithdraw = 1;
        account = new Account(0,0, initBalance, 0);
        output = testGetInputUtil(account, "a\n" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getCheckingBalance(), initBalance - actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 4 (C1b1, C2b4)
        actualWithdraw = 0;
        account = new Account(0,0, initBalance, 0);
        assertThrows(
                NoSuchElementException.class,
                () -> testGetInputUtil(account, "-1", initBalance, type, operation));
        assertEquals(account.getCheckingBalance(), initBalance - actualWithdraw, 0.0);

        // 5 (C1b2, C2b1)
        actualWithdraw = 10;
        account = new Account(0,0, initBalance, 0);
        output = testGetInputUtil(account, "" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getCheckingBalance(), initBalance - actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 6 (C1b2, C2b2)
        actualWithdraw = 10;
        account = new Account(0,0, initBalance, 0);
        output = testGetInputUtil(account, "-10\n" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getCheckingBalance(), initBalance - actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 7 (C1b2, C2b3)
        actualWithdraw = 10;
        account = new Account(0,0, initBalance, 0);
        output = testGetInputUtil(account, "a\n" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getCheckingBalance(), initBalance - actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 8 (C1b2, C2b4)
        actualWithdraw = 0;
        account = new Account(0,0, initBalance, 0);
        assertThrows(
                NoSuchElementException.class,
                () -> testGetInputUtil(account, "-10", initBalance, type, operation));
        assertEquals(account.getCheckingBalance(), initBalance - actualWithdraw, 0.0);
    }

    @Test
    public void testGetSavingWithdrawInput() {
        double initBalance = 100;
        type = "Savings";
        operation = "withdraw";

        // 1 (C1b1, C2b1)
        actualWithdraw = 1;
        account = new Account(0,0, 0, initBalance);
        output = testGetInputUtil(account, "" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getSavingBalance(), initBalance - actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 2 (C1b2, C2b1) [Base Choice]
        actualWithdraw = 10;
        account = new Account(0,0, 0, initBalance);
        output = testGetInputUtil(account, "" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getSavingBalance(), initBalance - actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 3 (C1b2, C2b2)
        actualWithdraw = 10;
        account = new Account(0,0, 0, initBalance);
        output = testGetInputUtil(account, "-10\n" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getSavingBalance(), initBalance - actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 4 (C1b2, C2b3)
        actualWithdraw = 10;
        account = new Account(0,0, 0, initBalance);
        output = testGetInputUtil(account, "a\n" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getSavingBalance(), initBalance - actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);


        // 5 (C1b2, C2b4)
        actualWithdraw = 0;
        account = new Account(0,0, 0, initBalance);
        assertThrows(
                NoSuchElementException.class,
                () -> testGetInputUtil(account, "-10", initBalance, type, operation));
        assertEquals(account.getSavingBalance(), initBalance - actualWithdraw, 0.0);
    }

    @Test
    public void testGetCheckingDepositInput() {
        double initBalance = 100;
        type = "Checkings";
        operation = "deposit";

        // 1 (C1b1, C2b1)
        actualWithdraw = 1;
        account = new Account(0,0, initBalance, 0);
        output = testGetInputUtil(account, "" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getCheckingBalance(), initBalance + actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 2 (C1b2, C2b1) [Base Choice]
        actualWithdraw = 10;
        account = new Account(0,0, initBalance, 0);
        output = testGetInputUtil(account, "" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getCheckingBalance(), initBalance + actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 3 (C1b2, C2b2)
        actualWithdraw = 10;
        account = new Account(0,0, initBalance, 0);
        output = testGetInputUtil(account, "-10\n" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getCheckingBalance(), initBalance + actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 4 (C1b2, C2b3)
        actualWithdraw = 10;
        account = new Account(0,0, initBalance, 0);
        output = testGetInputUtil(account, "a\n" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getCheckingBalance(), initBalance + actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);


        // 5 (C1b2, C2b4)
        actualWithdraw = 0;
        account = new Account(0,0, initBalance, 0);
        assertThrows(
                NoSuchElementException.class,
                () -> testGetInputUtil(account, "-10", initBalance, type, operation));
        assertEquals(account.getCheckingBalance(), initBalance - actualWithdraw, 0.0);
    }

    @Test
    public void testGetSavingDepositInput() {
        double initBalance = 100;
        type = "Savings";
        operation = "deposit";

        // 1 (C1b1, C2b1) [Base Choice]
        actualWithdraw = 1;
        account = new Account(0,0, 0, initBalance);
        output = testGetInputUtil(account, "" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getSavingBalance(), initBalance + actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 2 (C1b1, C2b2) [Base Choice]
        actualWithdraw = 1;
        account = new Account(0,0, 0, initBalance);
        output = testGetInputUtil(account, "-1\n" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getSavingBalance(), initBalance + actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 3 (C1b2, C2b1)
        actualWithdraw = 10;
        account = new Account(0,0, 0, initBalance);
        output = testGetInputUtil(account, "" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getSavingBalance(), initBalance + actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 4 (C1b2, C2b2)
        actualWithdraw = 10;
        account = new Account(0,0, 0, initBalance);
        output = testGetInputUtil(account, "-10\n" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getSavingBalance(), initBalance + actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 5 (C1b1, C2b3)
        actualWithdraw = 1;
        account = new Account(0,0, 0, initBalance);
        output = testGetInputUtil(account, "a\n" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getSavingBalance(), initBalance + actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 6 (C1b1, C2b4)
        actualWithdraw = 0;
        account = new Account(0,0, 0, initBalance);
        assertThrows(
                NoSuchElementException.class,
                () -> testGetInputUtil(account, "a", initBalance, type, operation));
        assertEquals(account.getSavingBalance(), initBalance + actualWithdraw, 0.0);

        // 7 #5 duplication
        actualWithdraw = 2;
        account = new Account(0,0, 0, initBalance);
        output = testGetInputUtil(account, "b\n" + actualWithdraw, initBalance, type, operation);
        assertEquals(account.getSavingBalance(), initBalance + actualWithdraw, 0.0);
        assertEquals(output[0], output[1]);

        // 8 #6 duplication
        actualWithdraw = 0;
        account = new Account(0,0, 0, initBalance);
        assertThrows(
                NoSuchElementException.class,
                () -> testGetInputUtil(account, "b", initBalance, type, operation));
        assertEquals(account.getSavingBalance(), initBalance + actualWithdraw, 0.0);
    }

    private static void testGetTransferInputUtil(String type, String inputSelection, String inputAmount, Account account, double initBalance) {
        String input = inputSelection + "\n" + inputAmount + "\n";
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        System.setIn(stdin);

        account.getTransferInput(type);
    }

    @Test
    public void testGetTransferInput() {
        double initBalance = 100;
        double actualAmount;

        // 1 (C1b1, C2b1, C3b1, C4b1)
        type = null;
        actualAmount = 0;
        account = new Account(0, 0, initBalance, initBalance);
        assertThrows(
                NullPointerException.class,
                () -> testGetTransferInputUtil(type, "1", "1", account, initBalance));
        assertEquals(account.getCheckingBalance(), initBalance - actualAmount, 0.0);
        assertEquals(account.getSavingBalance(), initBalance + actualAmount, 0.0);

        // 2 (C1b2, C2b2, C3b2, C4b2)
        type = "Savings";
        actualAmount = 1;
        account = new Account(0, 0, initBalance, initBalance);
        testGetTransferInputUtil(type, "3\n1", "1\n" + actualAmount , account, initBalance);
        assertEquals(account.getCheckingBalance(), initBalance + actualAmount, 0.0);
        assertEquals(account.getSavingBalance(), initBalance - actualAmount, 0.0);

        // 3 (C1b1, C2b1, C3b3, C4b1)
        type = null;
        actualAmount = 0;
        account = new Account(0, 0, initBalance, initBalance);
        assertThrows(
                NullPointerException.class,
                () -> testGetTransferInputUtil(type, "-3", "1", account, initBalance));
        assertEquals(account.getCheckingBalance(), initBalance - actualAmount, 0.0);
        assertEquals(account.getSavingBalance(), initBalance + actualAmount, 0.0);

        // 4 (C1b2, C2b2, C3b3, C4b2)
        type = "Savings";
        actualAmount = 0;
        account = new Account(0, 0, initBalance, initBalance);
        assertThrows(
                NoSuchElementException.class,
                () -> testGetTransferInputUtil(type, "3", "-1\n1", account, initBalance));
        assertEquals(account.getCheckingBalance(), initBalance - actualAmount, 0.0);
        assertEquals(account.getSavingBalance(), initBalance + actualAmount, 0.0);

        // 5 (C1b1, C2b1, C3b1, C4b3)
        type = null;
        actualAmount = 0;
        account = new Account(0, 0, initBalance, initBalance);
        assertThrows(
                NullPointerException.class,
                () -> testGetTransferInputUtil(type, "1", "a\n1", account, initBalance));
        assertEquals(account.getCheckingBalance(), initBalance - actualAmount, 0.0);
        assertEquals(account.getSavingBalance(), initBalance + actualAmount, 0.0);

        // 6 (C1b1, C2b1, C3b1, C4b4)
        type = null;
        actualAmount = 0;
        account = new Account(0, 0, initBalance, initBalance);
        assertThrows(
                NullPointerException.class,
                () -> testGetTransferInputUtil(type, "1", "-3", account, initBalance));
        assertEquals(account.getCheckingBalance(), initBalance - actualAmount, 0.0);
        assertEquals(account.getSavingBalance(), initBalance + actualAmount, 0.0);

        // 7 (C1b2, C2b2, C3b2, C4b3)
        type = "Savings";
        actualAmount = 1;
        account = new Account(0, 0, initBalance, initBalance);
        testGetTransferInputUtil(type, "3\n1", "a\n1\n" + actualAmount , account, initBalance);
        assertEquals(account.getCheckingBalance(), initBalance + actualAmount, 0.0);
        assertEquals(account.getSavingBalance(), initBalance - actualAmount, 0.0);

        // 8 (C1b2, C2b2, C3b2, C4b4)
        type = "Savings";
        actualAmount = 0;
        account = new Account(0, 0, initBalance, initBalance);
        testGetTransferInputUtil(type, "3\n2", "-1", account, initBalance);
        assertEquals(account.getCheckingBalance(), initBalance - actualAmount, 0.0);
        assertEquals(account.getSavingBalance(), initBalance + actualAmount, 0.0);
    }

}
