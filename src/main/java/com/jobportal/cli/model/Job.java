package com.jobportal.cli.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="employer_id") private int employerId;
    private String title;
    @Column(columnDefinition = "TEXT") private String description;
    private String location;
    @Column(name="posted_at") private LocalDateTime postedAt;

    public Job() {}
    // getters/setters
    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }
    public int getEmployerId(){ return employerId; }
    public void setEmployerId(int employerId){ this.employerId = employerId; }
    public String getTitle(){ return title; }
    public void setTitle(String title){ this.title = title; }
    public String getDescription(){ return description; }
    public void setDescription(String description){ this.description = description; }
    public String getLocation(){ return location; }
    public void setLocation(String location){ this.location = location; }
    public LocalDateTime getPostedAt(){ return postedAt; }
    public void setPostedAt(LocalDateTime postedAt){ this.postedAt = postedAt; }
    @Override public String toString(){ return "Job{"+id+", '"+title+"', loc='"+location+"', employerId="+employerId+"}"; }
}
