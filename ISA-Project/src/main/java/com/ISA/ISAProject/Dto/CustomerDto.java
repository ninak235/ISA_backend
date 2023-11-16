package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CustomerDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String country;
    @NotEmpty
    private String city;
    @NotEmpty
    private String number;
    @NotEmpty
    private String occupation;
    @NotEmpty
    private String companyInfo;

    public CustomerDto(){

    }

    public CustomerDto(User user, String occupation, String companyInfo) {
        this.firstName = user.getFirstName();
        this.email = user.getEmail();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.country = user.getPassword();
        this.city = user.getCity();
        this.number = user.getNumber();
        this.occupation = occupation;
        this.companyInfo = companyInfo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }
}
