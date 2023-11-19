package com.ISA.ISAProject.Model;

import com.ISA.ISAProject.Enum.TypeOfUser;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;

@Where(clause = "deleted = false")
@Entity(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FirstName",nullable = false)
    private String firstName;

    @Column(name = "LastName",nullable = false)
    private String lastName;

    @Column(name = "Email",unique = true,nullable = false)
    private String email;
    //Mozda i sliku dodati ako bude vremena
    @Column(name = "Password",unique = true,nullable = false)
    private String password;

    @Column(name = "Country",nullable = false)
    private String Country;

    @Column(name = "City" , nullable = false)
    private String City;

    @Column(name = "Number",unique = true,nullable = false)
    private String Number;

    @Enumerated(EnumType.STRING)
    @Column(name = "UserType",nullable = false)
    private TypeOfUser typeOfUser;

    @Column(name = "deleted",columnDefinition = "boolean default false")
    private boolean deleted;

    @Column(name = "isEnabled",columnDefinition = "boolean default false")
    private boolean isEnabled;

    public User() {
        this.deleted = false;
    }

    public User(Integer id, String firstName, String lastName, String email, String password, String country, String city, String number, TypeOfUser typeOfUser, boolean deleted,boolean isEnabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        Country = country;
        City = city;
        Number = number;
        this.typeOfUser = typeOfUser;
        this.deleted = deleted;
        this.isEnabled = isEnabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public TypeOfUser getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(TypeOfUser typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

}
