Job Portal - Terminal (ORM) Maven Project
========================================

How to use:
1. Edit `src/main/resources/META-INF/persistence.xml` and set your MySQL user/password and DB URL.
2. Create database schema (optional - Hibernate auto-update will create tables):
   mysql -u root -p < sql/schema.sql
3. Build:
   mvn -DskipTests package
4. Run:
   java -jar target/job-portal-orm-cli-1.0.0-jar-with-dependencies.jar

Features:
- JPA/Hibernate ORM (User, Job, Application)
- Menu-driven terminal UI
- Register, Login, Post job, Apply, List users/jobs
