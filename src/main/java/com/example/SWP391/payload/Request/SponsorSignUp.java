package com.example.SWP391.Payload.Request;

import lombok.Data;

@Data
public class SponsorSignUp {
    private String email;
    private String password;
    private String fpt_staff_email;
    private String confirm_password;
    private  String company_name;
    private String company_id;
    private String information;

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
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

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getFpt_staff_email() {
        return fpt_staff_email;
    }

    public void setFpt_staff_email(String fpt_staff_email) {
        this.fpt_staff_email = fpt_staff_email;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_io() {
        return company_id;
    }

    public void setCompany_io(String company_io) {
        this.company_id = company_io;
    }
}
