# #1 testAccountConstructor #
  *Goal: to test if Account object is created correctly*

  **a) Identify testable function**
      
      Account(int customerNumber, int pinNumber)

  **b) Identify parameters, return types, return values, and exceptional behavior**
      
      Parameters: customerNumber, pinNumber
      Return type: Account
      Return value: Account object that is set 2 class attributes (as params)
      Exceptional behavior: Value of parameter should not be negative.

  **c) Model the input domain**
      
      C1: relation of 'customerNumber' to 0 (interface-based)
          b1: less than 0
          b2: equal to 0
          b3: more than 0
      C2: relation between 'customerNumber' and 'pinNumber' (functionality-based)
          b1: 'customerNumber' and 'pinNumber' are the same.
          b2: 'customerNumber' and 'pinNumber' are different.

  **d) Combine partitions to define test requirements**
      
      ACoC: 3*2 = 6
      (C1b1, C2b1), (C1b2, C2b1), (C1b3, C2b1),
      (C1b1, C2b2), (C1b2, C2b2), (C1b3, C2b2)

  **e) Derive test values and expected values.**
      
      1) (-1, -1) -> Expected values: Throw Error (IllegalArgumentException)
      2) (0, 0) ->  Expected values: Account Object with customerNumber = 0, pinNumber = 0
      3) (1, 1) ->  Expected values: Account Object with customerNumber = 1, pinNumber = 1
      4) (-1, 0) ->  Expected values: Throw Error (IllegalArgumentException)
      5) (0, 1) ->   Expected values: Account Object with customerNumber = 0, pinNumber = 1
      6) (1, -1) ->   Expected values: Throw Error (IllegalArgumentException)

---

# #2 testAccountWithBalanceConstructor #
  *Goal: to test if Account object is create correctly*

  **a) Identify testable function**
      
      Account(int customerNumber, int pinNumber, double checkingBalance, double savingsBalance)

  **b) Identify parameters, return types, return values, and exceptional behavior**
      
      Parameters: customerNumber, pinNumber, checkingBalance, savingsBalance
      Return type: Account
      Return value: Account object that is set 4 class attributes (as params)
      Exceptional behavior: Value of parameter should not be negative.

  **c) Model the input domain**
      
      C1: relation of 'checkingBalance' to 0 (interface-based)
          b1: less than 0
          b2: equal to 0
          b3: more than 0
      C3: relation between 'checkingBalance' and 'savingBalance' (functionality-based)
          b1: 'checkingBalance' and 'savingBalance' are the same.
          b2: 'checkingBalance' and 'savingBalance' are different.

  **d) Combine partitions to define test requirements**
      
      ACoC: 3*2 = 6
      (C1b1, C2b1), (C1b2, C2b1), (C1b3, C2b1),
      (C1b1, C2b2), (C1b2, C2b2), (C1b3, C2b2)

  **e) Derive test values and expected values.**
  
      1) (0, 0, -1, -1) ->  Expected values: Throw Error (IllegalArgumentException)
      2) (0, 0, 0, 0) ->  Expected values: Account Object with customerNumber = 0, pinNumber = 0, checkingBalance = 0, savingBalance = 0
      3) (0, 0, 1, 1) ->  Expected values: Account Object with customerNumber = 0, pinNumber = 0, checkingBalance = 1, savingBalance = 1
      4) (0, 0, -1, 0) ->  Expected values: Throw Error (IllegalArgumentException)
      5) (0, 0, 0, 1) ->  Expected values: Account Object with customerNumber = 0, pinNumber = 0, checkingBalance = 0, savingBalance = 1
      6) (0, 0, 1, -1) ->  Expected values: Throw Error (IllegalArgumentException)

---

# #3 testCalcCheckingWithdraw #
  *Goal: to test if the checking withdraw is calculated correctly*

  **a) Identify testable function**
      
      calcCheckingWithdraw(double amount)

  **b) Identify parameters, return types, return values, and exceptional behavior**
      
      Parameters: amount
      Return type: double
      Return value: the amount of checking balance after withdraw
      Exceptional behavior: Value of parameter should not be negative.

  **c) Model the input domain**
      
      C1: relation of 'amount' to 0 (interface-based)
          b1: less than 0
          b2: equal to 0
          b3: more than 0
      C2: relation between 'checkingBalance' and 'amount' (functionality-based)
          b1: 'amount' is less than 'checkingBalance'
          b2: 'amount' is more than 'checkingBalance'

  **d) Combine partitions to define test requirements**
      
      ECC: 3 (from C1)
      (C1b1, C2b1), (C1b2, C2b1), (C1b3, C2b1)

  **e) Derive test values and expected values.**
      
      Assume checkingBalance = 1
      1) (-1) -> Expected values: Throw Error (IllegalArgumentException)
      2) (0) ->  Expected values: 1
      3) (1) ->  Expected values: 0

---

# #4 testCalcCheckingDeposit #
  
  *Goal: to test if the checking deposit is calculated correctly*

  **a) Identify testable function**
      
      calcCheckingDeposit(double amount)

  **b) Identify parameters, return types, return values, and exceptional behavior**
      
      Parameters: amount
      Return type: double
      Return value: the amount of checking balance after deposit
      Exceptional behavior: Value of parameter should not be negative.

  **c) Model the input domain**
      
      C1: relation of 'amount' to 0 (interface-based)
          b1: less than 0
          b2: equal to 0
          b3: more than 0
      C2: relation between 'checkingBalance' and 'amount' (functionality-based)
          b1: 'amount' is more than 'checkingBalance'
          b2: 'checkingBalance 'after calculation is less than 0

  **d) Combine partitions to define test requirements**
      
      ECC: 3 (from C1)
      (C1b1, C2b1), (C1b2, C2b1), (C1b3, C2b1)

  **e) Derive test values and expected values.**
      
      Assume checkingBalance = 1
      1) (-1) -> Expected values: Throw Error (IllegalArgumentException)
      2) (0) ->  Expected values: 1
      3) (1) ->  Expected values: 2

---

# #5 testCalcCheckTransfer #
  
  *Goal: to test if the checking transfers is calculated correctly*

  **a) Identify testable function**
      
      calcCheckTransfer(double amount)

  **b) Identify parameters, return types, return values, and exceptional behavior**
      
      Parameters: amount
      Return type: double
      Return value: value of checkingBalance and savingBalance after transfer
      Exceptional behavior: Value of parameter should not be negative.

  **c) Model the input domain**
      
      C1: relation of 'amount' to 0 (interface-based)
          b1: less than 0
          b2: equal to 0
          b3: more than 0
      C2: relation between 'checkingBalance' and 'amount' (functionality-based)
          b1: 'amount' is less than 'checkingBalance'
          b2: 'amount' is more than 'checkingBalance'

  **d) Combine partitions to define test requirements**
      
      PWC: 3*2 = 6
      (C1b1, C2b1), (C1b2, C2b1), (C1b3, C2b1),
      (C1b1, C2b2), (C1b2, C2b2), (C1b3, C2b3)

  **e) Derive test values and expected values.**
      
      Assume checkingBalance = 3, savingsBalance = 0
      1) (-1) -> Expected values: Throw Error (IllegalArgumentException)
      2) (0) ->  Expected values: checkingBalance = 3, savingBalance = 0
      3) (1) ->  Expected values: checkingBalance = 2, savingBalance = 1
      Assume checkingBalance = -2
      4) (-1) -> Expected values: Throw Error (IllegalArgumentException)
      5) (0) ->  Expected values: Throw Error (IllegalArgumentException)
      6) (2) ->  Expected values: Throw Error (IllegalArgumentException)

---
