package com.jobportal.cli.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable=false) private String name;
    @Column(nullable=false, unique=true) private String email;
    @Column(name="password_hash", nullable=false) private String passwordHash;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name="created_at") private LocalDateTime createdAt;

    // Added for resume support
    @Column(name="resume_path")
    private String resumePath;

    public User() {}
    public User(String name, String email, String passwordHash, Role role) {
        this.name = name; this.email = email; this.passwordHash = passwordHash; this.role = role;
    }
    // getters & setters
    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }
    public String getPasswordHash(){ return passwordHash; }
    public void setPasswordHash(String passwordHash){ this.passwordHash = passwordHash; }
    public Role getRole(){ return role; }
    public void setRole(Role role){ this.role = role; }
    public LocalDateTime getCreatedAt(){ return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt){ this.createdAt = createdAt; }
    public String getResumePath() { return resumePath; }
    public void setResumePath(String resumePath) { this.resumePath = resumePath; }

    @Override
    public String toString(){
        return "User{" + id + ", '" + name + "', '" + email + "', role=" + role +
               (resumePath != null ? ", resume='" + resumePath + "'" : "") + "}";
    }
}