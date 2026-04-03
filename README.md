# Banking System (Java OOP Project)

This is a simple Java banking system project based on the project brief in your image:
- Core idea: **accounts, transfers, loans, and interest**
- Uses OOP concepts and is structured in packages

## Features
- Customer registration
- Savings and checking accounts
- Deposit / withdraw / transfer operations
- Monthly interest application
- Loan total repayment calculation

## OOP & Requirement Mapping
- **Abstract classes (2):** `Person`, `Account`
- **Interfaces (3):** `Identifiable`, `Transactional`, `InterestBearing`
- **Composition:** `Customer` has `AccountPortfolio`
- **Inheritance:** `SavingsAccount`/`CheckingAccount` extend `Account`; `Customer` extends `Person`
- **equals/hashCode:** implemented in `Person` and `Account`
- **Design pattern:** Strategy pattern for interest calculation (`InterestStrategy`)
- **Packages:** clear separation under `com.bank.*`
- **Meaningful names:** account/customer/loan/service naming

## Run
From repository root:

```bash
javac -d out $(find src -name "*.java")
java -cp out com.bank.app.Main
```

## Project Structure
```
src/com/bank/
  app/Main.java
  core/Bank.java
  exception/BankingException.java
  interfaces/{Identifiable,Transactional,InterestBearing}.java
  model/{Person,Customer,AccountPortfolio,Account,SavingsAccount,CheckingAccount,Loan}.java
  service/TransferService.java
  strategy/{InterestStrategy,FixedRateInterestStrategy}.java
```
