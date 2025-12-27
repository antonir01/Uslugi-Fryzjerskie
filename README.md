# Salon Management System

A simple console-based Java application to manage a hair salon. It supports **customer reservations**, **employee management**, and **stock management**.

---

## Features

### Customer
- Sign up and log in.
- Make a reservation for services:
  - Cutting
  - Styling
  - Coloring
  - Washing and Conditioning
- View their own reservations.
- Choose a payment method: CASH, PAYPAL, or TRANSFER.

### Employee
- Log in with a PIN.
- View all reservations.
- Manage stock:
  - View current stock items.
  - Add new stock items (type and price).

### Admin
- Create employee accounts.
- Access all employee functionalities.

---

## Default Accounts

The application starts with **two pre-created accounts** for testing and management.

### Admin Account
- **Full Name:** `root`
- **Phone Number:** `111 111 111`
- **PIN:** `1111`
- **Position:** `admin`

Use this account to create additional employee accounts.

---

### Employee Account
- **Full Name:** `employee`
- **Phone Number:** `222 222 222`
- **PIN:** `2222`
- **Position:** `employee`

This account can:
- View all reservations
- Manage stock
- Log out

---

## Installation

1. Clone the repository or download the source files.
2. Compile all Java files:

```bash
javac *.java
