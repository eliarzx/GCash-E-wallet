# 𝓖𝓒𝓪𝓼𝓱 𝓔-𝓦𝓪𝓵𝓵𝓮𝓽

A GCash-inspired desktop e-wallet system. Users can manage accounts, cash in, send money, transfer funds to banks, apply for loans, and view transaction history through a Java Swing interface. Built with Java, JDBC, and Microsoft SQL Server.

<strong> ⋆୨୧˚  repository  ✧˖°｡ </strong><br>
https://github.com/eliarzx/GCash-E-wallet

## ₊⊹ Description

To design and implement a desktop e-wallet system that demonstrates event-driven programming and database integration. The application brings account registration, money transfers, loan management, and transaction tracking together in a user-friendly interface inspired by GCash.

Developed as a final requirement for **IT-206 – Event-Driven Programming** at **Bulacan State University**, submitted on May 13, 2025. Interface designs were developed using Canva and Figma.

## ₊⊹ System Highlights

✦ Account Registration and MPIN Login<br>
✦ Cash In and Balance Tracking<br>
✦ Express Send to Registered Users<br>
✦ Simulated Bank Transfers<br>
✦ Loan Applications and Balance Tracking<br>
✦ Account Transaction History<br>
✦ Profile and Account Management<br>

## Project structure

```text
src/
  System/                   Java Swing screens and e-wallet features
  AdminSide/                Admin screens and database helpers
  HelperClasses/            Connection helpers, sessions, and UI components
  Resources/                Interface images, logos, and icons
database/
  TABLE-STRUCTURE.sql        SQL Server database and table definitions
README.txt                  Original project documentation and usage guide
```

Some screens include NetBeans `.form` files. Keep them alongside their corresponding Java source files when editing those screens in NetBeans.

## Installation

1. Install **JDK 21**, **NetBeans IDE**, **Microsoft SQL Server Express**, and **SQL Server Management Studio (SSMS)**.
2. Clone this repository:

   ```sh
   git clone https://github.com/eliarzx/GCash-E-wallet.git
   cd GCash-E-wallet
   ```

3. In SSMS, run `CREATE DATABASE BankDB` as a separate batch, then run the rest of `database/TABLE-STRUCTURE.sql`, starting with `USE BankDB`. The script already includes the database creation statement; do not run that statement again if the database exists.
4. Create a Java application project in NetBeans using the existing `src/` sources. Add the **Microsoft JDBC Driver for SQL Server** to the project's libraries and make sure `src/Resources/` is included on the runtime classpath.
5. Update the database connection settings in `src/HelperClasses/Connector.java` and any screens that connect directly through `DriverManager`. Replace `YOUR_DB_USERNAME` and `YOUR_DB_PASSWORD` with your local SQL Server credentials, and adjust the host, port, and database name as needed. Keep real credentials out of commits.
6. Enable SQL Server authentication and TCP/IP. Check that the configured port, typically `1433`, is available to the application.
7. Run `src/System/LandingPage.java` from NetBeans. Register an account, log in with your phone number and MPIN, and explore the available services.

See [README.txt](README.txt) for the original setup instructions, database details, and usage guide.

## ₊⊹ Contribution Guidelines

Contributions are welcome! To contribute:<br>
✦ Fork the repository<br>
✦ Create a new branch (`git checkout -b feature/YourFeature`)<br>
✦ Make your changes and commit (`git commit -m 'Add some feature'`)<br>
✦ Push to the branch (`git push origin feature/YourFeature`)<br>
✦ Open a pull request for review<br>

## ₊⊹ Reminder

Hello ˙ᵕ˙ This is one of my final requirements in Univ and is published to personally showcase my work.
Please do not re-upload or copy this code for educational assignments without my knowledge (·•᷄ࡇ•᷅ ). Thank you!

This is an educational project inspired by GCash and is not affiliated with or endorsed by GCash. Bank transfers are simulated and do not connect to real bank APIs.

## Development note

The application uses Java Swing and JDBC, with some database calls written directly in individual screens. The main e-wallet connection targets Microsoft SQL Server; the admin database helpers currently reference MySQL and require separate configuration or adaptation before use. MPINs are stored as integers in the current schema. Further work could centralize database configuration, improve authentication and credential handling, and migrate the interface to JavaFX.
