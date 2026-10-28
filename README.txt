PROJECT TITLE: Java Swing Utilization with Database Integration for an E-Wallet System: GCash


PROJECT DESCRIPTION

The Digital E-Wallet System is a desktop application that mirrors the functionality of platforms like GCash.
It enables users to manage finances through an intuitive GUI, offering features such as registration, money
transfers, loans, and transaction tracking. The platform was built using Java Swing for the desktop interface,
pairing with Microsoft SQL Server Express for handling backend data storage. The system allows users to
create accounts, cash in, send cash, take loans and see transaction history. UI and UX designs were developed
using Canva and Figma to ensure proper and standard user experience. 



FEATURES & FUNCTIONALITIES

- Sign Up System : Register users with name, birthdate, sex, phone number, and MPIN.
- Login System : Secure login with phone number and MPIN.
- Cash In System : Add funds to user accounts.
- Bank Transfer System : Transfer money to banks (e.g., BPI, BDO, PSBank).
- Express Send System : Send money to other registered app users.
- Loan System : Apply for and manage personal loans.
- Account Transaction Tracking : View full transaction history for transparency.


TECHNOLOGIES USED

- Java (JDK 21)
- Java Swing :  For GUI development
- Microsoft SQL Server Express : Database management
- JDBC : Java Database Connectivity
- NetBeans IDE : Development environment
- Canva/Figma : Interface prototyping and UI design


INSTALLATION & SETUP INSTRUCTIONS

Follow the steps below to install and run the Digital E-Wallet System:
1. Install JDK 17 or higher
	• 🔗 Download JDK 17

2. Install Microsoft SQL Server Express and SQL Server Management Studio (SSMS)
	• 🔗 Download SQL Server Express
	• 🔗 Download SQL Server Management Studio (SSMS)

3. Set Up the Database
	• Locate the database folder inside the Grp3 - E-WalletSystem project directory.
	• Open database.sql in SSMS and execute it to create and populate the necessary tables.

4. Open the Project in NetBeans IDE
	• Recommended: NetBeans 19 or higher
	• Use File > Open Project and select the root folder of Grp3 - E-WalletSystem.

5. Configure JDBC Connection
	• Open the file Connection.java (typically under src/connection/ or similar path).
	• Modify the following values:
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Bank”DB;
		String user = "your_sql_username";
		String password = "your_sql_password";

	• Ensure SQL Server authentication is enabled.

6. Enable TCP/IP and Configure Firewall
	• Open SQL Server Configuration Manager:
	• Enable TCP/IP under SQL Server Network Configuration.
	• Set TCP Port (usually 1433).
	• Open Windows Firewall:
	• Allow inbound rule for TCP port 1433.

7. (Optional) Install Custom Font
	• Locate the font folder inside src/font/.
	• Install the .ttf font file on your system.
	• Restart NetBeans IDE to apply the font or ‘Clean and Rebuild’ the project.

8. Run the System
	• Open LandingPage.java.
	• Right-click and choose Run File or press Shift + F6 in NetBeans.


DATABASE CONFIGURATION

Database Name: `BankDB`

Main Tables:
The database schema defines the structure of our application’s database. This  outlines  the tables, columns, data types,
and relationships used to manage various tables utilized in our e-wallet system, as shown below:

CUSTOMER
Column name		Data Type			Description
CustomerID		INT				Unique identifier for customer (auto-increment starting at 100)
LastName			VARCHAR(50)		Customer's last name
FirstName			VARCHAR(50)		Customer's first name
MiddleName		VARCHAR(50)		Customer's middle name (optional)
Sex				VARCHAR(10)		Customer's gender (Male or Female)
Birthdate			DATE				Customer's date of birth

ACCOUNT 
Column Name		Data Type			Description
AccountID			INT				Unique identifier for account (auto-increment)
CustomerID		INT				Links to the Customer table
PhoneNumber		VARCHAR(20)		Unique phone number of customer
MPIN	INT			4-digit Mobile 		PIN for authentication
Balance			DECIMAL(18,2)		Current balance of the account
Date_Created		DATE				Account creation date

EXPRESS_SEND 
Column Name			Data Type			Description
Express_SendID			INT				Unique ID for each express send transaction (auto-increment starting at 8000)
AccountID				INT				Sender’s account ID
SendAmount			DECIMAL(18,2)		Amount sent
ReceiverPhoneNumber	VARCHAR(15)		Recipient’s phone number

BANK_TRANSFER 
Column Name		Data Type			Description
BankTransferID		INT				Unique ID for bank transfer (auto-increment starting at 9000)
AccountID			INT				Account initiating the transfer
BankName			VARCHAR(100)		Name of the recipient’s bank
ReceiverAccount	VARCHAR(50)		Recipient's bank account number
ReceiverAmount		DECIMAL(18,2)		Amount received by the recipient
ReceiverName		VARCHAR(100)		Name of the bank account recipient
TransferAmount		DECIMAL(18,2)		Total transfer amount

CASH_IN
Column Name		Data Type			Description
CashInID			INT				Unique ID for cash-in record (auto-increment starting at 5000)
AccountID			INT				Account receiving the cash-in
CashInAmount		DECIMAL(18,2)		Amount of money added to account

LOAN 
Column Name		Data Type			Description
LoanID			INT				Unique identifier for loan (auto-increment starting at 3000)
AccountID			INT				Account that requested the loan
LoanAmount		DECIMAL(18,2)		Total loan amount requested
LoanBalance		DECIMAL(18,2)		Remaining loan balance
LoanTerms			INT				Duration of the loan (e.g. in months)
ApplyDate			DATE				Date the loan was applied for

ACCOUNT_TRANSACTION 
Column Name		Data Type			Description
TransactionID		INT				Unique ID for each transaction (auto-increment)
AccountID			INT				Account involved in the transaction
Transact_Type		VARCHAR(50)		Type of transaction (e.g. Cash In, Loan)
TransactionAmount	DECIMAL(18,2)		Amount involved in the transaction
TransactionDate		DATE				Date the transaction occurred
 

HOW TO IMPORT THE DATABASE

1. Open SQL Server Management Studio.
2. Create a database named `BankDB`.
3. Execute the provided SQL script to create tables and constraints.
4. Adjust connection settings in the Java application accordingly.


USAGE GUIDE

1. Launch the app and in the landing page, click on any area and wait for registration
2. Open an account via the Sign Up screen.
3. Log in using your phone number and MPIN.
3. Navigate through services:
   - Use Cash In to add funds.
   - Use Express Send or Bank Transfer to send money.
   - Apply for loans and track balances in Loan.
   - View all transaction history in Account Transaction.
4. To close the account, click the ‘Profile’ in the navigation bar and close the account.


KNOWN ISSUES & FUTURE IMPROVEMENTS

Known Issues
- Bank transfer doesn't link directly to actual bank APIs (currently simulated).
- No support yet for different account types.

Future Enhancements
- Support for business accounts.
- Bank account linking for real-world transactions.
- Upgrade from Swing to JavaFX for modernized UI.


ACKNOWLEDGEMENTS & REFERENCES

- [GCash Website] (https://new.gcash.com/)
- [GCash App on Google Play] (https://play.google.com/store/apps/details?id=com.globe.gcash.android)
- [GCash App on Apple Store] (https://apps.apple.com/ph/app/gcash/id520020791)
- [GCash - Wikipedia] (https://en.wikipedia.org/wiki/GCash)

—————————————————————————————

📅 Date Submitted: May 13, 2025
🎓 Course: IT-206 – Event-Driven Programming
🏫 Bulacan State University