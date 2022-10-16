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

# #6 testGetCheckingWithdrawInput #
  
  *Goal: to test if the getCheckingWithdrawInput is calculated correctly*

  **a) Identify testable function**
      
      getCheckingWithdrawInput()

  **b) Identify parameters, return types, return values, and exceptional behavior**
      
      Parameters: -
      Hidden parameters: 
            1. checkingBalance
            2. amount
      Return type: void
      Return value: -
      Exceptional behavior: Input incorrect value according to conditions (Input can’t be a negative number, alphabet, or amount of withdrawal exceeds balance.)

  **c) Model the input domain**
      
      C1:digit of ‘amount’ (interface-based)
          b1: one digit
          b2: more than one digit

      C2: Input Validation (functionality-based)
          b1: Correct input (amount >= 0 & balance >= amount)
          b2: Negative number with re-input correct input
	      b3: Invalid input (Not Number) with re-input correct input
	      b4 : Throw NoSuchElementException

  **d) Combine partitions to define test requirements**
      
      PWC: 4 * 2  = 8
      (C1b1, C2b1), (C1b1, C2b2), (C1b1, C2b3), (C1b1, C2b4), 
	  (C1b2, C2b1), (C1b2, C2b2), (C1b2, C2b3), (C1b2, C2b4)


  **e) Derive test values and expected values.**
      
      ‘\n’ means the end of one input.
      Assume checkingBalance =  100
      1) “1” -> Expected values: checkingBalance =  99
      2) “-1\n1” ->  Expected values: checkingBalance = 99, Notify message (Balance Cannot be Negative.)
      3) “a\n1” ->  Expected values: checkingBalance = 99, Notify message (Invalid Choice.)
      4) “-1” -> Expected values: checkingBalance = 100 Throw NoSuchElementException        5) “10” ->  Expected values: checkingBalance = 90
      6) “-10\n10” ->  Expected values: checkingBalance = 90, Notify message (Balance Cannot be Negative.)
      7) “a\n10” ->  Expected values: checkingBalance =90, Notify message (Invalid Choice.)
      8) “-10” ->  Expected values: checkingBalance = 100 Throw NoSuchElementException


---

# #7 testGetSavingWithdrawInput #
  
  *Goal: to test if the getSavingWithdrawInput is calculated correctly*

  **a) Identify testable function**
      
      getSavingWithdrawInput()

  **b) Identify parameters, return types, return values, and exceptional behavior**
      
      Parameters: -
      Hidden parameters: 
            1. checkingBalance
            2. amount
      Return type: void
      Return value: -
      Exceptional behavior: Input incorrect value according to conditions (Input can’t be a negative number, alphabet, or amount of withdrawal exceeds balance.)

  **c) Model the input domain**
      
      C1:digit of ‘amount’ (interface-based)
          b1: one digit
          b2: more than one digit

      C2: Input Validation (functionality-based)
          b1: Correct input (amount >= 0 & balance >= amount)
          b2: Negative number with re-input correct input
	      b3: Invalid input (Not Number) with re-input correct input
	      b4 : Throw NoSuchElementException

  **d) Combine partitions to define test requirements**
      
      BCC (C1b2, C2b1) =  1 + (1 + 3) = 1 + 4
      Base Choice = (C1b2, C2b1)
	  (C1b1, C2b1),
	  (C1b2, C2b2), (C1b2, C2b3), (C1b2, C2b4)

  **e) Derive test values and expected values.**
      
      ‘\n’ means the end of one input.
      Assume checkingBalance =  100
      1) “1” -> Expected values: checkingBalance =  99
      2) “10” ->  Expected values: checkingBalance = 90
      3) “-10\n10” ->  Expected values: checkingBalance = 90, Notify message (Balance Cannot be Negative.)
      4) “a\10” ->  Expected values: checkingBalance =90, Notify message (Invalid Choice.)
      5) “-10” ->  Expected values: checkingBalance = 100 Throw NoSuchElementException


---

# #8 testGetCheckingDepositInput #
  
  *Goal: to test if the getCheckingDepositInput is calculated correctly*

  **a) Identify testable function**
      
      getCheckingDepositInput()

  **b) Identify parameters, return types, return values, and exceptional behavior**
      
      Parameters: -
      Hidden parameters: 
            1. checkingBalance
            2. amount
      Return type: void
      Return value: -
      Exceptional behavior: Input incorrect value according to conditions (Input can’t be a negative number or alphabet)

  **c) Model the input domain**
      
      C1:digit of ‘amount’ (interface-based)
          b1: one digit
          b2: more than one digit

      C2: Input Validation (functionality-based)
          b1: Correct input (amount >= 0)
          b2: Negative number with re-input correct input
	      b3: Invalid input (Not Number) with re-input correct input
	      b4 : Throw NoSuchElementException

  **d) Combine partitions to define test requirements**
      
      BCC (C1b2, C2b1) =  1 + (1 + 3) = 1 + 4
      Base Choice = (C1b2, C2b1)
	  (C1b1, C2b1),
	  (C1b2, C2b2), (C1b2, C2b3), (C1b2, C2b4)

  **e) Derive test values and expected values.**
      
      ‘\n’ means the end of one input.
      Assume checkingBalance =  100
      1) “1” -> Expected values: checkingBalance =  101
      2) “10” ->  Expected values: checkingBalance = 110
      3) “-10\n10” ->  Expected values: checkingBalance = 110, Notify message (Balance Cannot be Negative.)
      4) “a\n10” ->  Expected values: checkingBalance = 110, Notify message (Invalid Choice.)
      5) “-10” ->  Expected values: checkingBalance = 100 Throw NoSuchElementException


---

# #9 testGetSavingDepositInput #
  
  *Goal: to test if money is deposit to the account correctly*

  **a) Identify testable function**
      
      getSavingDepositInput()

  **b) Identify parameters, return types, return values, and exceptional behavior**
      
      Parameters: amount
      Hidden parameters: 
            1. checkingBalance
            2. amount
      Return type: void
      Return value: -
      Return value: Input incorrect value according to conditions (Input can’t be a negative number or alphabet)

  **c) Model the input domain**
      
      C1:digit of ‘amount’ (interface-based)
          b1: one digit
          b2: more than one digit

      C2: Input Validation (functionality-based)
          b1: Correct input (amount >= 0)
          b2: Negative number with re-input correct input
	      b3: Invalid input (Not Number) with re-input correct input
	      b4 : Throw NoSuchElementException

  **d) Combine partitions to define test requirements**
      
      MBCC: 2 + 2 * (2 - 1) + 2 * (4 - 2) = 2 + 6
      Base choices = [(C1b1, C2b1), (C1b1, C2b2)]
      (C1b2, C2b1), (C1b2, C2b2), 
      (C1b1, C2b3), (C1b1, C2b4), #1
      (C1b1, C2b3), (C1b1, C2b4), (duplicate with #1) 


  **e) Derive test values and expected values.**
      
      Assume checkingBalance = 100
      1) "1" -> Expected values: checkingBalance = 101
      2) "-1\n1" ->  Expected values: checkingBalance = 101, Notify message (Balance Cannot be Negative.) 
      3) "10" ->  Expected values: checkingBalance = 110
      4) "-10\n10" ->  Expected Value: checkingBalance = 110, Notify message (Balance Cannot be Negative.)
      5) "a\n1" ->   Expected values: checkingBalance = 101, Notify message (Invalid Choice.)
      6) "a" ->   Expected values: checkingBalance = 100, Throw NoSuchElementException 
      7) "b\n2" ->   Expected values: checkingBalance = 102, Notify message (Invalid Choice.)
      8) "b" ->   Expected values: checkingBalance = 100, Throw NoSuchElementException 


---

# #10 testGetTransferInput #
  
  *Goal: to test if the money is transfer between checking and saving account correctly*

  **a) Identify testable function**
      
      getTransferInput()

  **b) Identify parameters, return types, return values, and exceptional behavior**
      
      Parameters: accType: String
      Hidden parameters: 
            1. choice
            2. amount
      Return type: void
      Return value: -
      Exceptional behavior: Input incorrect value according to conditions (In first input (choice): inputting anything than 1 and 2, second input (amount): inputting negative number or alphabet)

  **c) Model the input domain**
      
      C1: Is accType null? (interface-based)
          b1: accType = null
          b2: accType != null
    
      C2: accType value (functionality-based)
          b1: accType = Checkings
          b2: accType = Saving

      C3: Input Validation choice(functionality-based)
          b1: Correct input (1, 2)
          b2: Wrong input with re-input correct input
          b3: Throw NoSuchElementException

      C4: Input Validation amount (functionality-based)
          b1: Correct input (amount >= 0 & balance >= amount)
          b2: Negative number with re-input correct input
          b3: Invalid input (Not Number) with re-input correct input
          b4: Throw NoSuchElementException


  **d) Combine partitions to define test requirements**
      
      MBCC: 2 + 2 * (2 - 2) + 2 * (2 - 2) + 2 * (3 - 2) + 2 * (4 - 2) = 2 + 6
      [Correct] [Invalid]
      Base choice = [(C1b1, C2b1, C3b1, C4b1), (C1b2, C2b2, C3b2, C4b2)]
      (C1b1, C2b1, C3b3, C4b1), (C1b2, C2b2, C3b3, C4b2),
      (C1b1, C2b1, C3b1, C4b3), (C1b1, C2b1, C3b1, C4b4),
      (C1b2, C2b2, C3b2, C4b3), (C1b2, C2b2, C3b2, C4b4),



  **e) Derive test values and expected values.**
      
      Assume checkingBalance = 100
      Assume savingBalance = 100
      1) accType = null, "1", "1" -> Expected values: Throw Error NullPointerException, checkingBalance = 100, savingBalance = 100
      2) accType = “Savings”, "3\n1", "-1\n1" ->  Expected values: checkingBalance = 101, savingBalance = 99
      3) accType = null, "-3", "1" -> Expected values: Throw Error NullPointerException, checkingBalance = 100, savingBalance = 100
      4) accType = "Savings", "3", "-1\n1" ->  Expected values: Throw Error NoSuchElementException, checkingBalance = 100, savingBalance = 100
      5) accType = null, "1", "a\n1" ->  Expected values: Throw Error NullPointerException, checkingBalance = 100, savingBalance = 100
      6) accType = null, "1", "-3" ->  Expected values: Throw Error NullPointerException, checkingBalance = 100, savingBalance = 100
      7) accType = "Savings", "3\n1", "a\n1\n1" -> Expected values: Notify user: Invalid Choice, Notify user: Invalid Choice, checkingBalance = 101, savingBalance = 99
      8) accType = "Savings", "3\n2", "-1" ->  Expected values: End program with no balance update, checkingBalance = 100, savingBalance = 100


---
