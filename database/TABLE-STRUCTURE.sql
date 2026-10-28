CREATE DATABASE BankDB
USE BankDB

CREATE TABLE Customer (
    CustomerID INT IDENTITY(100,1) PRIMARY KEY,
    LastName VARCHAR(50) NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    MiddleName VARCHAR(50),
    Sex VARCHAR(10) CHECK (Sex IN ('Male', 'Female')),
    Birthdate DATE
);

CREATE TABLE Account (
    AccountID INT IDENTITY(1,1) PRIMARY KEY,
    CustomerID INT FOREIGN KEY REFERENCES Customer(CustomerID),
    PhoneNumber VARCHAR(20) UNIQUE NOT NULL,
    MPIN INT NOT NULL,
    Balance DECIMAL(18, 2) DEFAULT 0.00,
    Date_Created DATE DEFAULT GETDATE()
);

CREATE TABLE Express_Send (
    Express_SendID INT IDENTITY(8000,1) PRIMARY KEY,
    AccountID INT FOREIGN KEY REFERENCES Account(AccountID),
    SendAmount DECIMAL(18, 2) DEFAULT 0.00,
    ReceiverPhoneNumber VARCHAR(15)
);

CREATE TABLE Bank_Transfer (
    BankTransferID INT IDENTITY(9000,1) PRIMARY KEY,
    AccountID INT FOREIGN KEY REFERENCES Account(AccountID),
    BankName VARCHAR(100) CHECK (BankName IN ('RCBC', 'Metrobank', 'Landbank', 'BPI', 'BDO', 'PSBank')),
    ReceiverAccount VARCHAR(50),
    ReceiverAmount DECIMAL(18, 2) DEFAULT 0.00,
    ReceiverName VARCHAR(100),
    TransferAmount DECIMAL(18, 2) DEFAULT 0.00
);


CREATE TABLE Cash_In (
    CashInID INT IDENTITY(5000,1) PRIMARY KEY,
    AccountID INT FOREIGN KEY REFERENCES Account(AccountID),
    CashInAmount DECIMAL(18, 2) DEFAULT 0.00
);

CREATE TABLE Loan (
    LoanID INT IDENTITY(3000,1) PRIMARY KEY,
    AccountID INT FOREIGN KEY REFERENCES Account(AccountID),
    LoanAmount DECIMAL(18, 2) DEFAULT 0.00,
    LoanBalance DECIMAL(18, 2) DEFAULT 0.00,
    LoanTerms INT,
    ApplyDate DATE DEFAULT GETDATE()
);

CREATE TABLE Account_Transaction (
    TransactionID INT IDENTITY(1,1) PRIMARY KEY,
    AccountID INT FOREIGN KEY REFERENCES Account(AccountID),
    Transact_Type VARCHAR(50) CHECK (Transact_Type IN ('Cash In', 'Express Send', 'Loan', 'Bank Transfer')),
    TransactionAmount DECIMAL(18, 2) DEFAULT 0.00,
    TransactionDate DATE DEFAULT GETDATE()
);

DROP TABLE Account_Transaction;
DROP TABLE Loan;
DROP TABLE Cash_In;
DROP TABLE Bank_Transfer;
DROP TABLE Express_Send;
DROP TABLE Account;
DROP TABLE Customer;