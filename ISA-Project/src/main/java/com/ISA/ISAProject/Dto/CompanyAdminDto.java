package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class CompanyAdminDto {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Email
    @NotEmpty
    private String email;
    @Size(min = 8)
    private String password;
    @NotEmpty
    private String country;
    @NotEmpty
    private String city;
    @NotEmpty
    private String number;

    private CompanyDto company;

    //private List<ReservationDto> reservationsSet;

    public CompanyAdminDto() {
        // Default constructor for Jackson deserialization
    }

    public CompanyAdminDto(User user, Company company) {
        this.firstName = user.getFirstName();
        this.email = user.getEmail();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.country = user.getPassword();
        this.city = user.getCity();
        this.number = user.getNumber();

        this.company = new CompanyDto(company);
    }

    // Getters and Setters...


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
}

