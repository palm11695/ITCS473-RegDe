/* Copyright (C) <Year> <Full Name (can be multiple persons) - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    /*
        #1 testAccountConstructor
        Goal: to test if Account object is created correctly

        a) Identify testable function
            Account(int customerNumber, int pinNumber)

        b) Identify parameters, return types, return values, and exceptional behavior
            Parameters: customerNumber, pinNumber
            Return type: Account
            Return value: Account object that is set 2 class attributes (as params)
            Exceptional behavior: -

        c) Model the input domain
            C1: relation of 'customerNumber' to 0 (interface-based)
                b1: less than 0
                b2: equal to 0
                b3: more than 0
            C2: relation between 'customerNumber' and 'pinNumber' (functionality-based)
                b1: 'customerNumber' and 'pinNumber' are the same.
                b2: 'customerNumber' and 'pinNumber' are different.

        d) Combine partitions to define test requirements
            ACoC: 3*2 = 6
            (C1b1, C2b1), (C1b2, C2b1), (C1b3, C2b1),
            (C1b1, C2b2), (C1b2, C2b2), (C1b3, C2b2)

        e) Derive test values and expected values.
            1) (-1, -1) -> Expected values: Throw Error (Value shouldn't be negative)
            2) (0, 0) ->  Expected values: Account Object with customerNumber = 0, pinNumber = 0
            3) (1, 1) ->  Expected values: Account Object with customerNumber = 1, pinNumber = 1
            4) (-1, 0) ->  Expected values: Throw Error (Value shouldn't be negative)
            5) (0, 1) ->   Expected values: Account Object with customerNumber = 0, pinNumber = 1
            6) (1, -1) ->   Expected values: Throw Error (Value shouldn't be negative)
    */
    @Test
    public void testAccountConstructor() {
        Account account = new Account(123, 1111);
        assertEquals(account, account);
    }

    /*
        #2 testAccountWithBalanceConstructor
        Goal: to test if Account object is create correctly

        a) Identify testable function
            Account(int customerNumber, int pinNumber, double checkingBalance, double savingsBalance)

        b) Identify parameters, return types, return values, and exceptional behavior
            Parameters: customerNumber, pinNumber, checkingBalance, savingsBalance
            Return type: Account
            Return value: Account object that is set 4 class attributes (as params)
            Exceptional behavior: -

        c) Model the input domain
            C1: relation of 'checkingBalance' to 0 (interface-based)
                b1: less than 0
                b2: equal to 0
                b3: more than 0
            C3: relation between 'checkingBalance' and 'savingBalance' (functionality-based)
                b1: 'checkingBalance' and 'savingBalance' are the same.
                b2: 'checkingBalance' and 'savingBalance' are different.

        d) Combine partitions to define test requirements
            ACoC: 3*2 = 6
            (C1b1, C2b1), (C1b2, C2b1), (C1b3, C2b1),
            (C1b1, C2b2), (C1b2, C2b2), (C1b3, C2b2)

        e) Derive test values and expected values.
            1) (0, 0, -1, -1) ->  Expected values: Throw Error (Value shouldn't be negative)
            2) (0, 0, 0, 0) ->  Expected values: Account Object with customerNumber = 0, pinNumber = 0, checkingBalance = 0, savingBalance = 0
            3) (0, 0, 1, 1) ->  Expected values: Account Object with customerNumber = 0, pinNumber = 0, checkingBalance = 1, savingBalance = 1
            4) (0, 0, -1, 0) ->  Expected values: Throw Error (Value shouldn't be negative)
            5) (0, 0, 0, 1) ->  Expected values: Account Object with customerNumber = 0, pinNumber = 0, checkingBalance = 0, savingBalance = 1
            6) (0, 0, 1, -1) ->  Expected values: Throw Error (Value shouldn't be negative)
    */
    @Test
    public void testAccountWithBalanceConstructor() {

    }

    //calcCheckingWithdraw ()
    //calcCheckingDeposit ()
    //calcCheckTransfer ()

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
