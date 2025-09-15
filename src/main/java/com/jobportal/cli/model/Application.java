package com.jobportal.cli.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="job_id") 
    private int jobId;

    @Column(name="candidate_id") 
    private int candidateId;

    private String status;

    @Column(name="applied_at") 
    private LocalDateTime appliedAt;

    public Application() {}

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }

    public int getCandidateId() { return candidateId; }
    public void setCandidateId(int candidateId) { this.candidateId = candidateId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }

    @Override
    public String toString() {
        return "Application ID: " + id +
               ", Job ID: " + jobId +
               ", Candidate ID: " + candidateId +
               ", Status: " + status +
               (appliedAt != null ? ", Applied At: " + appliedAt : "");
    }
}
