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
        // 1 (C1b1, C2b1)
        Account account1 = new Account(-1, -1);
        assertEquals(account1.getCustomerNumber(), -1);
        assertEquals(account1.getPinNumber(), -1);
        // 2 (C1b2, C2b1)
        Account account2 = new Account(0, 0);
        assertEquals(account2.getCustomerNumber(), 0);
        assertEquals(account2.getPinNumber(), 0);
        // 3 (C1b3, C2b1)
        Account account3 = new Account(1, 1);
        assertEquals(account3.getCustomerNumber(), 1);
        assertEquals(account3.getPinNumber(), 1);
        // 4 (C1b1, C2b2)
        Account account4 = new Account(-1, 0);
        assertEquals(account4.getCustomerNumber(), -1);
        assertEquals(account4.getPinNumber(), 0);
        // 5 (C1b2, C2b2)
        Account account5 = new Account(0, 1);
        assertEquals(account5.getCustomerNumber(), 0);
        assertEquals(account5.getPinNumber(), 1);
        // 6 (C1b3, C2b2)
        Account account6 = new Account(1, -1);
        assertEquals(account6.getCustomerNumber(), 1);
        assertEquals(account6.getPinNumber(), -1);

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
        // 1 (C1b1, C2b1)
        Account account1 = new Account(0, 0, -1, -1);
        assertEquals(account1.getCheckingBalance(), -1,0.0);
        assertEquals(account1.getSavingBalance(), -1, 0.0);
        // 2 (C1b2, C2b1)
        Account account2 = new Account(0, 0, 0, 0);
        assertEquals(account2.getCheckingBalance(), 0,0.0);
        assertEquals(account2.getSavingBalance(), 0, 0.0);
        // 3 (C1b3, C2b1)
        Account account3 = new Account(0, 0, 1, 1);
        assertEquals(account3.getCheckingBalance(), 1,0.0);
        assertEquals(account3.getSavingBalance(), 1, 0.0);
        // 4 (C1b1, C2b2)
        Account account4 = new Account(0, 0, -1, 0);
        assertEquals(account4.getCheckingBalance(), -1,0.0);
        assertEquals(account4.getSavingBalance(), 0, 0.0);
        // 5 (C1b2, C2b2)
        Account account5 = new Account(0, 0, 0, 1);
        assertEquals(account5.getCheckingBalance(), 0,0.0);
        assertEquals(account5.getSavingBalance(), 1, 0.0);
        // 6 (C1b3, C2b2)
        Account account6 = new Account(0, 0, 1, -1);
        assertEquals(account6.getCheckingBalance(), 1,0.0);
        assertEquals(account6.getSavingBalance(), -1, 0.0);
    }

    //calcCheckingWithdraw ()
    /*
        #3 testCalcCheckingWithdraw
        Goal: to test if the checking withdraw is calculated correctly

        a) Identify testable function
            calcCheckingWithdraw(double amount)

        b) Identify parameters, return types, return values, and exceptional behavior
            Parameters: amount
            Return type: double
            Return value: the amount of checking balance after withdraw
            Exceptional behavior: - 'throw error message'

        c) Model the input domain
            C1: relation of 'amount' to 0 (interface-based)
                b1: less than 0
                b2: equal to 0
                b3: more than 0
            C2: relation between 'checkingBalance' and 'amount' (functionality-based)
                b1: 'amount' is less than 'checkingBalance'
                b2: 'amount' is more than 'checkingBalance'

                checkingBalance = 1

        d) Combine partitions to define test requirements
            ECC: 3 (from C1)
            (C1b1, C2b1), (C1b2, C2b1), (C1b3, C2b1)
             
        e) Derive test values and expected values.
            Assume checkingBalance = 1
            1) (-1) -> Expected values: 'throw error message'
            2) (0) ->  Expected values: 1
            3) (1) ->  Expected values: 0        
    */

    @Test
    public void testCalcCheckingWithdraw() {
        // 1 (C1b1, C2b1)
        Account account1 = new Account(0, 0, 1, 0);
        assertEquals(account1.calcCheckingDeposit(0), "exception message");
        // 2 (C1b2, C2b1)
        Account account2 = new Account(0, 0, 1, 0);
        assertEquals(account2.calcCheckingWithdraw(0), 1, 0.0);
        // 3 (C1b3, C2b1)
        Account account3 = new Account(0, 0, 1, 0);
        assertEquals(account3.calcCheckingWithdraw(1), 0, 0.0);
    }
    //calcCheckingDeposit ()
    /*
        #4 testCalcCheckingDeposit
        Goal: to test if the checking deposit is calculated correctly

        a) Identify testable function
            calcCheckingDeposit(double amount)

        b) Identify parameters, return types, return values, and exceptional behavior
            Parameters: amount
            Return type: double
            Return value: the amount of checking balance after deposit
            Exceptional behavior: - 'throw error message'

        c) Model the input domain
            C1: relation of 'amount' to 0 (interface-based)
                b1: less than 0
                b2: equal to 0
                b3: more than 0
            C2: relation between 'checkingBalance' and 'amount' (functionality-based)
                b1: 'amount' is more than 'checkingBalance'
                b2: 'checkingbalance 'after calculation is less than 0

                checkingBalance = 1

        d) Combine partitions to define test requirements
            ECC: 3 (from C1)
            (C1b1, C2b1), (C1b2, C2b1), (C1b3, C2b1)
             

        e) Derive test values and expected values.
            Assume checkingBalance = 1
            1) (-1) -> Expected values: 'throw error message'
            2) (0) ->  Expected values: 1
            3) (1) ->  Expected values: 2          
    */
    @Test
    public void testCalcCheckingDeposit() {
        Account account1 = new Account(0, 0, 1, 0);
        // 1 (C1b1, C2b1)
        assertEquals(account1.calcCheckingDeposit(0), "exception message");
        // 2 (C1b2, C2b1)
        Account account2 = new Account(0, 0, 1, 0);
        assertEquals(account2.calcCheckingDeposit(0), 1, 0.0);
        // 3 (C1b3, C2b1)
        Account account3 = new Account(0, 0, 1, 0);
        assertEquals(account3.calcCheckingDeposit(1), 2, 0.0);
    }
    
    //calcCheckTransfer ()
    /*
        #5 testCalcCheckTransfer
        Goal: to test if the checking tranfers is calculated correctly

        a) Identify testable function
            calcCheckTransfer(double amount)

        b) Identify parameters, return types, return values, and exceptional behavior
            Parameters: amount
            Return type: double
            Return value: value of checkingBalance and savingBalance after transfer
            Exceptional behavior: - 'throw error message'

        c) Model the input domain
            C1: relation of 'amount' to 0 (interface-based)
                b1: less than 0
                b2: equal to 0
                b3: more than 0
            C2: relation between 'checkingBalance' and 'amount' (functionality-based)
                b1: 'amount' is less than 'checkingBalance'
                b2: 'amount' is more than 'checkingBalance'

                checkingBalance = 1

        d) Combine partitions to define test requirements
            PWC: 3*2 = 6
            (C1b1, C2b1), (C1b2, C2b1), (C1b3, C2b1),
            (C1b1, C2b2), (C1b2, C2b2), (C1b3, C2b3)

        e) Derive test values and expected values.
            Assume checkingBalance = 3, savingsBalance = 0
            1) (-1) -> Expected values: 'throw error message'
            2) (0) ->  Expected values: checkingbalance = 3, savingBalance = 0
            3) (1) ->  Expected values: checkingbalance = 2, savingBalance = 1
            Assume checkingBalance = -2
            4) (-1) -> Expected values: 'throw error message'
            5) (0) ->  Expected values: 'throw error message'
            6) (2) ->  Expected values: 'throw error message'  
    */
    @Test
    public void testCalcCheckTransfer() { 
        // 1 (C1b1, C2b1)
        Account account1 = new Account(0, 0, 3, 0);
        assertEquals(account1.calcCheckingDeposit(0), "exception message");
        // 2 (C1b2, C2b1)
        Account account2 = new Account(0, 0, 3, 0);
        account2.calcCheckTransfer(0);
        assertEquals(account2.getCheckingBalance(), 3,0.0);
        assertEquals(account2.getSavingBalance(), 1, 0.0);
        // 3 (C1b3, C2b1)
        Account account3 = new Account(0, 0, 3, 0);
        account3.calcCheckTransfer(1);
        assertEquals(account3.getCheckingBalance(), 0,0.0);
        assertEquals(account3.getSavingBalance(), 1, 0.0);
        // 4 (C1b1, C2b2)
        Account account4 = new Account(0, 0, -2, 0);
        assertEquals(account4.calcCheckingDeposit(0), "exception message");
        // 5 (C1b2, C2b2)
        Account account5 = new Account(0, 0, -2, 0);
        assertEquals(account5.calcCheckingDeposit(0), "exception message");
        // 6 (C1b3, C2b3)
        Account account6 = new Account(0, 0, -2, 0);
        assertEquals(account6.calcCheckingDeposit(0), "exception message");
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
