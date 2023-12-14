package com.ISA.ISAProject.Model;

import com.ISA.ISAProject.Enum.TypeOfUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Where(clause = "deleted = false")
@Entity(name = "Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UserName", unique = true)
    private String userName;

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

    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;
    public User() {
        this.deleted = false;
    }

    public User(Integer id,String userName, String firstName, String lastName, String email, String password, String country, String city, String number, TypeOfUser typeOfUser, boolean deleted,boolean isEnabled) {
        this.id = id;
        this.userName = userName;
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

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
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
        Timestamp now = new Timestamp(new Date().getTime());
        this.setLastPasswordResetDate(now);
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

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Konvertujte TypeOfUser u listu GrantedAuthority objekata
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(typeOfUser.name()));
        return authorities;
    }

    @JsonIgnore
    public String getAuthority() {
        return typeOfUser.name();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
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
