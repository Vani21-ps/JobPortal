# Job Portal (ORM CLI)

**Job Portal (ORM CLI)** is a **Java-based command-line application** for managing job listings, user accounts, and job applications.  
It demonstrates a clean **DAO-Service architecture** using **Hibernate ORM** and **MySQL**, with support for multiple roles: Candidate, Employer, and Admin.

---

## Features

### User Management
- **Register** as Candidate, Employer, or Admin
- **Login** with secure password hashing

### Job Management
- **Post jobs** (Employer)
- **View jobs** (Candidate / Employer / Admin)

### Application Management
- **Apply** to jobs (Candidate)
- **View applications** for your jobs (Employer)
- **View own applications** (Candidate)

### Admin Panel
- **List all registered users**

### CLI Interface
- Interactive menus for different roles
- Simple, terminal-based input/output

### Database Integration
- **MySQL** backend
- **Hibernate ORM** for object-relational mapping
- Auto schema updates (`hbm2ddl.auto=update`)

### Logging
- Configurable logging using **SLF4J SimpleLogger**

---

## Technologies Used
- **Java 17**
- **Hibernate ORM 6.5.2**
- **MySQL 8**
- **Maven** for dependency management
- **SLF4J SimpleLogger** for logging


---

## How to Run

1. **Clone the repository**
```bash
git clone https://github.com/Vani21-ps/JobPortal.git
cd job-portal-orm-cli
Build the project with Maven

mvn clean package


Run the application

java -jar target/job-portal-orm-cli-1.0.0-jar-with-dependencies.jar


Follow the CLI menus to:

Register / Login

Post or view jobs

Apply for jobs (Candidates)

View applications (Employers)

List all users (Admin)

Configuration

Database connection: persistence.xml

<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/job_portal"/>
<property name="jakarta.persistence.jdbc.user" value="root"/>
<property name="jakarta.persistence.jdbc.password" value="your_password"/>


Logging: Use simplelogger.properties in src/main/resources to suppress unnecessary Hibernate logs.

Future Enhancements

Add password encryption

Support job search/filtering

Add notifications for candidates and employers

Build a web or GUI version

