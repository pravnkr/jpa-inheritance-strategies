# JPA and Hibernate Inheritance Strategies

This repository contains a comprehensive guide and code examples for implementing inheritance strategies using JPA and Hibernate. The focus is on modeling complex domain models with an example of Private Equity domain.

## Inheritance Strategies

The repository covers three main inheritance strategies:

1. **Single Table**: All classes in the hierarchy are mapped to a single database table. Discriminator column is used to distinguish between entity types.
   - Branch: `inheritence-strategies/single-table`

2. **Joined**: Each class in the hierarchy is mapped to its own table. Tables are linked using shared primary key values.
   - Branch: `inheritence-strategies/joined`

3. **Table per Class**: Each concrete class in the hierarchy is mapped to its own table, containing all the properties of the class and its superclasses.
   - Branch: `inheritence-strategies/table-per-class`

## Domain Model

The code examples are based on a Private Equity domain model, which includes the following entities:

- `Investor`: Abstract base class representing an investor.
  - `CompanyInvestor`: Subclass representing a company investor.
  - `FundLP`: Subclass representing a fund limited partner.
  - `CoInvestor`: Subclass representing a co-investor.

- `FundStructure`: Represents a fund structure that can have multiple investors.

## Features

- Implementation of Single Table, Joined, and Table per Class inheritance strategies.
- Examples of polymorphic queries using JPA and Hibernate.
- Unit tests with Testcontainers for testing the inheritance mapping and queries.
- Many-to-many relationship between `Investor` and `FundStructure` entities.

## Getting Started

1. Clone the repository.
2. Checkout the branch corresponding to the inheritance strategy you want to explore.
3. Configure the database connection properties in the `application.properties` file.
4. Run the unit tests to see the inheritance strategies in action.

## Dependencies

- Spring Boot
- JPA (Java Persistence API)
- Hibernate
- Testcontainers
- JUnit
- AssertJ

Feel free to explore the code examples, run the tests, and adapt the inheritance strategies to your own domain model. Contributions and feedback are welcome!