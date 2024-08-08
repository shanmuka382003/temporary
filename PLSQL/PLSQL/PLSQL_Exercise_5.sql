-- Enable server output
SET SERVEROUTPUT ON;

-- Create tables
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance INT,
    LastModified DATE,
    IsVIP CHAR(1)
);

CREATE TABLE Accounts (
    AccountID INT PRIMARY KEY,
    CustomerID INT,
    AccountType VARCHAR2(20),
    Balance INT,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
    TransactionID INT PRIMARY KEY,
    AccountID INT,
    TransactionDate DATE,
    Amount INT,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE TABLE Loans (
    LoanID INT PRIMARY KEY,
    CustomerID INT,
    LoanAmount INT,
    InterestRate INT,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary INT,
    Department VARCHAR2(50),
    DepartmentID INT,
    HireDate DATE
);

-- Create ErrorLogs table for logging errors
CREATE TABLE ErrorLogs (
    ErrorID INT PRIMARY KEY,
    ErrorMessage VARCHAR2(255),
    ErrorDate DATE
);

-- Create AuditLog table for maintaining an audit log
CREATE TABLE AuditLog (
    AuditID INT PRIMARY KEY,
    TransactionID INT,
    TransactionDate DATE,
    AccountID INT,
    Amount INT,
    TransactionType VARCHAR2(10)
);

-- Create sequence for AuditLog primary key
CREATE SEQUENCE AuditLog_Seq
START WITH 1
INCREMENT BY 1
NOCACHE;

-- Scripts for Sample Data Insertion

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (1, 'John Doe', TO_DATE('1963-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (1, 1, 'Savings', 1000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (2, 2, 'Checking', 1500, SYSDATE);

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (1, 1, SYSDATE, 200, 'Deposit');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (2, 2, SYSDATE, 300, 'Withdrawal');

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (2, 2, 10000, 5, SYSDATE, SYSDATE + 25);

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));

-- Triggers

-- Trigger to Automatically Update LastModified Date When a Customer's Record is Updated
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    -- Update the LastModified column to the current date
    :NEW.LastModified := SYSDATE;
END;
/
SHOW ERRORS TRIGGER UpdateCustomerLastModified;

-- Trigger to Maintain an Audit Log for All Transactions
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    -- Insert transaction details into the AuditLog table
    INSERT INTO AuditLog (AuditID, TransactionID, TransactionDate, AccountID, Amount, TransactionType)
    VALUES (AuditLog_Seq.NEXTVAL, :NEW.TransactionID, :NEW.TransactionDate, :NEW.AccountID, :NEW.Amount, :NEW.TransactionType);
END;
/
SHOW ERRORS TRIGGER LogTransaction;

-- Trigger to Enforce Business Rules on Deposits and Withdrawals
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    currentBalance DECIMAL(15,2);
BEGIN
    -- Check if the transaction type is withdrawal and ensure it does not exceed the balance
    IF :NEW.TransactionType = 'WITHDRAWAL' THEN
        -- Get the current balance of the account
        SELECT Balance INTO currentBalance
        FROM Accounts
        WHERE AccountID = :NEW.AccountID
        FOR UPDATE;
        
        -- Ensure the withdrawal does not exceed the balance
        IF :NEW.Amount > currentBalance THEN
            RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds for withdrawal.');
        END IF;
    END IF;
    
    -- Check if the transaction type is deposit and ensure the amount is positive
    IF :NEW.TransactionType = 'DEPOSIT' THEN
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.');
        END IF;
    END IF;
END;
/
SHOW ERRORS TRIGGER CheckTransactionRules;

-- Anonymous block to test the triggers
BEGIN
    -- Update a customer record to test UpdateCustomerLastModified trigger
    UPDATE Customers SET Name = 'John Doe Updated' WHERE CustomerID = 1;
    
    -- Insert a transaction to test LogTransaction and CheckTransactionRules triggers
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (3, 1, SYSDATE, 500, 'DEPOSIT');
    
    -- Insert a withdrawal transaction to test CheckTransactionRules trigger
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (4, 1, SYSDATE, 200, 'WITHDRAWAL');
    
    -- Test insufficient funds scenario
    BEGIN
        INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
        VALUES (5, 1, SYSDATE, 2000, 'WITHDRAWAL');
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('before insufficient funds');
            DBMS_OUTPUT.PUT_LINE(SQLERRM);
            DBMS_OUTPUT.PUT_LINE('after insufficient funds');
    END;
    
    -- Test negative deposit scenario
    BEGIN
        INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
        VALUES (6, 1, SYSDATE, -500, 'DEPOSIT');
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('before negative deposit');
            DBMS_OUTPUT.PUT_LINE(SQLERRM);
            DBMS_OUTPUT.PUT_LINE('after negative deposit');
    END;
END;
/
