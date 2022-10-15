/* Copyright (C) <Year> <Full Name (can be multiple persons) - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;

import static org.junit.Assert.*;

public class AccountTest {

    boolean enabledAssertThrowsTest = false;
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");


    @Test
    public void testAccountConstructor() {
        // 1 (C1b1, C2b1)
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> new Account(-1, -1));

        // 2 (C1b2, C2b1)
        Account account2 = new Account(0, 0);
        assertEquals(account2.getCustomerNumber(), 0);
        assertEquals(account2.getPinNumber(), 0);
        assertEquals(account2.getCustomerNumber(), account2.getPinNumber());

        // 3 (C1b3, C2b1)
        Account account3 = new Account(1, 1);
        assertEquals(account3.getCustomerNumber(), 1);
        assertEquals(account3.getPinNumber(), 1);
        assertEquals(account3.getCustomerNumber(), account3.getPinNumber());

        // 4 (C1b1, C2b2)
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> new Account(-1, 0));

        // 5 (C1b2, C2b2)
        Account account5 = new Account(0, 1);
        assertEquals(account5.getCustomerNumber(), 0);
        assertEquals(account5.getPinNumber(), 1);
        assertNotEquals(account5.getCustomerNumber(), account5.getPinNumber());

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
        Account account2 = new Account(0, 0, 0, 0);
        assertEquals(account2.getCheckingBalance(), 0,0.0);
        assertEquals(account2.getSavingBalance(), 0, 0.0);
        assertEquals(account2.getCustomerNumber(), account2.getPinNumber());

        // 3 (C1b3, C2b1)
        Account account3 = new Account(0, 0, 1, 1);
        assertEquals(account3.getCheckingBalance(), 1,0.0);
        assertEquals(account3.getSavingBalance(), 1, 0.0);
        assertEquals(account3.getCustomerNumber(), account3.getPinNumber());

        // 4 (C1b1, C2b2)
        if (enabledAssertThrowsTest)
            assertThrows(
                IllegalArgumentException.class,
                () -> new Account(0, 0, -1, 0)
            );


        // 5 (C1b2, C2b2)
        Account account5 = new Account(0, 0, 0, 1);
        assertEquals(account5.getCheckingBalance(), 0,0.0);
        assertEquals(account5.getSavingBalance(), 1, 0.0);

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
        Account account1 = new Account(0, 0, 1, 0);
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> account1.calcCheckingDeposit(0));

        // 2 (C1b2, C2b1)
        Account account2 = new Account(0, 0, 1, 0);
        assertEquals(account2.calcCheckingWithdraw(0), 1, 0.0);

        // 3 (C1b3, C2b1)
        Account account3 = new Account(0, 0, 1, 0);
        assertEquals(account3.calcCheckingWithdraw(1), 0, 0.0);
    }

    @Test
    public void testCalcCheckingDeposit() {
        // 1 (C1b1, C2b1)
        Account account1 = new Account(0, 0, 1, 0);
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> account1.calcCheckingDeposit(-1));

        // 2 (C1b2, C2b1)
        Account account2 = new Account(0, 0, 1, 0);
        assertEquals(account2.calcCheckingDeposit(0), 1, 0.0);

        // 3 (C1b3, C2b1)
        Account account3 = new Account(0, 0, 1, 0);
        assertEquals(account3.calcCheckingDeposit(1), 2, 0.0);
    }

    @Test
    public void testCalcCheckTransfer() { 
        // 1 (C1b1, C2b1)
        Account account1 = new Account(0, 0, 3, 0);
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> account1.calcCheckTransfer(-1));

        // 2 (C1b2, C2b1)
        Account account2 = new Account(0, 0, 3, 0);
        account2.calcCheckTransfer(0);
        assertEquals(account2.getCheckingBalance(), 3,0.0);
        assertEquals(account2.getSavingBalance(), 0, 0.0);

        // 3 (C1b3, C2b1)
        Account account3 = new Account(0, 0, 3, 0);
        account3.calcCheckTransfer(1);
        assertEquals(account3.getCheckingBalance(), 2,0.0);
        assertEquals(account3.getSavingBalance(), 1, 0.0);

        // Assume that we can set checkingBalance = -2 (Actually it can.)

        // 4 (C1b1, C2b2)
        Account account4 = new Account(0, 0, -2, 0);
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> account4.calcCheckingDeposit(-1));

        // 5 (C1b2, C2b2)
        Account account5 = new Account(0, 0, -2, 0);
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> account5.calcCheckingDeposit(0));

        // 6 (C1b3, C2b3)
        Account account6 = new Account(0, 0, -2, 0);
        if (enabledAssertThrowsTest)
            assertThrows(IllegalArgumentException.class, () -> account6.calcCheckingDeposit(2));
    }

    @Test
    public void testGetCheckingWithdrawInput() {
        Account account = new Account(111, 1234, 10, 10);
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        account.getCheckingWithdrawInput();

        System.setIn(stdin);
        System.setOut(stdout);

        String output = byteArrayOutputStream.toString();
        String expectedOutput = "\nCurrent Checkings Account Balance: " + moneyFormat.format(account.calcCheckingWithdraw(3));
        assertEquals(output, expectedOutput);
    }

    //getCheckingWithdrawInput ()
    //getSavingWithdrawInput ()
    //getCheckingDepositInput ()
    //getSavingDepositInput ()
    //getTransferInput ()
}
