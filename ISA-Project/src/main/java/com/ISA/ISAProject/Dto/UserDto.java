package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String userName;
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



    public UserDto(){
    }

    public UserDto(User user) {
        this.firstName = user.getFirstName();
        this.email = user.getEmail();
        this.userName = user.getUsername();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.country = user.getCountry();
        this.city = user.getCity();
        this.number = user.getNumber();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
