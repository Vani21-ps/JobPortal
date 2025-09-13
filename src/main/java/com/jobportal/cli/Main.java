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

// Add Spring imports
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.jobportal.cli.config.AppConfig;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        LogManager.getLogManager().reset();

        // Use Spring ApplicationContext to get beans
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        UserDao userDao = ctx.getBean(UserDao.class);
        JobDao jobDao = ctx.getBean(JobDao.class);
        ApplicationDao appDao = ctx.getBean(ApplicationDao.class);

        AuthService auth = ctx.getBean(AuthService.class);
        UserService userService = ctx.getBean(UserService.class);
        JobService jobService = ctx.getBean(JobService.class);
        ApplicationService appService = ctx.getBean(ApplicationService.class);

        System.out.println("=== Job Portal ===");
        loop:
        while (true) {
            System.out.println("\n1) Register  2) Login  3) List Jobs  4) List Users (admin) 0) Exit");
            System.out.print("Choose: ");
            int ch = parseInt();
            try {
                switch (ch) {
                    case 1 -> register(auth);
                    case 2 -> {
                        User u = login(auth);
                        if (u.getRole() == Role.EMPLOYER || u.getRole() == Role.ADMIN) employerMenu(u, jobService, appService);
                        else candidateMenu(u, jobService, appService);
                    }
                    case 3 -> listJobs(jobService);
                    case 4 -> {
                        System.out.print("Admin email: "); String adminEmail = in.nextLine();
                        Optional<User> ad = userService.byId(1); // simple check skipped
                        List<User> users = userService.all();
                        users.forEach(System.out::println);
                    }
                    case 0 -> { System.out.println("Bye"); break loop; }
                    default -> System.out.println("Invalid");
                }
            } catch (Exception e) {
                System.out.println("Error: "+e.getMessage());
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
        System.out.println("Welcome " + u.getName() + " ("+u.getRole()+")");
        return u;
    }

    private static void candidateMenu(User u, JobService jobService, ApplicationService appService) {
        while (true) {
            System.out.println("\n[CANDIDATE] 1) List jobs 2) Apply 3) My apps 0) Logout");
            int c = parseInt();
            if (c==0) return;
            switch (c) {
                case 1 -> listJobs(jobService);
                case 2 -> {
                    System.out.print("Job id: "); int jid = parseInt();
                    Application a = new Application();
                    a.setJobId(jid); a.setCandidateId(u.getId()); a.setStatus("APPLIED");
                    appService.apply(a);
                    System.out.println("Applied");
                }
                case 3 -> appService.byCandidate(u.getId()).forEach(System.out::println);
                default -> System.out.println("Invalid");
            }
        }
    }

    private static void employerMenu(User u, JobService jobService, ApplicationService appService) {
        while (true) {
            System.out.println("\n[EMPLOYER] 1) Post job 2) My jobs 3) View apps 0) Logout");
            int c = parseInt();
            if (c==0) return;
            switch (c) {
                case 1 -> {
                    System.out.print("Title: "); String title = in.nextLine();
                    System.out.print("Location: "); String loc = in.nextLine();
                    System.out.print("Description: "); String desc = in.nextLine();
                    Job j = new Job(); j.setEmployerId(u.getId()); j.setTitle(title); j.setLocation(loc); j.setDescription(desc);
                    jobService.postJob(j);
                    System.out.println("Posted");
                }
                case 2 -> jobService.byEmployer(u.getId()).forEach(System.out::println);
                case 3 -> {
                    System.out.print("Job id: "); int jid = parseInt();
                    appService.byJob(jid).forEach(System.out::println);
                }
                default -> System.out.println("Invalid");
            }
        }
    }

    private static void listJobs(JobService jobService) {
        List<Job> list = jobService.listAll();
        if (list.isEmpty()) System.out.println("No jobs");
        else list.forEach(System.out::println);
    }

    private static int parseInt() {
        try { return Integer.parseInt(in.nextLine().trim()); } catch (Exception e) { return -1; }
    }
}