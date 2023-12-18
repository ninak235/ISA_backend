package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.User;

import javax.validation.constraints.NotEmpty;

public class CompanyAdminBasicDto {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    public CompanyAdminBasicDto() {
        // Empty constructor
    }

    public CompanyAdminBasicDto(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
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
}
