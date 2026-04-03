# Design Decisions Log

## 1) Domain modeling
- Split the project into core banking entities: `Customer`, `Account`, and `Loan`.
- Added `Bank` as the aggregate root for customer and account management.

## 2) Abstraction and contracts
- Created abstract base classes (`Person`, `Account`) to avoid duplication.
- Defined interfaces (`Identifiable`, `Transactional`, `InterestBearing`) for stable contracts.

## 3) Composition
- Introduced `AccountPortfolio` that belongs to `Customer`.
- This models real ownership and keeps account collection logic encapsulated.

## 4) Design pattern usage
- Applied **Strategy Pattern** for monthly interest logic:
  - `InterestStrategy`
  - `FixedRateInterestStrategy`
- Benefit: easy to add future policies (tiered rates, promo rates) without changing account logic.

## 5) Error handling
- Added `BankingException` for domain-level validation failures.

## 6) Equality rules
- `Person` equality: by immutable ID.
- `Account` equality: by account number.

## 7) Extendability
- Project can be extended with transaction history, loan installments, and persistence.
