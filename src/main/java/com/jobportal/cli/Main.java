package com.jobportal.cli;

import com.jobportal.cli.dao.UserDao;
import com.jobportal.cli.dao.JobDao;
import com.jobportal.cli.dao.ApplicationDao;
import com.jobportal.cli.model.*;
import com.jobportal.cli.service.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.LogManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.jobportal.cli.config.AppConfig;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        LogManager.getLogManager().reset();

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        UserDao userDao = ctx.getBean(UserDao.class);
        JobDao jobDao = ctx.getBean(JobDao.class);
        ApplicationDao appDao = ctx.getBean(ApplicationDao.class);

        AuthService auth = ctx.getBean(AuthService.class);
        UserService userService = ctx.getBean(UserService.class);
        JobService jobService = ctx.getBean(JobService.class);
        ApplicationService appService = ctx.getBean(ApplicationService.class);
        AdminService adminService = ctx.getBean(AdminService.class);

        System.out.println("=== Job Portal ===");
        loop:
        while (true) {
            System.out.println("\n1) Register  2) Login  3) List Jobs  0) Exit");
            System.out.print("Choose: ");
            int ch = parseInt();
            try {
                switch (ch) {
                    case 1 -> register(auth);
                    case 2 -> {
                        User u = login(auth);
                        if (u.getRole() == Role.ADMIN)
                            adminMenu(u, userService, jobService, appService, adminService);
                        else if (u.getRole() == Role.EMPLOYER)
                            employerMenu(u, jobService, appService, userService);
                        else
                            candidateMenu(u, jobService, appService, userService);
                    }
                    case 3 -> listJobs(jobService);
                    case 0 -> { 
                        System.out.println("Bye"); 
                        break loop; 
                    }
                    default -> System.out.println("Invalid");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void register(AuthService auth) {
        System.out.print("Name: "); String name = in.nextLine();
        System.out.print("Email: "); String email = in.nextLine();
        System.out.print("Password: "); String pass = in.nextLine();
        System.out.print("Role (1=CANDIDATE,2=EMPLOYER,3=ADMIN): ");
        int r = parseInt();
        Role role = (r==2)?Role.EMPLOYER:(r==3?Role.ADMIN:Role.CANDIDATE);
        User u = auth.register(name, email, pass, role);
        System.out.println("Registered: " + u);
    }

    private static User login(AuthService auth) {
        System.out.print("Email: "); String e = in.nextLine();
        System.out.print("Password: "); String p = in.nextLine();
        User u = auth.login(e, p);
        System.out.println("Welcome " + u.getName() + " (" + u.getRole() + ")");
        return u;
    }


    private static void candidateMenu(User u, JobService jobService, ApplicationService appService, UserService userService) {
        while (true) {
            System.out.println("\n[CANDIDATE] 1) List Jobs 2) Apply Job 3) My Applications 4) Update Profile 0) Logout");
            int c = parseInt();
            if (c == 0) return;
            switch (c) {
                case 1 -> listJobs(jobService);
                case 2 -> {
                    System.out.print("Job ID to apply: "); 
                    int jid = parseInt();

                   
                    Optional<Application> existing = appService.getApplicationsByCandidate(u.getId())
                        .stream()
                        .filter(ap -> ap.getJobId() == jid)
                        .findFirst();

                    if (existing.isPresent()) {
                        System.out.println("You have already applied to this job.");
                    } else {
                        Application a = new Application();
                        a.setJobId(jid); 
                        a.setCandidateId(u.getId()); 
                        a.setStatus("APPLIED");
                        appService.apply(a);
                        System.out.println("Applied successfully!");
                    }
                }
                case 3 -> {
                    List<Application> apps = appService.getApplicationsByCandidate(u.getId());
                    if (apps.isEmpty()) System.out.println("No applications yet.");
                    else apps.forEach(System.out::println);
                }
                case 4 -> {
                    System.out.print("Name: "); String name = in.nextLine();
                    System.out.print("Email: "); String email = in.nextLine();
                    System.out.print("Resume path: "); String resume = in.nextLine();
                    userService.updateProfile(u.getId(), name, email, resume);
                    System.out.println("Profile updated.");
                }
                default -> System.out.println("Invalid");
            }
        }
    }


    private static void employerMenu(User u, JobService jobService, ApplicationService appService, UserService userService) {
        while (true) {
            System.out.println("\n[EMPLOYER] 1) Post Job 2) Edit Job 3) Delete Job 0) Logout");
            int c = parseInt();
            if (c == 0) return;
            switch (c) {
                case 1 -> {
                    System.out.print("Title: "); String title = in.nextLine();
                    System.out.print("Location: "); String loc = in.nextLine();
                    System.out.print("Description: "); String desc = in.nextLine();

                    Job j = new Job();
                    j.setEmployer(u);
                    j.setTitle(title); 
                    j.setLocation(loc); 
                    j.setDescription(desc);

                    jobService.postJob(j);
                    System.out.println("Job posted!");
                }
                case 2 -> {
                    System.out.print("Job ID: "); int jid = parseInt();
                    System.out.print("New Title: "); String title = in.nextLine();
                    System.out.print("New Location: "); String loc = in.nextLine();
                    System.out.print("New Description: "); String desc = in.nextLine();
                    jobService.editJob(jid, title, loc, desc);
                    System.out.println("Job updated.");
                }
                case 3 -> {
                    System.out.print("Job ID: "); int jid = parseInt();
                    jobService.deleteJob(jid);
                    System.out.println("Job deleted.");
                }
                default -> System.out.println("Invalid");
            }
        }
    }

    private static void adminMenu(User u, UserService userService, JobService jobService, ApplicationService appService, AdminService adminService) {
        while (true) {
            System.out.println("\n[ADMIN] 1) List Users 2) List Jobs 3) Delete User 4) Delete Job 5) View Stats 0) Logout");
            int c = parseInt();
            if (c == 0) return;
            switch (c) {
                case 1 -> { // List Users
                    List<User> users = userService.all();
                    if (users.isEmpty()) System.out.println("No users found.");
                    else users.forEach(u1 -> System.out.println(
                        "ID: " + u1.getId() + ", Name: " + u1.getName() + ", Email: " + u1.getEmail() + ", Role: " + u1.getRole()
                    ));
                }
                case 2 -> { 
                    List<Job> jobs = jobService.getAllJobs();
                    if (jobs.isEmpty()) System.out.println("No jobs found.");
                    else jobs.forEach(j -> System.out.println(
                        "ID: " + j.getId() + ", Title: " + j.getTitle() + ", Location: " + j.getLocation() +
                        ", Employer: " + j.getEmployer().getName()
                    ));
                }
                case 3 -> {
                    System.out.print("User ID: "); int uid = parseInt();
                    userService.deleteUser(uid);
                    System.out.println("User deleted.");
                }
                case 4 -> {
                    System.out.print("Job ID: "); int jid = parseInt();
                    jobService.deleteJob(jid);
                    System.out.println("Job deleted.");
                }
                case 5 -> {
                    System.out.println("Total users: " + adminService.getTotalUsers());
                    System.out.println("Total jobs: " + adminService.getTotalJobs());
                    System.out.println("Total applications: " + adminService.getTotalApplications());
                }
                default -> System.out.println("Invalid");
            }
        }
    }

 
    private static void listJobs(JobService jobService) {
        List<Job> list = jobService.listAll();
        if (list.isEmpty()) System.out.println("No jobs available.");
        else {
            System.out.println("Available Jobs:");
            for (Job j : list) {
                System.out.println("ID: " + j.getId() +
                                   ", Title: " + j.getTitle() +
                                   ", Location: " + j.getLocation() +
                                   ", Description: " + j.getDescription() +
                                   ", Employer: " + j.getEmployer().getName());
            }
        }
    }

    private static int parseInt() {
        try { 
            return Integer.parseInt(in.nextLine().trim()); 
        } catch (Exception e) { 
            return -1; 
        }
    }
}
