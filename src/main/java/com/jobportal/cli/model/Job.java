package com.jobportal.cli.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private String location;

    // ✅ Link job → employer (User) with a foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    private User employer;

    // --------- Getters & Setters ---------
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public User getEmployer() { return employer; }
    public void setEmployer(User employer) { this.employer = employer; }

    // ✅ Proper toString for readable output
    @Override
    public String toString() {
        return "Job ID: " + id +
               ", Title: '" + title + '\'' +
               ", Location: '" + location + '\'' +
               ", Description: '" + description + '\'' +
               ", Employer: " + (employer != null ? employer.getName() : "N/A");
    }
}
