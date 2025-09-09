Job Portal 

Job Portal is a Java-based command-line application that manages job listings, users, and applications.
It supports multiple roles: Candidate, Employer, and Admin.

Features
User registration and login
Employers can post jobs and view applications
Candidates can view jobs and apply
Admin can list all users
Interactive CLI menus

Technologies Used
Java 17
Hibernate ORM 6.5.2
MySQL 8
Maven for dependency management
SLF4J SimpleLogger for logging

Architecture
DAO Layer – Database operations for Users, Jobs, and Applications
Service Layer – Business logic and validations
Model Layer – Entity classes: User, Job, Application
