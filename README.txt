Job Portal (ORM CLI)

Job Portal (ORM CLI) is a Java-based command-line application for managing job listings, user accounts, and job applications. Built using Hibernate ORM and MySQL, this project demonstrates a clean DAO-Service architecture with multi-role support: Candidate, Employer, and Admin.

Features

User Management

Register as Candidate, Employer, or Admin

Login with secure password hashing

Job Management

Employers can post jobs

Candidates can view available jobs

Application Management

Candidates can apply to jobs

Employers can view applications for their jobs

Candidates can view their applications

Admin Panel

List all registered users

CLI Interface

Interactive menus for different roles

Simple, terminal-based input/output

Database Integration

MySQL backend

Hibernate ORM for object-relational mapping

Auto schema updates (hbm2ddl.auto=update)

Logging

Configurable logging using SLF4J SimpleLogger

Technologies Used

Java 17

Hibernate ORM 6.5.2

MySQL 8

Maven for dependency management

SLF4J for logging

How to Run

Clone the repository:

git clone <repo-url>
cd job-portal-orm-cli


Build the project with Maven:

mvn clean package


Run the application:

java -jar target/job-portal-orm-cli-1.0.0-jar-with-dependencies.jar


Follow the CLI menus to register, login, post jobs, or apply.
