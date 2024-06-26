package com.example.SWP391.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "account")
    private Set<Visitor> visitors;

    @OneToMany(mappedBy = "account")
    private Set<EventOperator> eventOperators;

    @OneToMany(mappedBy = "account")
    private Set<Sponsor> sponsors;

    @OneToMany(mappedBy = "account")
    private Set<CheckingStaff> checkingStaffs;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(Set<Visitor> visitors) {
        this.visitors = visitors;
    }

    public Set<EventOperator> getEventOperators() {
        return eventOperators;
    }

    public void setEventOperators(Set<EventOperator> eventOperators) {
        this.eventOperators = eventOperators;
    }

    public Set<CheckingStaff> getCheckingStaffs() {
        return checkingStaffs;
    }

    public void setCheckingStaffs(Set<CheckingStaff> checkingStaffs) {
        this.checkingStaffs = checkingStaffs;
    }

    public Set<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(Set<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }


}