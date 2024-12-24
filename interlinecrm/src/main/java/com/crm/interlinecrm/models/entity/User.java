package com.crm.interlinecrm.models.entity;

import com.crm.interlinecrm.models.vo.*;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {

    @EmbeddedId
    private UserId id;

    @Column(name = "uid")
    private String uid;

    @Column(name = "password")
    private String password;

    @Embedded
    private Name name;

    @Embedded
    private Phone phone;

    @Embedded
    private Address address;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    private User() {}

    public User(UserId userId, String uid, Name name, Phone phone, Address address){
        this.id = userId;
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.role = Role.ROLE_NORMAL;

    }

    public UserId userId(){return this.id;}

    public String uid(){return this.uid;}

    public String password(){return this.password;}

    public Name name(){return this.name;}
    public Phone phone(){return this.phone;}
    public Address address(){return this.address;}

    public Role role() {return this.role;}

    public boolean isAdmin() {
        return role.equals(Role.ROLE_ADMIN);
    }

    public void roleToAdmin(){
        this.role = Role.ROLE_ADMIN;
    }

    public boolean checkPassword(String password, PasswordEncoder passwordEncoder){
        return passwordEncoder.matches(password, this.password);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void changeBasicInfo(Name name, Phone phone, Address address){
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

}
